package com.example.domain.model

sealed class Item(
    open val type: String,
    open val id: String,
    open val area: String,
    open val image: String
) {

    data class PropertyItem(
        override val type: String,
        override val id: String,
        override val area: String,
        override val image: String,
        val askingPrice: String,
        val monthlyFee: String,
        val municipality: String,
        val daysOnHemnet: Int,
        val livingArea: Int,
        val numberOfRooms: Int,
        val streetAddress: String
    ) : Item(type, id, area, image)

    data class AreaItem(
        override val type: String,
        override val id: String,
        override val area: String,
        override val image: String,
        val averagePrice: String,
        val rating: String,
    ) : Item(type, id, area, image)

    data class HighlightedPropertyItem(
        override val type: String,
        override val id: String,
        override val area: String,
        override val image: String,
        val askingPrice: String,
        val monthlyFee: String,
        val municipality: String,
        val daysOnHemnet: Int,
        val livingArea: Int,
        val numberOfRooms: Int,
        val streetAddress: String
    ) : Item(type, id, area, image)

}