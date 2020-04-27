package com.muiezarif.inspirationalquotes.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.muiezarif.inspirationalquotes.R
import com.muiezarif.inspirationalquotes.activities.FullQuoteViewActivity
import com.muiezarif.inspirationalquotes.adapters.QuoteRecyclerAdapter
import com.muiezarif.inspirationalquotes.databinding.CustomQuoteItemBinding
import com.muiezarif.inspirationalquotes.databinding.FragmentFavouritesBinding
import com.muiezarif.inspirationalquotes.db.DbQueries
import com.muiezarif.inspirationalquotes.db.models.QuotesModel
import com.muiezarif.inspirationalquotes.interfaces.QuoteClickListener
import com.muiezarif.inspirationalquotes.utils.AdHelper

/**
 * A simple [Fragment] subclass.
 */
class FavouritesFragment : Fragment(), QuoteClickListener {
    private lateinit var binding: FragmentFavouritesBinding
    private lateinit var dbQuery: DbQueries
    private lateinit var quoteList:ArrayList<QuotesModel>
    private var adapter: QuoteRecyclerAdapter?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.fragment_favourites, container, false)
        AdHelper.loadBannerAd(binding.adView)
        init()
        return binding.root
    }

    private fun init(){
        dbQuery= DbQueries(context)
        quoteList=dbQuery.getFavQuotes()
        quoteList.shuffle()
        adapter= context?.let { QuoteRecyclerAdapter(it,quoteList,this) }
        binding.rvQuotes.adapter=adapter

    }

    override fun onClick(view: CustomQuoteItemBinding?, position: Int) {
        var intent= Intent(context, FullQuoteViewActivity::class.java)
        intent.putExtra("quotePos",position)
        intent.putExtra("list",adapter?.getList())
        intent.putExtra("from","listQuotes")
        startActivity(intent)
    }

}
