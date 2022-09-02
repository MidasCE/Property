package com.example.property.main.property.viewentity

import android.os.Parcelable
import com.example.domain.model.Item
import kotlinx.parcelize.Parcelize

sealed class PropertyItemEntity(
    open val id: String,
    open val imageUrl: String
): Parcelable {

    @Parcelize
    data class PropertyEntity(
        override val id: String,
        override val imageUrl: String
    ) : PropertyItemEntity(id, imageUrl), Parcelable

    @Parcelize
    data class AreaEntity(
        override val id: String,
        override val imageUrl: String,
        val area: String,
        val averagePrice: String,
        val rating: String
    ) : PropertyItemEntity(id, imageUrl), Parcelable

    @Parcelize
    data class HighlightedPropertyEntity(
        override val id: String,
        override val imageUrl: String
    ) : PropertyItemEntity(id, imageUrl), Parcelable

}

fun Item.toViewEntity(): PropertyItemEntity {
    return when(this) {
        is Item.PropertyItem -> {
            PropertyItemEntity.PropertyEntity(id, image)
        }
        is Item.AreaItem -> {
            PropertyItemEntity.AreaEntity(id,
                image,
                area,
                averagePrice,
                rating)
        }
        is Item.HighlightedPropertyItem -> {
            PropertyItemEntity.HighlightedPropertyEntity(id, image)
        }
    }
}