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
import pm.miyashiro.team.cineluna.classes.controller.GestorPeliculas
import pm.miyashiro.team.cineluna.classes.controller.GestorPeliculas.Companion.listaPeliculas
import pm.miyashiro.team.cineluna.databinding.ActivityMainBinding
import pm.miyashiro.team.cineluna.fragments.ListaPeliculasFragment
import pm.miyashiro.team.cineluna.fragments.PeliculaDetalleFragment


class MainActivity : AppCompatActivity() {

    var numeroPagina : Int = 1
    private lateinit var binding: ActivityMainBinding                                               //VIEW BINDING
    private lateinit var nombreDelUsuario : String
    private lateinit var adapterRV : MovieListAdapter
    val fragments : List<Fragment> = listOf(PeliculaDetalleFragment(),ListaPeliculasFragment())
    var ft : FragmentTransaction = supportFragmentManager.beginTransaction()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Se recuperan los datos del intent
        datosIntent()
        // Se ingresa el nombre del usuario correctamente

        supportActionBar?.title = "Hola " + nombreDelUsuario + "!"

        binding = ActivityMainBinding.inflate(layoutInflater)                                       //VIEW BINDING
        setContentView(binding.root)                                                                //VIEW BINDING
        val header = binding.navMain.getHeaderView(0)
        header.findViewById<TextView>(R.id.txNameHeader).setText(nombreDelUsuario)

        ft.replace(R.id.fragcont,fragments[1]).commit()

        binding.navMain.setNavigationItemSelectedListener {
            it.setChecked(true)

            when(it.itemId){
                R.id.aboutUs -> hacerAlgo()
                R.id.pelis ->hacerAlgo2()
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

    private fun hacerAlgo2() {
        supportActionBar?.title = "Hola " + nombreDelUsuario + "!"
        ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragcont,fragments[1]).commit()
    }

    private fun hacerAlgo() {
        supportActionBar?.title = "Quienes somos?"
        ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragcont,fragments[0]).commit()
    }



    private fun datosIntent() {
        nombreDelUsuario = intent.extras?.getString("Nombre").toString()
    }
    private fun showError() {
        Toast.makeText(this,"OCURRIO UN ERROR",Toast.LENGTH_LONG).show()
    }
}

