package com.example.grocerryapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(),GrocerryRVAdapter.GrocerryItemClickInterface {

    lateinit var itemRV: RecyclerView
    lateinit var addFAB: FloatingActionButton
    lateinit var list: List<GrocerryItems>
    lateinit var grocerryRVAdapter: GrocerryRVAdapter
    lateinit var grocerryViewModal: GrocerryViewModal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        itemRV = findViewById(R.id.idRVItems)
        addFAB = findViewById(R.id.idFABAdd)
        list = ArrayList<GrocerryItems>()
        grocerryRVAdapter = GrocerryRVAdapter(list,this)
        itemRV.layoutManager = LinearLayoutManager(this)
        itemRV.adapter = grocerryRVAdapter
        val grocerryRepository = GrocerryRepository(GrocerryDatabase(this))
        val factory = GrocerryViewModalFactory(grocerryRepository)
        grocerryViewModal = ViewModelProvider(this, factory).get(GrocerryViewModal::class.java)
        grocerryViewModal.allGroceryItems().observe(this, Observer {
            grocerryRVAdapter.list = it
            grocerryRVAdapter.notifyDataSetChanged()
        })

        addFAB.setOnClickListener{
            openDialog()

        }





    }

    fun openDialog(){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.grocerry_add_dialog)
        val cancelBtn = dialog.findViewById<Button>(R.id.idBtnCancel)
        val addBtn = dialog.findViewById<Button>(R.id.idBtnAdd)
        val itemEdt = dialog.findViewById<Button>(R.id.idEdtItemName)
        val itemPriceEdt = dialog.findViewById<Button>(R.id.idEdtItemPrice)
        val itemQuantityEdt = dialog.findViewById<Button>(R.id.idEdtItemQuantity)
        cancelBtn.setOnClickListener{
            dialog.dismiss()
        }
        addBtn.setOnClickListener{
            val itemName : String = itemEdt.text.toString()
            val itemPrice : String = itemPriceEdt.text.toString()
            val itemQuantity : String = itemQuantityEdt.text.toString()
            val qty : Int = itemQuantity.toInt()
            val pr : Int = itemPrice.toInt()

            if(itemName.isNotEmpty() && itemPrice.isNotEmpty() && itemQuantity.isNotEmpty()){
                val items = GrocerryItems(itemName,qty,pr)
                grocerryViewModal.insert(items)
                Toast.makeText(applicationContext, "Item added successfully!", Toast.LENGTH_SHORT).show()
                grocerryRVAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }else{
                Toast.makeText(applicationContext, "Please enter all dat!", Toast.LENGTH_SHORT).show()
            }

        }
        dialog.show()

    }

    override fun onItemClick(grocerryItems: GrocerryItems) {
        grocerryViewModal.delete(grocerryItems)
        grocerryRVAdapter.notifyDataSetChanged()
        Toast.makeText(applicationContext, "Item Deleted Successfully!", Toast.LENGTH_SHORT).show()
    }
}

