package com.example.cryptoexchange.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoexchange.R
import com.example.cryptoexchange.data.Data

class CurrencyListAdapter(val clickListener: CurrencyListListener): RecyclerView.Adapter<CurrencyListAdapter.ViewHolder>() {


    var data = listOf<Data>()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.exchange_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]

        holder.cryptoTitle.text = item.name
        holder.cryptoCode.text = item.code
        holder.cryptoRate.text = item.rate.toString()

        holder.itemView.setOnClickListener {
            clickListener.onClickItem(item)
        }
        if(item.iconId != 0){
            holder.cryptoIcon.setImageResource(item.iconId)
        }else{
            holder.cryptoIcon.setImageResource(R.drawable.ic_baseline_monetization_on_24)
        }
        println("holder.cryptoIcon.setImageResource(item.iconId)... item.id = ${item.iconId}")


    /*
        when (item.code) {
            "BTC" -> holder.cryptoIcon.setImageResource(R.drawable.btc)
            "BCH" -> holder.cryptoIcon.setImageResource(R.drawable.bch)
            "ETH" -> holder.cryptoIcon.setImageResource(R.drawable.eth)
            "XRP" -> holder.cryptoIcon.setImageResource(R.drawable.xrp)
            "DOGE" -> holder.cryptoIcon.setImageResource(R.drawable.doge)
            else -> holder.cryptoIcon.setImageResource(R.drawable.ic_baseline_monetization_on_24)
        }
     */
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val cryptoTitle: TextView = itemView.findViewById(R.id.crypto_title)
        val cryptoCode: TextView = itemView.findViewById(R.id.crypto_code)
        val cryptoRate: TextView = itemView.findViewById(R.id.crypto_rate)
        val cryptoIcon: ImageView = itemView.findViewById(R.id.crypto_icon)
    }


}

class CurrencyListListener(val clickListener: (data: Data) -> Unit) {
    fun onClickItem(data: Data) = clickListener(data)
}