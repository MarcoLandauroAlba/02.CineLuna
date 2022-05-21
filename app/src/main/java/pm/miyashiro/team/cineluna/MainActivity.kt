package pm.miyashiro.team.cineluna

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import pm.miyashiro.team.cineluna.adapters.MovieListAdapter
import pm.miyashiro.team.cineluna.classes.controller.GestorPeliculas
import pm.miyashiro.team.cineluna.classes.controller.GestorPeliculas.Companion.listaPeliculas
import pm.miyashiro.team.cineluna.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    var numeroPagina : Int = 1
    private lateinit var binding: ActivityMainBinding                                               //VIEW BINDING
    private lateinit var nombreDelUsuario : String
    private lateinit var adapterRV : MovieListAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Se recuperan los datos del intent
        datosIntent()
        // Se ingresa el nombre del usuario correctamente
        supportActionBar?.title = "Hola " + nombreDelUsuario + "!"

        binding = ActivityMainBinding.inflate(layoutInflater)                                       //VIEW BINDING
        setContentView(binding.root)                                                                //VIEW BINDING



        actualizarPantalla()



        // LISTENERS
        binding.btnAnterior.setOnClickListener{
            funcionalidadesBotonAnterior()
        }
        binding.btnSiguiente.setOnClickListener{
            funcionalidadesBotonSiguiente()
        }
    }












    private fun actualizarPantalla() {
        binding.rvMovieList.visibility = View.GONE
        binding.viewLoading.visibility = View.VISIBLE
        binding.btnAnterior.isEnabled = true
        if(numeroPagina==1){
            binding.btnAnterior.isEnabled = false
        }
        binding.tvNumeroPagina.text = "Pagina "+numeroPagina
        inicializarRecyclerView()
    }



    private fun inicializarRecyclerView(){
        GestorPeliculas.obtenerPeliculas(numeroPagina.toString())

        Handler(Looper.getMainLooper()).postDelayed({
            adapterRV = MovieListAdapter(this, listaPeliculas.results) {
                // AQUI PONER FUNCIONALIDAD PARA IR A OTRAS PANTALLAS
                Toast.makeText(this,it.original_title,Toast.LENGTH_LONG).show()
            }
            binding.rvMovieList.layoutManager = LinearLayoutManager(this)
            binding.rvMovieList.adapter = adapterRV
            binding.rvMovieList.visibility = View.VISIBLE
            binding.viewLoading.visibility = View.GONE
        }, 3000)
    }





















    private fun funcionalidadesBotonSiguiente() {
        numeroPagina++
        actualizarPantalla()
    }
    private fun funcionalidadesBotonAnterior() {
        if(numeroPagina>1){
            numeroPagina--
            actualizarPantalla()
        }
    }
    private fun datosIntent() {
        nombreDelUsuario = intent.extras?.getString("Nombre").toString()
    }
    private fun showError() {
        Toast.makeText(this,"OCURRIO UN ERROR",Toast.LENGTH_LONG).show()
    }
}