package pm.miyashiro.team.cineluna

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import pm.miyashiro.team.cineluna.adapters.MovieListAdapter
import pm.miyashiro.team.cineluna.classes.controller.DatosUsuario
import pm.miyashiro.team.cineluna.classes.controller.DatosUsuario.Companion.NombreUsuario
import pm.miyashiro.team.cineluna.classes.controller.GestorPeliculas
import pm.miyashiro.team.cineluna.classes.controller.GestorPeliculas.Companion.listaPeliculas
import pm.miyashiro.team.cineluna.databinding.ActivityMainBinding
import pm.miyashiro.team.cineluna.fragments.ListaPeliculasFragment
import pm.miyashiro.team.cineluna.fragments.PeliculaDetalleFragment
import pm.miyashiro.team.cineluna.fragments.SobreNosotrosFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding                                               //VIEW BINDING
    val fragments : List<Fragment> = listOf(PeliculaDetalleFragment(),ListaPeliculasFragment(), SobreNosotrosFragment())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Se recuperan los datos del intent
        datosIntent()
        // Se ingresa el nombre del usuario correctamente
        binding = ActivityMainBinding.inflate(layoutInflater)                                       //VIEW BINDING
        setContentView(binding.root)                                                                //VIEW BINDING
        val header = binding.navMain.getHeaderView(0)
        header.findViewById<TextView>(R.id.txNameHeader).setText(NombreUsuario)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragcont,fragments[1],"lista")
            .commit()

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Hola " + NombreUsuario + "!"
        var toggle  =
            ActionBarDrawerToggle(this,binding.drawerLayoutMain,binding.toolbar,R.string.drawer_open,R.string.drawer_close)

        binding.drawerLayoutMain.addDrawerListener(toggle)
        toggle.syncState()

        binding.navMain.setCheckedItem(R.id.pelis)

        binding.navMain.setNavigationItemSelectedListener {
            it.setChecked(true)

            when(it.itemId){
                R.id.aboutUs -> pressItemAboutUs()
                R.id.pelis ->pressItemPel()
            }

            binding.drawerLayoutMain.closeDrawer(GravityCompat.START)
            true
        }

    }



    override fun onBackPressed() {
        if(binding.drawerLayoutMain.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayoutMain.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }
    }

    private fun pressItemPel() {
        if(supportFragmentManager.findFragmentById(R.id.fragcont)!=fragments[1]){
        supportActionBar?.title = "Hola " + NombreUsuario + "!"
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .show(fragments[1])
            .commit()}
        else{
            showError()
        }
    }

    private fun pressItemAboutUs() {
        if(supportFragmentManager.findFragmentById(R.id.fragcont)!=fragments[2]){
        supportActionBar?.title = "¿Quiénes somos?"
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .hide(fragments[1])
            .add(R.id.fragcont,fragments[2],"aboutus")
            .addToBackStack("aboutus")
            .commit()
        }else{
            showError()
        }
    }



    private fun datosIntent() {
        DatosUsuario.NombreUsuario = intent.extras?.getString("Nombre").toString()
    }
    private fun showError() {
        Toast.makeText(this,"Pagina actual",Toast.LENGTH_LONG).show()
    }



}

