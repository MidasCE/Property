package com.example.property.main.property.viewentity

import com.example.domain.model.Item
import org.junit.Assert.*
import org.junit.Test

class PropertyItemEntityTest {

    @Test
    fun `Item as PropertyItem | Should map to PropertyEntity`() {
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

        val viewEntity = PropertyItemEntity.PropertyEntity(
            "id",
            "image",
            "askingPrice",
            "monthlyFee",
            "municipality",
            "area",
            1,
            2,
            3,
            "streetAddress"
        )

        assertEquals(viewEntity, item.toViewEntity())
    }

    @Test
    fun `Item as AreaItem | Should map to AreaEntity`() {
        val item = Item.AreaItem(
            "type",
            "id",
            "area",
            "image",
            "averagePrice",
            "rating"
        )

        val viewEntity = PropertyItemEntity.AreaEntity(
            "id",
            "image",
            "area",
            "averagePrice",
            "rating"
        )

        assertEquals(viewEntity, item.toViewEntity())
    }

    @Test
    fun `Item as HighlightedPropertyItem | Should map to HighlightedPropertyEntity`() {
        val item = Item.HighlightedPropertyItem(
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

        val viewEntity = PropertyItemEntity.HighlightedPropertyEntity(
            "id",
            "image",
            "askingPrice",
            "monthlyFee",
            "municipality",
            "area",
            1,
            2,
            3,
            "streetAddress"
        )

        assertEquals(viewEntity, item.toViewEntity())
    }
}
