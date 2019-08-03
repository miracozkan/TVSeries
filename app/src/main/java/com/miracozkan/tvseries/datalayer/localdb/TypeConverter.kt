package com.miracozkan.tvseries.datalayer.localdb

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.miracozkan.tvseries.datalayer.model.*


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 22/07/19 - 13:26            │
//└─────────────────────────────┘

object TypeConverter {

    @TypeConverter
    @JvmStatic
    fun backdropToString(array: List<Backdrop>): String {
        if (array.isEmpty()) {
            return ""
        }
        val builder = StringBuilder(array[0].toString())
        for (i in 1 until array.size) {
            builder.append(",").append(array[i])
        }
        return builder.toString()
    }

    @TypeConverter
    @JvmStatic
    fun backdropFromString(value: String): List<Backdrop> {
        val listType = object : TypeToken<List<Backdrop>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    @JvmStatic
    fun posterToString(array: List<Poster>): String {
        if (array.isEmpty()) {
            return ""
        }
        val builder = StringBuilder(array[0].toString())
        for (i in 1 until array.size) {
            builder.append(",").append(array[i])
        }
        return builder.toString()
    }

    @TypeConverter
    @JvmStatic
    fun posterFromString(value: String): List<Poster> {
        val listType = object : TypeToken<List<Poster>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    @JvmStatic
    fun popularSeriesResultToString(array: List<PopularSeriesResult>): String {
        if (array.isEmpty()) {
            return ""
        }
        val builder = StringBuilder(array[0].toString())
        for (i in 1 until array.size) {
            builder.append(",").append(array[i])
        }
        return builder.toString()
    }

    @TypeConverter
    @JvmStatic
    fun popularSeriesResultFromString(value: String): List<PopularSeriesResult> {
        val listType = object : TypeToken<List<PopularSeriesResult>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    @JvmStatic
    fun seriesCreatedBySeriesResultToString(array: List<SeriesCreatedBy>): String {
        if (array.isEmpty()) {
            return ""
        }
        val builder = StringBuilder(array[0].toString())
        for (i in 1 until array.size) {
            builder.append(",").append(array[i])
        }
        return builder.toString()
    }

    @TypeConverter
    @JvmStatic
    fun seriesCreatedByResultFromString(value: String): List<SeriesCreatedBy> {
        val listType = object : TypeToken<List<SeriesCreatedBy>>() {}.type
        return Gson().fromJson(value, listType)
    }

//    @TypeConverter
//    @JvmStatic
//    fun intToString(array: List<Int>): String {
//        if (array.isEmpty()) {
//            return ""
//        }
//        val builder = StringBuilder(array[0].toString())
//        for (i in 1 until array.size) {
//            builder.append(",").append(array[i])
//        }
//        return builder.toString()
//    }
//
//    @TypeConverter
//    @JvmStatic
//    fun intFromString(value: String): List<Int> {
//        val listType = object : TypeToken<List<Int>>() {}.type
//        return Gson().fromJson(value, listType)
//    }


    @TypeConverter
    @JvmStatic
    fun stringToIntList(data: String?): List<Int>? {
        return data?.let { _it ->
            _it.split(",").map { __it ->
                __it.toInt()

            }
        }
    }

    @TypeConverter
    @JvmStatic
    fun intListToString(ints: List<Int>?): String? {
        return ints?.joinToString(",")
    }


    @TypeConverter
    @JvmStatic
    fun seriesGenreToString(array: List<SeriesGenre>): String {
        if (array.isEmpty()) {
            return ""
        }
        val builder = StringBuilder(array[0].toString())
        for (i in 1 until array.size) {
            builder.append(",").append(array[i])
        }
        return builder.toString()
    }

    @TypeConverter
    @JvmStatic
    fun seriesGenreFromString(value: String): List<SeriesGenre> {
        val listType = object : TypeToken<List<SeriesGenre>>() {}.type
        return Gson().fromJson(value, listType)
    }
//
//    @TypeConverter
//    @JvmStatic
//    fun stringToString(array: List<String>): String {
//        if (array.isEmpty()) {
//            return ""
//        }
//        val builder = StringBuilder(array[0].toString())
//        for (i in 1 until array.size) {
//            builder.append(",").append(array[i])
//        }
//        return builder.toString()
//    }
//
//    @TypeConverter
//    @JvmStatic
//    fun stringFromString(value: String): List<String> {
//        val listType = object : TypeToken<List<String>>() {}.type
//        return Gson().fromJson(value, listType)
//    }


    @TypeConverter
    @JvmStatic
    fun stringToStringList(data: String?): List<String>? {
        return data?.let { _it ->
            _it.split(",").map { __it ->
                __it

            }
        }
    }

    @TypeConverter
    @JvmStatic
    fun StringListToString(ints: List<String>?): String? {
        return ints?.joinToString(",")
    }

    @TypeConverter
    @JvmStatic
    fun seriesNetworkToString(array: List<SeriesNetwork>): String {
        if (array.isEmpty()) {
            return ""
        }
        val builder = StringBuilder(array[0].toString())
        for (i in 1 until array.size) {
            builder.append(",").append(array[i])
        }
        return builder.toString()
    }

    @TypeConverter
    @JvmStatic
    fun seriesNetworkFromString(value: String): List<SeriesNetwork> {
        val listType = object : TypeToken<List<SeriesNetwork>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    @JvmStatic
    fun seriesProductCompanyToString(array: List<SeriesProductCompany>): String {
        if (array.isEmpty()) {
            return ""
        }
        val builder = StringBuilder(array[0].toString())
        for (i in 1 until array.size) {
            builder.append(",").append(array[i])
        }
        return builder.toString()
    }

    @TypeConverter
    @JvmStatic
    fun seriesProductCompanyFromString(value: String): List<SeriesProductCompany> {
        val listType = object : TypeToken<List<SeriesProductCompany>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    @JvmStatic
    fun seriesSeasonCompanyToString(array: List<SeriesSeason>): String {
        if (array.isEmpty()) {
            return ""
        }
        val builder = StringBuilder(array[0].toString())
        for (i in 1 until array.size) {
            builder.append(",").append(array[i])
        }
        return builder.toString()
    }

    @TypeConverter
    @JvmStatic
    fun seriesSeasonCompanyFromString(value: String): List<SeriesSeason> {
        val listType = object : TypeToken<List<SeriesSeason>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    @JvmStatic
    fun seriesReviewsToString(array: List<SeriesReviews>): String {
        if (array.isEmpty()) {
            return ""
        }
        val builder = StringBuilder(array[0].toString())
        for (i in 1 until array.size) {
            builder.append(",").append(array[i])
        }
        return builder.toString()
    }

    @TypeConverter
    @JvmStatic
    fun seriesReviewsFromString(value: String): List<SeriesReviews> {
        val listType = object : TypeToken<List<SeriesReviews>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    @JvmStatic
    fun videoResultToString(array: List<VideoResult>): String {
        if (array.isEmpty()) {
            return ""
        }
        val builder = StringBuilder(array[0].toString())
        for (i in 1 until array.size) {
            builder.append(",").append(array[i])
        }
        return builder.toString()
    }

    @TypeConverter
    @JvmStatic
    fun videoResultFromString(value: String): List<VideoResult> {
        val listType = object : TypeToken<List<VideoResult>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    @JvmStatic
    fun longToString(array: List<Long>): String {
        if (array.isEmpty()) {
            return ""
        }
        val builder = StringBuilder(array[0].toString())
        for (i in 1 until array.size) {
            builder.append(",").append(array[i])
        }
        return builder.toString()
    }

    @TypeConverter
    @JvmStatic
    fun longFromString(value: String): List<Long> {
        val listType = object : TypeToken<List<Long>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    @JvmStatic
    fun stringToSeriesLastEpisode(value: String): SeriesLastEpisode {
        return if (value == "") {
            SeriesLastEpisode()
        } else {
            val listType = object : TypeToken<SeriesLastEpisode>() {}.type
            Gson().fromJson(value, listType)
        }
    }

    @TypeConverter
    @JvmStatic
    fun seriesLastEpisodeToString(any: SeriesLastEpisode): String {
        return run {
            val gson = Gson()
            gson.toJson(any)
        }
    }

    @TypeConverter
    @JvmStatic
    fun stringToAny(value: String): Any {
        return if (value == "") {
            Any()
        } else {
            val listType = object : TypeToken<Any>() {}.type
            Gson().fromJson(value, listType)
        }
    }

    @TypeConverter
    @JvmStatic
    fun anyToString(any: Any): String {
        return run {
            Gson().toJson(any)
        }
    }

    @TypeConverter
    @JvmStatic
    fun stringToSeriesNextEpisodeToAir(value: String?): SeriesNextEpisodeToAir {
        return if (value == "") {
            SeriesNextEpisodeToAir()
        } else {
            val listType = object : TypeToken<SeriesNextEpisodeToAir>() {}.type
            Gson().fromJson(value, listType)
        }
    }

    @TypeConverter
    @JvmStatic
    fun seriesNextEpisodeToAirToString(any: SeriesNextEpisodeToAir?): String {
        return run {
            Gson().toJson(any)
        }
    }

    @TypeConverter
    @JvmStatic
    fun stringToPopularSeriesResult(value: String): PopularSeriesResult {
        return if (value == "") {
            PopularSeriesResult()
        } else {
            val listType = object : TypeToken<PopularSeriesResult>() {}.type
            Gson().fromJson(value, listType)
        }
    }

    @TypeConverter
    @JvmStatic
    fun popularSeriesResultToString(value: PopularSeriesResult): String {
        return run {
            Gson().toJson(value)
        }
    }


}