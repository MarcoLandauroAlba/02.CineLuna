package pm.miyashiro.team.cineluna.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieAPIService {
    companion object{
        private const val BASE_URL = "https://api.themoviedb.org/3/"
        private var retrofit : Retrofit? = null

        fun getRetrofit(): Retrofit {
            if (retrofit == null) {
                retrofit = Retrofit.Builder().also {
                    it.baseUrl(BASE_URL)
                    it.addConverterFactory(GsonConverterFactory.create())
                }
                    .build()
            }
            return retrofit!!
        }
    }
}