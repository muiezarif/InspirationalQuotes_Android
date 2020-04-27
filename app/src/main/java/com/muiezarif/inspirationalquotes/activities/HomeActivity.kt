package com.muiezarif.inspirationalquotes.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAdCallback
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.muiezarif.inspirationalquotes.R
import com.muiezarif.inspirationalquotes.databinding.ActivityHomeBinding
import com.muiezarif.inspirationalquotes.fragments.AuthorsFragment
import com.muiezarif.inspirationalquotes.fragments.FavouritesFragment
import com.muiezarif.inspirationalquotes.fragments.QuotesFragment
import com.muiezarif.inspirationalquotes.utils.AdHelper

class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityHomeBinding
    private var context: Context =this
    private var adWatchCount=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_home)
        init()
        binding.homeHeader.ivInfo.setOnClickListener {
            startActivity(Intent(this,InfoActivity::class.java))
        }
        binding.homeHeader.ivAds.setOnClickListener {
            val adCallback = object: RewardedAdCallback() {
                override fun onRewardedAdOpened() {
                    // Ad opened.
                }
                override fun onRewardedAdClosed() {
                    AdHelper.reloadRewardedAd(context,getString(R.string.admob_rewarded_ad))
                }
                override fun onUserEarnedReward(@NonNull reward: RewardItem) {
                    Toast.makeText(context,"Earned Reward", Toast.LENGTH_SHORT).show()
                    adWatchCount++
                    binding.homeHeader.tvRewwardAdCount.text=adWatchCount.toString()
                }
                override fun onRewardedAdFailedToShow(errorCode: Int) {
                    // Ad failed to display.
                }
            }
            AdHelper.showRewardedAd(this,adCallback)
        }
        AdHelper.loadRewardedAd(this,getString(R.string.admob_rewarded_ad))
    }

    private fun init(){
        binding.bnvNavigation.setOnNavigationItemSelectedListener(this)
        binding.homeHeader.ivInfo.visibility= View.VISIBLE
        binding.homeHeader.ivAds.visibility= View.VISIBLE
        binding.homeHeader.tvRewwardAdCount.visibility= View.VISIBLE
        binding.homeHeader.tvRewwardAdCount.text=adWatchCount.toString()
        val fragmentTransaction =supportFragmentManager.beginTransaction()
        fragmentTransaction.add(binding.flhome.id, QuotesFragment())
        fragmentTransaction.commit()
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.quotes_menu->{
                var fragmentTransaction =supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(binding.flhome.id, QuotesFragment()).commit()
            }
            R.id.fav_menu->{
                var fragmentTransaction =supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(binding.flhome.id, FavouritesFragment()).commit()
            }
            R.id.author_menu->{
                var fragmentTransaction =supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(binding.flhome.id, AuthorsFragment()).commit()
            }

        }
        return true
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
        AdHelper.reloadRewardedAd(this,getString(R.string.admob_rewarded_ad))
    }
}
