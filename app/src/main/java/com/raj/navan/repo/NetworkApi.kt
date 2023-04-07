package com.raj.navan.repo

import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkApi {
    companion object {
        const val BASE_URL = "https://api.nytimes.com"
    }

    @GET("/svc/search/v2/articlesearch.json?api-key=OKsEwghCzAPR3kRr7Hp51cFn2tMfXWgj")
    suspend fun getNewsFromServer(@Query("q") search: String): NewsResponse
}