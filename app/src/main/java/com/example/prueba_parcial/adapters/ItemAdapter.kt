package com.example.prueba_parcial.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.prueba_parcial.R
import com.example.prueba_parcial.models.Item
import com.squareup.picasso.Picasso

class ItemAdapter (private val item: List<Item>,  private val clickListener: OnItemClickListener): Adapter<ItemPrototype>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemPrototype {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.prototype_item,parent,false)
        return ItemPrototype(view)
    }

    override fun onBindViewHolder(holder: ItemPrototype, position: Int) {
        holder.bind(item[position],clickListener)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    interface OnItemClickListener {
        fun onItemClick(item: Item)
    }

}

class ItemPrototype (itemView: View):RecyclerView.ViewHolder(itemView) {
    val ivItem=itemView.findViewById<ImageView>(R.id.ivItem)
    val tvName=itemView.findViewById<TextView>(R.id.tvName)
    val tvDescription=itemView.findViewById<TextView>(R.id.tvDescription)
    val ibFavorite=itemView.findViewById<ImageButton>(R.id.ibFavorite)

    fun bind(item: Item, clickListener: ItemAdapter.OnItemClickListener){
        tvName.text=item.name
        tvDescription.text=item.description


        Picasso.get()
            .load(item.image)
            .resize(100, 100)
            .into(ivItem)

        ibFavorite.setOnClickListener{
           clickListener.onItemClick(item)
        }
    }
}
