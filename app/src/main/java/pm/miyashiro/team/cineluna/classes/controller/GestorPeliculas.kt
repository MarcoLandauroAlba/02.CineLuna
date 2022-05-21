package pm.miyashiro.team.cineluna.classes.controller

import pm.miyashiro.team.cineluna.api.APIthemoviedb
import pm.miyashiro.team.cineluna.api.MovieAPIService
import pm.miyashiro.team.cineluna.classes.data.Movie
import pm.miyashiro.team.cineluna.classes.data.MovieList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GestorPeliculas {
    companion object {
        var listaPeliculas : MovieList = MovieList(listOf(
            Movie(
            "/A3bsT0m1um6tvcmlIGxBwx9eAxn.jpg",
            "ninguno1",
            "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg",
            0.0
            )
        ))

        fun obtenerPeliculas(numeroPagina : String) {
            val apiService = MovieAPIService.getRetrofit().create(APIthemoviedb::class.java)
            apiService.getMovieListDiscover(numeroPagina).enqueue(object :
                Callback<MovieList> {
                override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                    listaPeliculas = response.body()!!
                }

                override fun onFailure(call: Call<MovieList>, t: Throwable) {
                    listaPeliculas = MovieList(listOf(
                        Movie(
                            "/A3bsT0m1um6tvcmlIGxBwx9eAxn.jpg",
                            "ninguno2",
                            "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg",
                            0.0
                        )
                    ))
                }
            })
        }
    }

}