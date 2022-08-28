package com.example.property.main.property.viewentity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class PropertyItemEntity(
    open val imageUrl: String,
    open val price: String
): Parcelable {

    @Parcelize
    data class PropertyEntity(
        override val imageUrl: String,
        override val price: String
    ) : PropertyItemEntity(imageUrl, price), Parcelable

    @Parcelize
    data class AreaEntity(
        override val imageUrl: String,
        override val price: String
    ) : PropertyItemEntity(imageUrl, price), Parcelable

    @Parcelize
    data class HighlightedPropertyEntity(
        override val imageUrl: String,
        override val price: String
    ) : PropertyItemEntity(imageUrl, price), Parcelable

}