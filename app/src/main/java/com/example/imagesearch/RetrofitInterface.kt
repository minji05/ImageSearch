package com.example.imagesearch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface RetrofitInterface {
    @GET("v2/search/image")
    fun Image(
        @Header("Authorization") apiKey: String?,
        @Query("query") query: String?,
        @Query("sort") sort: String?,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): Call<ImageModel?>?
}