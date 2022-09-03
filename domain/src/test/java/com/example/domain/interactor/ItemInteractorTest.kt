package com.example.domain.interactor

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
class ItemInteractorTest {

    @Mock
    lateinit var repository: ItemRepository

    private lateinit var interactor: ItemInteractor

    @Before
    fun setUp() {
        interactor = ItemInteractorImpl(repository)
    }

    @Test
    fun `Test getPropertyList | should getPropertyList from repository`() {
        val item = Item.PropertyItem(
            "type",
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

        whenever(repository.getPropertyList()).thenReturn(Single.just(listOf(item)))

        interactor.getPropertyList().test()
            .assertValue(listOf(item))
    }

}
