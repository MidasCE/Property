package com.example.property.main.property.viewentity

import android.os.Parcelable
import com.example.domain.model.Item
import kotlinx.parcelize.Parcelize

sealed class PropertyItemEntity(
    open val imageUrl: String
): Parcelable {

    @Parcelize
    data class PropertyEntity(
        override val imageUrl: String
    ) : PropertyItemEntity(imageUrl), Parcelable

    @Parcelize
    data class AreaEntity(
        override val imageUrl: String
    ) : PropertyItemEntity(imageUrl), Parcelable

    @Parcelize
    data class HighlightedPropertyEntity(
        override val imageUrl: String
    ) : PropertyItemEntity(imageUrl), Parcelable

}

fun Item.toViewEntity(): PropertyItemEntity {
    return when(this) {
        is Item.PropertyItem -> {
            PropertyItemEntity.PropertyEntity(image)
        }
        is Item.AreaItem -> {
            PropertyItemEntity.AreaEntity(image)
        }
        is Item.HighlightedPropertyItem -> {
            PropertyItemEntity.HighlightedPropertyEntity(image)
        }
    }
}