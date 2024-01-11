package com.example.composeretrofitmvvmroomcode.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Keep
@Parcelize
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class UserData(
   @JsonProperty("limit")
    var limit: Int? = 0,
    @JsonProperty("skip")
    var skip: Int? = 0,
    @JsonProperty("total")
    var total: Int? = 0,
    @JsonProperty("products")
    var products: @RawValue List<Product>? = ArrayList<Product>()
) : Parcelable