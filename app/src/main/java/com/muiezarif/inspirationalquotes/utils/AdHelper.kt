package com.muiezarif.inspirationalquotes.utils

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.annotation.NonNull
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdCallback
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback


object AdHelper {
    lateinit var adRequestBanner:AdRequest
    lateinit var adRequestInterstitial:AdRequest
    lateinit var adRequestRewarded:AdRequest
    lateinit var mInterstitialAd: InterstitialAd
    lateinit var mRewardedAd:RewardedAd

    fun adRequestBanner(){
        adRequestBanner=AdRequest.Builder().build()
    }
    fun adRequestInterstitial(){
        adRequestInterstitial=AdRequest.Builder().build()
    }
    fun adRequestRewarded(){
        adRequestRewarded=AdRequest.Builder().build()
    }
    fun loadRewardedAd(context:Context,adUnitId: String){
        adRequestRewarded()
        mRewardedAd=RewardedAd(context,adUnitId)
        val adLoadCallback = object: RewardedAdLoadCallback() {
            override fun onRewardedAdFailedToLoad(p0: Int) {

            }

            override fun onRewardedAdLoaded() {
                super.onRewardedAdLoaded()
            }

        }
        mRewardedAd.loadAd(adRequestRewarded,adLoadCallback)

    }
    fun loadBannerAd(adView: AdView){
        adRequestBanner()
        adView.loadAd(adRequestBanner)
    }
    fun loadInterstitialAd(context:Context,adUnitId:String){
        adRequestInterstitial()
        mInterstitialAd = InterstitialAd(context)
        mInterstitialAd.adUnitId = adUnitId
        mInterstitialAd.loadAd(adRequestInterstitial)
        mInterstitialAd.adListener= object : AdListener() {
            override fun onAdImpression() {
                super.onAdImpression()
            }

            override fun onAdLeftApplication() {
                super.onAdLeftApplication()
            }

            override fun onAdClicked() {
                super.onAdClicked()
            }

            override fun onAdFailedToLoad(p0: Int) {
                super.onAdFailedToLoad(p0)
                adRequestInterstitial()
                mInterstitialAd.loadAd(adRequestInterstitial)
            }

            override fun onAdClosed() {
                super.onAdClosed()
                adRequestInterstitial()
                mInterstitialAd.loadAd(adRequestInterstitial)
            }

            override fun onAdOpened() {
                super.onAdOpened()
            }

            override fun onAdLoaded() {
                super.onAdLoaded()
            }
        }
    }
    fun reloadInterstitialAd(){
        adRequestInterstitial()
        mInterstitialAd.loadAd(adRequestInterstitial)
    }

    fun showInterstitialAd(){
        if (mInterstitialAd != null && mInterstitialAd.isLoaded) {
            mInterstitialAd.show()
        }
    }

    fun reloadRewardedAd(context: Context,adUnitId: String){
        adRequestRewarded()
        mRewardedAd=RewardedAd(context,adUnitId)
        val adLoadCallback = object: RewardedAdLoadCallback() {
            override fun onRewardedAdFailedToLoad(p0: Int) {

            }

            override fun onRewardedAdLoaded() {
                super.onRewardedAdLoaded()
            }

        }
        mRewardedAd.loadAd(adRequestRewarded,adLoadCallback)
    }

    fun showRewardedAd(context: Context,adCallback: RewardedAdCallback){
        if (mRewardedAd.isLoaded) {

            mRewardedAd.show(context as Activity, adCallback)
        }
        else {
            Log.d("TAG", "The rewarded ad wasn't loaded yet.")
        }
    }
}