package com.example.domain.interactor

import com.example.domain.model.Item
import com.example.domain.repository.ItemRepository
import io.reactivex.rxjava3.core.Single

class ItemInteractorImpl(private val itemRepository: ItemRepository) : ItemInteractor {

    override fun getPropertyList(): Single<List<Item>> {
        return itemRepository.getPropertyList()
    }

}
