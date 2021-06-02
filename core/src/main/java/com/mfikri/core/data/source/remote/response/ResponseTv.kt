package com.mfikri.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseTv(

    @field:SerializedName("results")
    val results: ArrayList<ResponseTvDetail>

)