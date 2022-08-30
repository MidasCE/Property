package com.example.domain.repository

import com.example.domain.model.Item
import io.reactivex.rxjava3.core.Single

interface ItemRepository {

    fun getPropertyList() : Single<List<Item>>

}
