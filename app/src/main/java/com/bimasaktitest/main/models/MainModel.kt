package com.bimasaktitest.main.models

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Keep
object MainModel {
    @Keep
    @Parcelize
    data class Response(val results: MutableMap<String, MutableList<Data>>): Parcelable {
        class Deserializer: ResponseDeserializable<Response> {
            override fun deserialize(content: String): Response? {
                val response: MutableMap<String, MutableList<Data>> = mutableMapOf()
                val jsonObject = JSONObject(content)
                val date = jsonObject.keys()
                date.forEach {
                    val arrayData = jsonObject.getJSONArray(it)
                    val listData: MutableList<Data> = mutableListOf()
                    if (arrayData.length() != 0) {
                        for (i in 0 until arrayData.length()) {
                            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                            val jsonAdapter = moshi.adapter<Data>(
                                Data::class.java)
                            val data = jsonAdapter.fromJson(arrayData.getJSONObject(i).toString())
                            data?.let { it1 ->
                                listData.add(it1)
                            }
                        }
                    }
                    response[it] = listData
                }
                return Response(response)
            }
        }
    }

    @Keep
    @Parcelize
    @Entity(tableName = "local_data")
    data class Data(
        @ColumnInfo(name = "id") @PrimaryKey @Transient val id: Int? = 0,
        @ColumnInfo(name = "date") @Transient val date: String? = "",
        @ColumnInfo(name = "avg_time_generation") val avg_time_generation: Double,
        @ColumnInfo(name = "avg_time_on_page") val avg_time_on_page: Int,
        @ColumnInfo(name = "bounce_rate") val bounce_rate: String,
        @ColumnInfo(name = "entry_bounce_count") val entry_bounce_count: Int,
        @ColumnInfo(name = "entry_nb_actions") val entry_nb_actions: Int,
        @ColumnInfo(name = "entry_nb_visits") val entry_nb_visits: Int,
        @ColumnInfo(name = "entry_sum_visit_length") val entry_sum_visit_length: Int,
        @ColumnInfo(name = "exit_nb_visits") val exit_nb_visits: Int,
        @ColumnInfo(name = "exit_rate") val exit_rate: String,
        @ColumnInfo(name = "label") val label: String,
        @ColumnInfo(name = "max_bandwidth") val max_bandwidth: Int?,
        @ColumnInfo(name = "max_time_generation") val max_time_generation: String,
        @ColumnInfo(name = "min_bandwidth") val min_bandwidth: Int?,
        @ColumnInfo(name = "min_time_generation") val min_time_generation: String,
        @ColumnInfo(name = "nb_hits") val nb_hits: Int,
        @ColumnInfo(name = "nb_hits_with_bandwidth") val nb_hits_with_bandwidth: Int,
        @ColumnInfo(name = "nb_hits_with_time_generation") val nb_hits_with_time_generation: Int,
        @ColumnInfo(name = "nb_visits") val nb_visits: Int,
        @ColumnInfo(name = "sum_bandwidth") val sum_bandwidth: Int,
        @ColumnInfo(name = "sum_daily_entry_nb_uniq_visitors") val sum_daily_entry_nb_uniq_visitors: Int,
        @ColumnInfo(name = "sum_daily_exit_nb_uniq_visitors") val sum_daily_exit_nb_uniq_visitors: Int,
        @ColumnInfo(name = "sum_daily_nb_uniq_visitors") val sum_daily_nb_uniq_visitors: Int,
        @ColumnInfo(name = "sum_time_spent") val sum_time_spent: Int
    ): Parcelable
}