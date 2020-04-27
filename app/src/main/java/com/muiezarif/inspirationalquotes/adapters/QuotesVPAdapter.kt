package com.muiezarif.inspirationalquotes.adapters

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.muiezarif.inspirationalquotes.R
import com.muiezarif.inspirationalquotes.databinding.CustomQuoteVpItemBinding
import com.muiezarif.inspirationalquotes.db.DbQueries
import com.muiezarif.inspirationalquotes.db.models.QuotesModel

import java.util.*
import kotlin.collections.ArrayList


class QuotesVPAdapter(var context: Context,var quotesList:ArrayList<QuotesModel>): PagerAdapter() {
    private var colors:Array<Int> = arrayOf(R.color.colorCombo1,
        R.color.colorCombo2,R.color.colorCombo3,R.color.colorCombo4,R.color.colorCombo5,R.color.colorCombo6,R.color.colorCombo7,R.color.colorCombo8,R.color.colorCombo9,R.color.colorCombo10)
    val min = 0
    val max = 9
    var query: DbQueries = DbQueries(context)

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (container as ViewPager).removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding: CustomQuoteVpItemBinding =DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.custom_quote_vp_item,container,false)
        val model=quotesList[position]
        binding.tvQuote.text=model.quote
        binding.tvAuthor.text=model.author
        binding.ivShareQuote.setOnClickListener {
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            val shareBody = model.quote
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "By 10000 Best Quotes")
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
            context.startActivity(Intent.createChooser(sharingIntent, "Share via"))
        }
        if (model.fav == 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                binding.ivFav.setBackgroundResource(R.drawable.ic_like)
            }else{
                val drawable = AppCompatResources.getDrawable(
                    context,
                    R.drawable.ic_like
                )
                binding.ivFav.setImageDrawable(drawable)
            }
        } else if (model.fav == 1) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                binding.ivFav.setBackgroundResource(R.drawable.ic_liked)
            }else{
                val drawable = AppCompatResources.getDrawable(
                    context,
                    R.drawable.ic_liked
                )
                binding.ivFav.setImageDrawable(drawable)
            }
        }
        binding.ivFav.setOnClickListener {
            if (model.fav == 0) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    binding.ivFav.setBackgroundResource(R.drawable.ic_liked)
                }else{
                    val drawable = AppCompatResources.getDrawable(
                        context,
                        R.drawable.ic_liked
                    )
                    binding.ivFav.setImageDrawable(drawable)
                }
                quotesList[position].fav=1
                query.updateQuoteFav(model.id,1)
            } else if (model.fav == 1) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    binding.ivFav.setBackgroundResource(R.drawable.ic_like)
                }else{
                    val drawable = AppCompatResources.getDrawable(
                        context,
                        R.drawable.ic_like
                    )
                    binding.ivFav.setImageDrawable(drawable)
                }
                quotesList[position].fav=0
                query.updateQuoteFav(model.id,0)
            }
        }
        val random = Random().nextInt(max - min + 1) + min
        Log.i("COLOR",colors[random].toString())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.clQuoteVpItem.setBackgroundColor(context.getColor(colors[random]))
        }
        container.addView(binding.root)
        return binding.root
    }
    override fun getCount(): Int {
        return quotesList.size
    }
}