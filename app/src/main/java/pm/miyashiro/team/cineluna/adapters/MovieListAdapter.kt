package pm.miyashiro.team.cineluna.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pm.miyashiro.team.cineluna.R
import pm.miyashiro.team.cineluna.classes.data.Movie

class MovieListAdapter (
    private val context : Context,
    private val movieList : List<Movie>,
    private val onClickListener: (Movie) -> Unit
    ):
    RecyclerView.Adapter<MovieListViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MovieListViewHolder(context,layoutInflater.inflate(R.layout.view_movie,parent,false))
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.render(
            movieList[position],
            onClickListener
        )
    }

    override fun getItemCount(): Int = movieList.size

}