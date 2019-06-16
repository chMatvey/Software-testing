package ru.chudakov.pages

import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidElement
import io.appium.java_client.pagefactory.AndroidFindBy

class CreateTwitPage(driver: AndroidDriver<MobileElement>) : AbstractPage(driver) {

    @AndroidFindBy(id = "com.twitter.android:id/tweet_storm_recycler_view")
    lateinit var textArea: AndroidElement

    @AndroidFindBy(className = "android.widget.ImageView")
    lateinit var imageView: AndroidElement

    @AndroidFindBy(className = "android.widget.FrameLayout")
    lateinit var accountFrame: AndroidElement

    @AndroidFindBy(id = "com.twitter.android:id/tweet_text")
    lateinit var textInput: AndroidElement

    @AndroidFindBy(id = "com.twitter.android:id/found_media")
    lateinit var gifBtn: AndroidElement

    @AndroidFindBy(className = "android.view.View")
    lateinit var gifView: AndroidElement

    @AndroidFindBy(className = "android.widget.ImageView")
    lateinit var gifImageView: AndroidElement

    @AndroidFindBy(id = "com.twitter.android:id/button_tweet")
    lateinit var twitButton: AndroidElement
}
