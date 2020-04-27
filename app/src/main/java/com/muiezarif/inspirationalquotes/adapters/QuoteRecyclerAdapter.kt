package com.muiezarif.inspirationalquotes.adapters

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.muiezarif.inspirationalquotes.R
import com.muiezarif.inspirationalquotes.adapters.viewholders.MyQuoteViewHolder
import com.muiezarif.inspirationalquotes.databinding.CustomQuoteItemBinding
import com.muiezarif.inspirationalquotes.db.DbQueries
import com.muiezarif.inspirationalquotes.db.models.QuotesModel
import com.muiezarif.inspirationalquotes.interfaces.QuoteClickListener
import java.util.*


class QuoteRecyclerAdapter(
    var context: Context,
    var quoteList: ArrayList<QuotesModel>,
    var quoteClickListener: QuoteClickListener
) : RecyclerView.Adapter<MyQuoteViewHolder>() {
    private var colors: Array<Int> = arrayOf(
        R.color.colorCombo1,
        R.color.colorCombo2,
        R.color.colorCombo3,
        R.color.colorCombo4,
        R.color.colorCombo5,
        R.color.colorCombo6,
        R.color.colorCombo7,
        R.color.colorCombo8,
        R.color.colorCombo9,
        R.color.colorCombo10
    )
    val min = 0
    val max = 9

    var query: DbQueries = DbQueries(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyQuoteViewHolder {
        var binding: CustomQuoteItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.custom_quote_item,
            parent,
            false
        )
        return MyQuoteViewHolder(binding, quoteClickListener)
    }

    override fun getItemCount(): Int {
        return quoteList.size
    }

    override fun onBindViewHolder(holder: MyQuoteViewHolder, position: Int) {
        val model = quoteList[position]
        holder.binding.tvQuote.text = model.quote
        holder.binding.tvAuthor.text = "'" + model.author + "'"
        val random = Random().nextInt(max - min + 1) + min
        Log.i("COLOR", colors[random].toString())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            holder.binding.cvQuoteItem.setCardBackgroundColor(context.getColor(colors[random]))
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            holder.binding.cvQuoteItem.setCardBackgroundColor(context.resources.getColor(colors[random]))
        }
        if (model.fav == 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                holder.binding.ivFav.setBackgroundResource(R.drawable.ic_like)
            }else{
                val drawable = AppCompatResources.getDrawable(
                    context,
                    R.drawable.ic_like
                )
                holder.binding.ivFav.setImageDrawable(drawable)
            }
        } else if (model.fav == 1) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                holder.binding.ivFav.setBackgroundResource(R.drawable.ic_liked)
            }else{
                val drawable = AppCompatResources.getDrawable(
                    context,
                    R.drawable.ic_liked
                )
                holder.binding.ivFav.setImageDrawable(drawable)
            }
        }
        holder.binding.ivFav.setOnClickListener {
            if (model.fav == 0) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    holder.binding.ivFav.setBackgroundResource(R.drawable.ic_liked)
                }else{
                    val drawable = AppCompatResources.getDrawable(
                        context,
                        R.drawable.ic_liked
                    )
                    holder.binding.ivFav.setImageDrawable(drawable)
                }
                quoteList[position].fav=1
                query.updateQuoteFav(model.id,1)
            } else if (model.fav == 1) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    holder.binding.ivFav.setBackgroundResource(R.drawable.ic_like)
                }else{
                    val drawable = AppCompatResources.getDrawable(
                        context,
                        R.drawable.ic_like
                    )
                    holder.binding.ivFav.setImageDrawable(drawable)
                }
                quoteList[position].fav=0
                query.updateQuoteFav(model.id,0)
            }
        }
        holder.binding.ivShareQuote.setOnClickListener {
                val sharingIntent = Intent(Intent.ACTION_SEND)
                sharingIntent.type = "text/plain"
                val shareBody = model.quote
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "By 10000 Best Quotes")
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
                context.startActivity(Intent.createChooser(sharingIntent, "Share via"))

        }
    }

    fun filterList(filteredList: ArrayList<QuotesModel>) {
        quoteList = filteredList
        notifyDataSetChanged()
    }

    fun getList(): ArrayList<QuotesModel> {
        return quoteList
    }
}