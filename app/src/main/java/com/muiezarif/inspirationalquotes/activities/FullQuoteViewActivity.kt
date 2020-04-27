package com.muiezarif.inspirationalquotes.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.muiezarif.inspirationalquotes.R
import com.muiezarif.inspirationalquotes.adapters.QuotesVPAdapter
import com.muiezarif.inspirationalquotes.databinding.ActivityFullQuoteViewBinding
import com.muiezarif.inspirationalquotes.db.DbQueries
import com.muiezarif.inspirationalquotes.db.models.QuotesModel
import com.muiezarif.inspirationalquotes.fragments.QuotesFragment
import com.muiezarif.inspirationalquotes.utils.AdHelper

class FullQuoteViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFullQuoteViewBinding
    private lateinit var dbQuery: DbQueries
    private lateinit var quoteList:ArrayList<QuotesModel>
    private var adapter: QuotesVPAdapter?=null
    private var position=-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_full_quote_view)
        Log.i("TESTING","InsideFullQUOTE 4")
        binding.fullQuoteHeader.ivBack.visibility= View.VISIBLE
        binding.fullQuoteHeader.tvHomeTitle.text= "Quotes"
        binding.fullQuoteHeader.ivBack.setOnClickListener {
            finish()
        }
        if(intent.hasExtra("from")){
            if (intent.extras?.getString("from").equals("quotesFrag")){
                position= intent?.extras?.getInt("quotePos",0)!!
                quoteList= QuotesFragment.awesomelist!!
                adapter=  QuotesVPAdapter(this,quoteList)
                binding.vpQuotes.adapter=adapter
                binding.vpQuotes.currentItem = position
            }else if(intent.extras?.getString("from").equals("listQuotes")){
                position= intent.extras?.getInt("quotePos",0)!!
                quoteList= intent.getSerializableExtra("list") as ArrayList<QuotesModel>
                adapter=  QuotesVPAdapter(this,quoteList)
                binding.vpQuotes.adapter=adapter
                binding.vpQuotes.currentItem = position
            }
        }
        AdHelper.loadBannerAd(binding.adView)


    }
}
