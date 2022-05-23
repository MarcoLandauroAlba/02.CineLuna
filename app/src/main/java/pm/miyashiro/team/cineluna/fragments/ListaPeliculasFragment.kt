package pm.miyashiro.team.cineluna.fragments

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import pm.miyashiro.team.cineluna.R
import pm.miyashiro.team.cineluna.adapters.MovieListAdapter
import pm.miyashiro.team.cineluna.classes.controller.GestorPeliculas
import pm.miyashiro.team.cineluna.classes.controller.GestorPeliculas.Companion.listaPeliculas
import pm.miyashiro.team.cineluna.databinding.FragmentDetallepeliculaBinding
import pm.miyashiro.team.cineluna.databinding.FragmentListapeliculasBinding

class ListaPeliculasFragment:Fragment() {

    private var _binding : FragmentListapeliculasBinding? = null
    private val binding get() = _binding!!
    var numeroPagina : Int = 1
    private lateinit var adapterRV : MovieListAdapter
    private lateinit var thiscontext : Context


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListapeliculasBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        thiscontext = this.requireActivity().applicationContext


        actualizarPantalla()

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
            adapterRV = MovieListAdapter(thiscontext, listaPeliculas.results) {
                // AQUI PONER FUNCIONALIDAD PARA IR A OTRAS PANTALLAS
                val bundle = Bundle()
                val NombrePelicula = it.original_title
                val DetallePelicula = it.overview
                val FondoPelicula = it.backdrop_path
                val Foto = it.poster_path
                bundle.putString("Nombre", NombrePelicula)
                bundle.putString("Detalle", DetallePelicula)
                bundle.putString("Fondo", FondoPelicula)
                bundle.putString("Foto", Foto)

                val Detalle = PeliculaDetalleFragment()
                Detalle.arguments = bundle
                binding.clayout.visibility = View.GONE
                parentFragmentManager.beginTransaction()
                    .add(R.id.fragcont,Detalle,"detalle")
                    .addToBackStack("detalle")
                    .commit()
            }
            binding.rvMovieList.layoutManager = LinearLayoutManager(thiscontext)
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
}