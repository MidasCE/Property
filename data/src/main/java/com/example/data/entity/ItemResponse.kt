package com.example.data.entity

import com.google.gson.annotations.SerializedName

data class ItemsResponse(
    @SerializedName("items") val items: List<ItemData>
)

data class ItemData(
    @SerializedName("type") val type: String,
    @SerializedName("id") val id: String,
    @SerializedName("askingPrice") val askingPrice: String?,
    @SerializedName("monthlyFee") val monthlyFee: String?,
    @SerializedName("municipality") val municipality: String?,
    @SerializedName("area") val area: String,
    @SerializedName("averagePrice") val averagePrice: String?,
    @SerializedName("rating") val rating: String?,
    @SerializedName("daysOnHemnet") val daysOnHemnet: Int?,
    @SerializedName("livingArea") val livingArea: Int?,
    @SerializedName("numberOfRooms") val numberOfRooms: Int?,
    @SerializedName("streetAddress") val streetAddress: String?,
    @SerializedName("image") val image: String
)
