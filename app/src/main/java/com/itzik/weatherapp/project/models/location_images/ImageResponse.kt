package com.itzik.weatherapp.project.models.location_images

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageResponse(
    val results: List<ResulItem>
) : Parcelable{
    companion object {
        fun getMockObj() =ImageResponse(emptyList())
    }
}


@Parcelize
data class ResulItem(
    @SerializedName("urls")
    val resultUrls: Urls
) : Parcelable


@Parcelize
data class Urls(
    @SerializedName("full")
    val imageUrl: String
) : Parcelable