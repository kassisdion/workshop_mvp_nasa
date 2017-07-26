package com.eldorne.workshop.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FeedResponse(
        @Expose
        @SerializedName("links")
        val links: Map<String, String>,

        @Expose
        @SerializedName("element_count")
        val elementCount: Long,

        @Expose
        @SerializedName("near_earth_objects")
        val objects: Map<String, List<SpaceObject>>)


data class SpaceObject(
        @Expose
        @SerializedName("neo_reference_id")
        val neoReferenceId: String,

        @Expose
        @SerializedName("name")
        val name: String,

        @Expose
        @SerializedName("nasa_jpl_url")
        val nasaJplUrl: String,

        @Expose
        @SerializedName("absolute_magnitude_h")
        val absoluteMagnitudeH: String)