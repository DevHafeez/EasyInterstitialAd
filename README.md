# EasyInterstitialAd
EasyInterstitialAd is a simple Android library for managing interstitial ads with ease. It provides a convenient way to load and display interstitial ads in your Android app.

## Implementation

### Step 1: Add the EasyInterstitialAd Library to Your Project

To use EasyInterstitialAd in your Android project, you need to add the library as a dependency. You can do this by adding the following dependency to your app-level `build.gradle` file:

```gradle
implementation 'com.devhafeez:easyinterstitialad:1.0.0'
```
Make sure to sync your project to fetch the library.

##Step 2: Initialize the EasyInterstitialAd
You should initialize EasyInterstitialAd in your application's code, typically in the onCreate method of your Application class or in your Activity.
```groovy
// Initialize EasyInterstitialAd with your context and ad ID
EasyInterstitialAd.init(this, "YOUR_AD_ID");
```
### Usage in Kotlin
```groovy
// Show an interstitial ad
EasyInterstitialAd.show(this, true, object : EasyInterstitialAd.MyInterAdListener {
    override fun onAdClosed(isSuccess: Boolean) {
        // Handle ad closed event
    }
})
```
### Usage in Java
```groovy
// Show an interstitial ad
EasyInterstitialAd.show(this, true, new EasyInterstitialAd.MyInterAdListener() {
    @Override
    public void onAdClosed(boolean isSuccess) {
        // Handle ad closed event
    }
});
```
### Configuration
You can control whether to show or hide interstitial ads by updating the shouldShowInterAd variable in the EasyInterstitialAd class. Set it to true if you want to show interstitial ads or false to hide them.
```groovy
EasyInterstitialAd.shouldShowInterAd = true; // Show interstitial ads
```
### Callbacks
EasyInterstitialAd provides callbacks to handle various events:
```groovy
onAdLoading(): Called when an ad is being loaded.
onAdFailed(LoadAdError loadAdError): Called when ad loading fails with the provided error.
onAdLoaded(InterstitialAd interstitialAd): Called when an ad is successfully loaded.
onAdClosed(boolean isSuccess): Called when the ad is closed. The isSuccess parameter indicates whether the ad was closed successfully.
These callbacks can be implemented by overriding the respective methods in the MyInterAdListener interface when calling EasyInterstitialAd.show.
```
```groovy
EasyInterstitialAd.show(this, true, new EasyInterstitialAd.MyInterAdListener() {
    @Override
    public void onAdLoading() {
        // Handle ad loading
    }

    @Override
    public void onAdFailed(LoadAdError loadAdError) {
        // Handle ad loading failure
    }

    @Override
    public void onAdLoaded(InterstitialAd interstitialAd) {
        // Handle ad loaded
    }

    @Override
    public void onAdClosed(boolean isSuccess) {
        // Handle ad closed
    }
});
```
That's it! You've successfully integrated EasyInterstitialAd into your Android app, and you can now easily manage interstitial ads.
