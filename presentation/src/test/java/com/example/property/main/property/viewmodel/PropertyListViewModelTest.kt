package com.example.property.main.property.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.interactor.ItemInteractor
import com.example.domain.model.Item
import com.example.property.core.SchedulerFactory
import com.example.property.main.property.viewentity.toViewEntity
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class PropertyListViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var schedulerFactory: SchedulerFactory

    @Mock
    lateinit var itemInteractor: ItemInteractor

    private lateinit var viewModel: PropertyListViewModel

    private lateinit var ioScheduler: TestScheduler

    private lateinit var mainScheduler: TestScheduler

    @Before
    fun setUp() {
        ioScheduler = TestScheduler()
        mainScheduler = TestScheduler()

        whenever(schedulerFactory.io()).thenReturn(ioScheduler)
        whenever(schedulerFactory.main()).thenReturn(mainScheduler)

        viewModel = PropertyListViewModel(
            schedulerFactory,
            itemInteractor
        )

        viewModel.propertyItemLiveData.observeForever {  }
        viewModel.errorLiveData.observeForever {  }
        viewModel.loadingLiveData.observeForever {  }
    }
    @Test
    fun `Test getPropertyList | should show the proper list of property entity`() {
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

        whenever(itemInteractor.getPropertyList()).thenReturn(
            Single.just(
                listOf(item)
            )
        )

        viewModel.getPropertyItems()

        Assert.assertEquals(true, viewModel.loadingLiveData.value)

        ioScheduler.triggerActions()
        mainScheduler.triggerActions()

        verify(itemInteractor, times(1)).getPropertyList()
        Assert.assertEquals(false, viewModel.loadingLiveData.value)

        val propertyItem = viewModel.propertyItemLiveData.value
        Assert.assertEquals(listOf(item.toViewEntity()), propertyItem)
    }

    @Test
    fun `Test getPropertyList got error | should show error`() {
        val throwable = Throwable("error")

        whenever(itemInteractor.getPropertyList()).thenReturn(
            Single.error(
                throwable
            )
        )

        viewModel.getPropertyItems()
        Assert.assertEquals(true, viewModel.loadingLiveData.value)

        ioScheduler.triggerActions()
        mainScheduler.triggerActions()

        verify(itemInteractor, times(1)).getPropertyList()

        val errorResult = viewModel.errorLiveData.value
        Assert.assertEquals(throwable.toString(), errorResult)
    }

}
