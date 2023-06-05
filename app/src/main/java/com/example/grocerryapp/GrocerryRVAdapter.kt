package com.example.grocerryapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GrocerryRVAdapter
    (var list: List<GrocerryItems>,
     val grocerryItemClickInterface: GrocerryItemClickInterface) :
    RecyclerView.Adapter<GrocerryRVAdapter.GrocerryViewHolder>() {

    inner class GrocerryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nameTV = itemView.findViewById<TextView>(R.id.idTVItemName)
        val quantityTV = itemView.findViewById<TextView>(R.id.idTVQuantity)
        val rateTV = itemView.findViewById<TextView>(R.id.idTVRate)
        val amountTV = itemView.findViewById<TextView>(R.id.idTVTotalAmt)
        val deleteTV = itemView.findViewById<ImageView>(R.id.idTVDelete)

    }




    interface GrocerryItemClickInterface{
        fun onItemClick(grocerryItems: GrocerryItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GrocerryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grocerry_rv_item,parent,false)
        return GrocerryViewHolder(view)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: GrocerryViewHolder, position: Int) {
        holder.nameTV.text = list.get(position).itemName
        holder.quantityTV.text = list.get(position).itemName.toString()
        holder.rateTV.text = "Rs. " + list.get(position).itemName.toString()
        val itemTotal : Int = list.get(position).itemPrice * list.get(position).itemQuantity
        holder.amountTV.text = "Rs. " + itemTotal.toString()
        holder.deleteTV.setOnClickListener{
            grocerryItemClickInterface.onItemClick((list.get(position)))
        }

    }
}