package com.mfikri.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tv(
    var id: Int = 0,
    var name: String,
    var poster_path: String,
    var first_air_date: String,
    var overview: String,
    var vote_average: Double,
    var vote_count: Int,
    var fav: Boolean
) : Parcelable