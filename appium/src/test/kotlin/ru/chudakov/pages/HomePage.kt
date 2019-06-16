package ru.chudakov.pages

import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidElement
import io.appium.java_client.pagefactory.AndroidFindBy

class HomePage(driver: AndroidDriver<MobileElement>) : AbstractPage(driver) {

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Home Tab\"]")
    lateinit var view: AndroidElement

    @AndroidFindBy(id = "com.twitter.android:id/toolbar")
    lateinit var toolbar: AndroidElement

    @AndroidFindBy(id = "com.twitter.android:id/tweet_curation_action")
    lateinit var tweetCurationAction: AndroidElement

    @AndroidFindBy(id = "com.twitter.android:id/gripper")
    lateinit var gripper: AndroidElement

    @AndroidFindBy(id = "com.twitter.android:id/toolbar_timeline_switch")
    lateinit var timelineSwitch: AndroidElement

    @AndroidFindBy(className = "android.widget.ImageButton")
    lateinit var imageButton: AndroidElement

    @AndroidFindBy(id = "com.twitter.android:id/drawer")
    lateinit var drawer: AndroidElement
}
