package ru.chudakov.pages

import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidElement
import io.appium.java_client.pagefactory.AndroidFindBy

class MessagePage(driver: AndroidDriver<MobileElement>) : AbstractPage(driver) {

    @AndroidFindBy(id = "com.twitter.android:id/dms")
    lateinit var messageBtn: AndroidElement

    @AndroidFindBy(id = "com.twitter.android:id/composer_write")
    lateinit var createMessage: AndroidElement

    @AndroidFindBy(id = "com.twitter.android:id/screenname_item")
    lateinit var profile: AndroidElement

    @AndroidFindBy(id = "com.twitter.android:id/toolbar")
    lateinit var toolbar: AndroidElement

    @AndroidFindBy(id = "com.twitter.android:id/compose_next")
    lateinit var nextBtn: AndroidElement

    @AndroidFindBy(id = "com.twitter.android:id/tweet_text")
    lateinit var tweetText: AndroidElement

    @AndroidFindBy(id = "com.twitter.android:id/send_dm_button")
    lateinit var sendBtn: AndroidElement

    @AndroidFindBy(className = "android.widget.ImageButton")
    lateinit var backwardBtn: AndroidElement
}
