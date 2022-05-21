package pm.miyashiro.team.cineluna.api

import pm.miyashiro.team.cineluna.classes.data.MovieList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIthemoviedb {
    @GET("discover/movie?api_key=cd4bd725407cd84b3a9218fe23d48892")
    fun getMovieListDiscover(
        @Query("page") page: String
    )
    : Call<MovieList>
}