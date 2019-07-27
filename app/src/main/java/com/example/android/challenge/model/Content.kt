
package com.example.android.challenge.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Immutable model class for a repo that holds all the information about a repository.
 * Objects of this type are received from the Rest API, therefore all the fields are annotated
 * with the serialized name.
 * This class also defines the Room repos table, where the repo [id] is the primary key.
 */
@Entity(tableName = "contents")
data class Content(
    @PrimaryKey(autoGenerate = true) @field:SerializedName("id") val id: Long,
    @field:SerializedName("title") val title: String?,
    @field:SerializedName("description") val description: String?,
    @field:SerializedName("imageHref") val imageHref: String?
)
