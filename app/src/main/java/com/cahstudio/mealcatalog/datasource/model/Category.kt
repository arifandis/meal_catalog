package com.cahstudio.mealcatalog.datasource.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category(
    @SerializedName("idCategory") val idCategory: String?,
    @SerializedName("strCategory") val strCategory: String?,
    @SerializedName("strCategoryThumb") val strCategoryThumb: String?,
    @SerializedName("strCategoryDescription") val strCategoryDescription: String?
): Parcelable