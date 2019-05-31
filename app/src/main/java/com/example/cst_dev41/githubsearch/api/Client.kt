package com.example.cst_dev41.githubsearch.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Client {

    val BASE_URL = "https://api.github.com"
    var retrofit: Retrofit? = null

    val client: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return retrofit
        }
}
