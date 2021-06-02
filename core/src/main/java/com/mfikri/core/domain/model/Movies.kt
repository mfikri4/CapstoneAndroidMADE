package com.mfikri.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movies(
    var id: Int = 0,
    var title: String,
    var poster_path: String,
    var release_date: String,
    var overview: String,
    var vote_average: Double,
    var vote_count: Int,
    var fav: Boolean
) : Parcelable