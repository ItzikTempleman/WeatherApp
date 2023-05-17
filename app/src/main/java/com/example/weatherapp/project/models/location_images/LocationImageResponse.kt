package com.example.weatherapp.project.models.location_images

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationImageResponse(
    @SerializedName("data")
    val cityResponse: CityResponse
) : Parcelable


@Parcelize
data class CityResponse(

    @SerializedName("Typeahead_autocomplete")
    val resultResponse: Typeahead_autocomplete
) : Parcelable


@Parcelize
data class Typeahead_autocomplete(
    val results: List<Results>
) : Parcelable


@Parcelize
data class Results(
    val image: Image
) : Parcelable


@Parcelize
data class Image(
    val photo: Photo
) : Parcelable


@Parcelize
data class Photo(
    val photoSizes: List<PhotoSize>
) : Parcelable


@Parcelize
data class PhotoSize(
    val url: String
) : Parcelable