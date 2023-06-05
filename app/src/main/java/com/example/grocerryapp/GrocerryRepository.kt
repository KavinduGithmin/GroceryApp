package com.example.grocerryapp


class GrocerryRepository(private val db: GrocerryDatabase) {

    suspend fun insert(item: GrocerryItems) = db.getGrocerryDao().insert(item)
    suspend fun delete(item: GrocerryItems) = db.getGrocerryDao().delete(item)

    fun allGroceryItems() = db.getGrocerryDao().getAllGrocerryItems()

}