package com.eslirodrigues.tutorials.admob.ui.components

import android.app.Activity
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.viewinterop.AndroidView
import com.eslirodrigues.tutorials.R
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

@Composable
fun AdMobBanner() {
    val currentWidth = LocalConfiguration.current.screenWidthDp
    AndroidView(
        factory = {
            AdView(it).apply {
                setAdSize(AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, currentWidth))
                adUnitId = context.getString(R.string.ad_unit_id)
                loadAd(AdRequest.Builder().build())
            }
        }
    )
}

class AdMobInterstitial(private val activity: Activity) {
    var mInterstitialAd: InterstitialAd? = null
    private val context = activity.applicationContext
    fun loadAd() {
        val adUnitId = context.getString(R.string.interstitial_ad_unit_id)
        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(context, adUnitId, adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(p0: LoadAdError) {
                mInterstitialAd = null
            }

            override fun onAdLoaded(ad: InterstitialAd) {
                mInterstitialAd = ad
                Toast.makeText(context, "add loaded", Toast.LENGTH_SHORT).show()
            }
        })
    }
    fun showAdd() {
        if (mInterstitialAd != null) {
            mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent()
                    mInterstitialAd = null
                    loadAd()
                    Toast.makeText(context, "dismissed", Toast.LENGTH_SHORT).show()
                }

                override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                    super.onAdFailedToShowFullScreenContent(p0)
                    mInterstitialAd = null
                    Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show()
                }

                override fun onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent()
                    Toast.makeText(context, "showed", Toast.LENGTH_SHORT).show()
                }
            }
            mInterstitialAd?.show(activity)
        } else {
            loadAd()
        }
    }
}