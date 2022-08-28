package com.example.data.entity

import com.google.gson.annotations.SerializedName

data class ItemResponse(
    @SerializedName("type") val type: String,
    @SerializedName("id") val id: String,
    @SerializedName("askingPrice") val askingPrice: String,
    @SerializedName("monthlyFee") val monthlyFee: String,
    @SerializedName("municipality") val municipality: String,
    @SerializedName("area") val area: String,
    @SerializedName("daysOnHemnet") val daysOnHemnet: Int,
    @SerializedName("livingArea") val livingArea: Int,
    @SerializedName("numberOfRooms") val numberOfRooms: Int,
    @SerializedName("streetAddress") val streetAddress: String,
    @SerializedName("image") val image: String
)
