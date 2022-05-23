package pm.miyashiro.team.cineluna.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import pm.miyashiro.team.cineluna.R
import pm.miyashiro.team.cineluna.databinding.FragmentDetallepeliculaBinding

class PeliculaDetalleFragment : Fragment(){
    private var _binding : FragmentDetallepeliculaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetallepeliculaBinding.inflate(inflater,container,false)
        binding.butRegresar.setOnClickListener {
            val a = ListaPeliculasFragment()
            val transaction : FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.remove(this).commit()
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(arguments != null){
            val nombre = requireArguments().getString("Nombre")
            val detalle = requireArguments().getString("Detalle")
            val fondo = requireArguments().getString("Fondo")
            val foto = requireArguments().getString("Foto")
            val e1 : TextView = binding.txtNombre
            val e2 : TextView = binding.txtDescripcion
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500" + fondo)
                .into(binding.ivFondo)
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500" + foto)
                .into(binding.ivFotoPelicula)
            e1.setText(nombre)
            e2.setText(detalle)
        }
    }
}