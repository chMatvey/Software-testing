package ru.chudakov.pages

import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidElement
import io.appium.java_client.pagefactory.AndroidFindBy
import org.openqa.selenium.support.ui.WebDriverWait

class HomePage(driver: AndroidDriver<MobileElement>) : AbstractPage(driver) {

    @AndroidFindBy(id = "com.twitter.android:id/toolbar")
    lateinit var toolbar: AndroidElement

    @AndroidFindBy(id = "\tcom.twitter.android:id/tweet_curation_action")
    lateinit var tweetCurationAction: AndroidElement
}
