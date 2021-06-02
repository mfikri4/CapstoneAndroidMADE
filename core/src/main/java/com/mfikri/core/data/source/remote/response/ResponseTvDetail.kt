package com.mfikri.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseTvDetail(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("poster_path")
    val poster_path: String,

    @field:SerializedName("first_air_date")
    val first_air_date: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("vote_average")
    val vote_average: Double,

    @field:SerializedName("vote_count")
    val vote_count: Int

)


