package com.example.weatherapp.project.models.unsplash_location_image

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UnsplashImageResponse(
    val results: List<ResulItem>
) : Parcelable


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