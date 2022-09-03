package com.example.data.mapper

import com.example.data.entity.ItemData
import com.example.domain.model.Item
import org.junit.Assert.*
import org.junit.Test

class ResponseMapperTest {

    @Test
    fun `ItemData with unknown type | Should map to null`() {
        val data = ItemData(
            "unknown",
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
            "image",
        )

        assertNull(data.toDomain())
    }

    @Test
    fun `ItemData with property type | Should map to PropertyItem`() {
        val data = ItemData(
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
            "image",
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

        assertEquals(item, data.toDomain())
    }

    @Test
    fun `ItemData with area type | Should map to AreaItem`() {
        val data = ItemData(
            "Area",
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
            "image",
        )

        val item = Item.AreaItem(
            "Area",
            "id",
            "area",
            "image",
            "averagePrice",
            "rating"
        )

        assertEquals(item, data.toDomain())
    }

    @Test
    fun `ItemData with HighlightedProperty type | Should map to HighlightedPropertyItem`() {
        val data = ItemData(
            "HighlightedProperty",
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
            "image",
        )
        val item = Item.HighlightedPropertyItem(
            "HighlightedProperty",
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

        assertEquals(item, data.toDomain())
    }

}
