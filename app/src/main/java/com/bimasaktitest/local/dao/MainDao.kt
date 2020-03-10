package com.bimasaktitest.local.dao

import androidx.room.*
import com.bimasaktitest.main.MainModel

@Dao
interface MainDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: MainModel.Data)

    @Query("SELECT * FROM  local_data")
    fun getAll(): List<MainModel.Data>

    @Query("SELECT * FROM local_data WHERE id=:id")
    fun getById(id: Int): MainModel.Data?

    @Query("DELETE FROM local_data")
    fun deleteAll()

}