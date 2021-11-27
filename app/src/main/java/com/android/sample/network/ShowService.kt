package com.android.sample.network

import com.android.sample.model.Show
import retrofit2.http.GET
import retrofit2.http.Query

interface ShowService {

    @GET("shows")
    suspend fun fetchShowList(@Query("page") page: Int): List<Show>
}