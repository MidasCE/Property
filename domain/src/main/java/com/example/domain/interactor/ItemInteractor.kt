package com.example.domain.interactor

import com.example.domain.model.Item
import io.reactivex.rxjava3.core.Single

interface ItemInteractor {

    fun getPropertyList() : Single<List<Item>>
}
