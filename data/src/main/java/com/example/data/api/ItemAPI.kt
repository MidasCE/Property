package com.example.data.api

import com.example.data.entity.ItemsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ItemAPI {

    @GET("eXqnGgCY")
    fun getItems()
            : Single<ItemsResponse>

}
