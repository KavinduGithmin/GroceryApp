package com.example.grocerryapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GrocerryViewModal(private val repository: GrocerryRepository) : ViewModel() {

    // In coroutines thread insert item in insert function.
    fun insert(item: GrocerryItems) = GlobalScope.launch {
        repository.insert(item)
    }

    // In coroutines thread delete item in delete function.
    fun delete(item: GrocerryItems) = GlobalScope.launch {
        repository.delete(item)
    }

    //Here we initialized allGroceryItems function with repository
    fun allGroceryItems() = repository.allGroceryItems()

}