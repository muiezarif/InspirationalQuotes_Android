package com.muiezarif.inspirationalquotes.activities

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.muiezarif.inspirationalquotes.R
import com.muiezarif.inspirationalquotes.databinding.ActivityInfoBinding
import com.muiezarif.inspirationalquotes.utils.AdHelper

class InfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInfoBinding
    private var context=this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_info)
        AdHelper.loadBannerAd(binding.adView)
        binding.infoHeader.ivBack.visibility= View.VISIBLE
        binding.infoHeader.tvHomeTitle.text="Info"
        binding.infoHeader.ivBack.setOnClickListener {
            finish()
        }
        binding.ivShare.setOnClickListener {
            share()
        }

        binding.ivRateApp.setOnClickListener {
            rate()
        }
    }

    private fun share(){
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        val shareBody = "http://play.google.com/store/apps/details?id=" + context.packageName
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here")
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
        startActivity(Intent.createChooser(sharingIntent, "Share via"))
    }

    private fun rate(){
        val uri: Uri = Uri.parse("market://details?id=" + context.packageName)
        val goToMarket = Intent(Intent.ACTION_VIEW, uri)
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(
            Intent.FLAG_ACTIVITY_NO_HISTORY or
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK
        )
        try {
            startActivity(goToMarket)
        } catch (e: ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + context.packageName)
                )
            )
        }
    }
}
