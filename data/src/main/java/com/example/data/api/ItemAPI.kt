package com.example.data.api

import com.example.data.entity.ItemResponse
import retrofit2.Response
import retrofit2.http.GET

interface ItemAPI {

    @GET("eXqnGgCY")
    suspend fun getItems()
            : Response<ItemResponse>

}
