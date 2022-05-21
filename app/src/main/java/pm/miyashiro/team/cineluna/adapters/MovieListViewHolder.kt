package pm.miyashiro.team.cineluna.adapters

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pm.miyashiro.team.cineluna.classes.data.Movie
import pm.miyashiro.team.cineluna.databinding.ViewMovieBinding

class MovieListViewHolder(private val context : Context, view : View): RecyclerView.ViewHolder(view) {
    private val binding = ViewMovieBinding.bind(view)                                                       //VIEW BINDING

    fun render (
        pelicula : Movie,
        onClickListener: (Movie) -> Unit
    ){
//        FALTA ASIGNARLE SUS VALORES POR RETROFIT
        binding.tvTitulo.text = pelicula.original_title
        binding.tvVotacion.text = pelicula.vote_average.toString()
//        FALTA ASIGNARLES LA IMAGEN POR GLIDE
        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w500" + pelicula.backdrop_path)
            .into(binding.ivFondo)
        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w500" + pelicula.poster_path)
            .into(binding.ivFotoPortada)

        //El itemView sirve para que cuando la view se compone
        // de multiples subviews, con tocar el area que abarca
        // la view padre, es suficiente para arrancar el onclicklistener
        itemView.setOnClickListener{
            onClickListener(pelicula)
        }
    }
}
