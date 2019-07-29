package com.miracozkan.tvseries.datalayer.localdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.miracozkan.tvseries.datalayer.network.response.GetSeriesDetail


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 22/07/19 - 12:44            │
//└─────────────────────────────┘

@Dao
interface ProjectDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSeriesDetail(getSeriesDetail: GetSeriesDetail)

    @Query("SELECT * FROM get_series_detail_table WHERE sid= :id")
    suspend fun getSeriesDetail(id: Int): GetSeriesDetail
}