package com.example.data.mapper

import com.example.data.entity.*
import com.example.domain.model.Item

fun ItemData.toDomain(): Item? {
    when (type) {
        "Area" -> {
            return Item.AreaItem(type, id, area, image, averagePrice ?: return null, rating ?: return null)
        }
        "HighlightedProperty" -> {
            return Item.HighlightedPropertyItem(
                type,
                id,
                area,
                image,
                askingPrice ?: return null,
                monthlyFee ?: return null,
                municipality ?: return null,
                daysOnHemnet ?: return null,
                livingArea ?: return null,
                numberOfRooms ?: return null,
                streetAddress ?: return null,
            )
        }
        "Property" -> {
            return Item.PropertyItem(
                type,
                id,
                area,
                image,
                askingPrice ?: return null,
                monthlyFee ?: return null,
                municipality ?: return null,
                daysOnHemnet ?: return null,
                livingArea ?: return null,
                numberOfRooms ?: return null,
                streetAddress ?: return null,
            )
        }
    }
    return null
}
