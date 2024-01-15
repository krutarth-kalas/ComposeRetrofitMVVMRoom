package com.example.composeretrofitmvvmroomcode.model

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    @JsonProperty("brand")
    var brand: String? = "",
    @JsonProperty("category")
    var category: String? = "",
    @JsonProperty("description")
    var description: String? = "",
    @JsonProperty("discountPercentage")
    var discountPercentage: Double? = 0.0,
    @JsonProperty("id")
    var id: Int? = 0,
    @JsonProperty("images")
    var images: List<String>,
    @JsonProperty("price")
    var price: Int? = 0,
    @JsonProperty("rating")
    var rating: Double? = 0.0,
    @JsonProperty("stock")
    var stock: Int? = 0,
    @JsonProperty("thumbnail")
    var thumbnail: String? = "",
    @JsonProperty("title")
    var title: String? = "",
) : Parcelable