package com.miracozkan.tvseries.datalayer.localdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.miracozkan.tvseries.datalayer.model.*
import com.miracozkan.tvseries.datalayer.network.response.*


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 22/07/19 - 12:41            │
//└─────────────────────────────┘

@Database(
    entities = [GetSeriesDetail::class, Backdrop::class, PopularSeriesResult::class, Poster::class,
        SeriesCreatedBy::class, SeriesGenre::class, SeriesLastEpisode::class, SeriesNetwork::class,
        SeriesProductCompany::class, SeriesReviews::class, SeriesSeason::class, VideoFragmentModel::class,
        VideoResult::class, GetSeriesReviews::class, GetImages::class, GetVideo::class,
        GetPopular::class, SeriesNextEpisodeToAir::class],
        version = 3
)
@TypeConverters(TypeConverter::class)
abstract class ProjectDatabase : RoomDatabase() {

    abstract fun projectDao(): ProjectDao

    companion object {
        @Volatile
        private var INSTANCE: ProjectDatabase? = null

        fun getInstance(context: Context): ProjectDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProjectDatabase::class.java,
                    "tv_series_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}