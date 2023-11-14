package com.benjamin.shoppingapp

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.shopping_item.view.*
class MyAdapter(
    var blist: ArrayList<Product>,
    val imageList: List<Drawable?>,
    val iconList: List<Drawable?>
) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    var onItemClick: ((Product) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val product = blist[position]
        holder.bind(product, imageList[position], iconList[position])
    }

    override fun getItemCount(): Int {
        return blist.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var itemPosition: Int = -1

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(product: Product, imageDrawable: Drawable?, iconDrawable: Drawable?) {
            itemPosition = adapterPosition
            itemView.productName.text = product.productName
            itemView.productCost.text = "$ ${product.cost}"
            itemView.productDescription.text = product.productDescription

            itemView.productImage.setImageDrawable(imageDrawable)
            itemView.productLogo.setImageDrawable(iconDrawable)
        }

        override fun onClick(v: View?) {
            onItemClick?.invoke(blist[itemPosition])
        }
    }
}