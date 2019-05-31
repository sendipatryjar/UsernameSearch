package com.example.cst_dev41.githubsearch.api
import com.example.cst_dev41.githubsearch.model.GithubResponse

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET("/search/users?page=1&per_page=100")
    fun getItems(@Query("q") query: String): Call<GithubResponse>

    @GET("&page=1&per_page=100")
    fun getPaging(): Call<GithubResponse>
}
