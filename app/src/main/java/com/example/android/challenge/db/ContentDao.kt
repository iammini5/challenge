
package com.example.android.challenge.db

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.challenge.model.Content

/**
 * Room data access object for accessing the [Content] table.
 */
@Dao
interface ContentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts: List<Content>)

    // Do a similar query as the getContent API:
    // Look for repos that contain the query string in the name or in the description
    // and order those results descending, by the number of stars and then by name
    @Query("SELECT * FROM contents ORDER BY id ASC")
    fun contentsByTime(): DataSource.Factory<Int, Content>
}
