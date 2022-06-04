package ir.almasapps.recyclerviewswipeitems.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.almasapps.recyclerviewswipeitems.Model.Item
import ir.almasapps.recyclerviewswipeitems.R
import kotlinx.android.synthetic.main.item_layout.view.*

class MyAdapter (private var context: Context, private var itemList:List<Item>): RecyclerView.Adapter<MyAdapter.Holder>(){

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txtCartName : TextView
        var txtCartPrice : TextView
        var imgCard : ImageView

        init {
            txtCartName = itemView.cart_item_name
            txtCartPrice = itemView.cart_item_price
            imgCard = itemView.cart_image
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false)
        return Holder(itemView)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.txtCartName.text = itemList[position].name
        holder.txtCartPrice.text = itemList[position].price
        holder.imgCard.setImageResource(itemList[position].image)

    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}