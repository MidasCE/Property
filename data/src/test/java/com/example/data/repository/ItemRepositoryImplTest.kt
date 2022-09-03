package com.example.data.repository

import com.example.data.api.ItemAPI
import com.example.data.entity.ItemData
import com.example.data.entity.ItemsResponse
import com.example.data.repository.ItemRepositoryImpl
import com.example.domain.model.Item
import com.example.domain.repository.ItemRepository
import io.reactivex.rxjava3.core.Single
import org.junit.Test

import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class ItemRepositoryImplTest {

    @Mock
    lateinit var api: ItemAPI


    private lateinit var repository: ItemRepository

    @Before
    fun setUp() {
        repository = ItemRepositoryImpl(api)
    }

    @Test
    fun `Test getPropertyList | should return data from api`() {
        val itemData = ItemData(
            "Property",
            "id",
            "askingPrice",
            "monthlyFee",
            "municipality",
            "area",
            "averagePrice",
            "rating",
            1,
            2,
            3,
            "streetAddress",
            "image"
        )

        val response = ItemsResponse(
            items = listOf(itemData)
        )

        val item = Item.PropertyItem(
            "Property",
            "id",
            "area",
            "image",
            "askingPrice",
            "monthlyFee",
            "municipality",
            1,
            2,
            3,
            "streetAddress"
        )

        whenever(api.getItems()).thenReturn(Single.just(response))

        repository.getPropertyList().test()
            .assertValue(listOf(item))
    }
}