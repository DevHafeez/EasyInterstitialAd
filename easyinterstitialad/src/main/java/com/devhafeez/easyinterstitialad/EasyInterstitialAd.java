package com.devhafeez.easyinterstitialad;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class EasyInterstitialAd {

    private static boolean isLoading = false;
    private static boolean isLoaded = false;
    private static boolean isFailed = false;
    private static boolean isShown = false;
    private static boolean isShowing = false;
    private static InterstitialAd mInterstitialAd;
    private static Context context;
    public static boolean shouldShowInterAd = true;
    public static boolean isShowingInterstitial = false;
    static String adId;

    public interface MyInterAdListener {
        default void onAdLoading() {
        }

        default void onAdFailed(LoadAdError loadAdError) {
            isLoaded = false;
            isFailed = true;
        }

        default void onAdLoaded(InterstitialAd interstitialAd) {
            isLoaded = true;
            isFailed = false;
            isShown = false;
        }

        default void onAdClosed(boolean isSuccess) {
            isShowingInterstitial = false;
            isShowing = false;
            isShown = true;
        }
    }

    public static void init(Context context, String adId) {
        EasyInterstitialAd.context = context;
        EasyInterstitialAd.adId = adId;
        loadNextAd();
    }

    private static String TAG = "##myInterAdInfo";

    private static void loadNextAd() {
        if (!shouldShowInterAd) {
            isFailed = true;
            isLoading = false;
            isLoaded = false;
            return;
        }

        isLoading = true;
        if (context != null && context != context.getApplicationContext()) {
            context = context.getApplicationContext();
        }

        AdRequest adRequest = new AdRequest.Builder().build();
        Log.d("##myInterAdInfo", "Loading AD: " + System.currentTimeMillis());
        InterstitialAd.load(context, adId, adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {

                Log.i("##myInterAdInfo", "Ad Loaded: " + System.currentTimeMillis());
                mInterstitialAd = interstitialAd;
                isLoaded = true;
                isLoading = false;
                isFailed = false;
//                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
//                        @Override
//                        public void onAdDismissedFullScreenContent() {
//                            super.onAdDismissedFullScreenContent();
//                            loadNextAd();
//                        }
//                    });
                Log.i(TAG, "onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {

                Log.i("##myInterAdInfo", "AD Failed: " + System.currentTimeMillis());
                Log.d(TAG, loadAdError.toString());
                mInterstitialAd = null;
                isFailed = true;
                isLoading = false;
                isLoaded = false;
//                    loadNextAd();
            }
        });
//        } else {
//            Log.e(TAG, "Please use Application context for Ads!");
//        }
    }

    private static boolean isReady() {
        Log.e(TAG, "isReady called!");
        Log.e(TAG, "vars: \nad:" + mInterstitialAd + " isLoading:" + isLoading + " isFailed:" + isFailed + " isLoaded:" + isLoaded);
        return mInterstitialAd != null && !isLoading && !isFailed && isLoaded;
    }

    public static void show(Activity activity, Boolean shouldLoadNext, MyInterAdListener listener) {
        Log.i("##myInterAdInfo", "Show Function Called: " + System.currentTimeMillis());
        if (isReady()) {
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent();
                    if (listener != null) {
                        listener.onAdClosed(true);
                    }
                    if (shouldLoadNext) loadNextAd();
                    isShown = true;
                    isShowing = false;
                    isFailed = true;
                    isLoading = false;
                    isLoaded = false;
                }
            });

            mInterstitialAd.show(activity);

            Log.i("##myInterAdInfo", "Ad Shown: " + System.currentTimeMillis());
            isShowing = true;
            isShowingInterstitial = true;
        } else {
            Log.i("##myInterAdInfo", "Ad is Not ready: " + System.currentTimeMillis());
            listener.onAdClosed(false);
            isShowing = false;
            isShowingInterstitial = false;
            if (shouldLoadNext) loadNextAd();
        }
        isFailed = true;
        isLoading = false;
        isLoaded = false;
    }
}
