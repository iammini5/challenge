
package com.example.android.challenge.model

import com.google.gson.annotations.SerializedName

data class UserContent(
        @SerializedName("title") val title: String,
        @SerializedName("rows") val items: List<Content> = emptyList(),
        val nextPage: Int? = null
)
