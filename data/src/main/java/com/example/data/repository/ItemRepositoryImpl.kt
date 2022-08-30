package com.example.data.repository

import com.example.data.api.ItemAPI
import com.example.data.mapper.toDomain
import com.example.domain.model.Item
import com.example.domain.repository.ItemRepository
import io.reactivex.rxjava3.core.Single

class ItemRepositoryImpl(private val api: ItemAPI) : ItemRepository {

    override fun getPropertyList(): Single<List<Item>> {
        return api.getItems().map { itemResponse ->
            itemResponse.items.mapNotNull {  itemData ->
                itemData.toDomain()
            }
        }
    }

}
