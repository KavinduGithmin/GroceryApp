package com.example.grocerryapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.*

@Dao
interface GrocerryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: GrocerryItems)

    @Delete
    suspend fun delete(item: GrocerryItems)

    @Query("SELECT * FROM grocerry_items")
    fun getAllGrocerryItems(): LiveData<List<GrocerryItems>>

}
