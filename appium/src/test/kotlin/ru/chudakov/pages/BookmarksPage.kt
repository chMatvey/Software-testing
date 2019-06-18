package ru.chudakov.pages

import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidElement
import io.appium.java_client.pagefactory.AndroidFindBy

class BookmarksPage(driver: AndroidDriver<MobileElement>) : AbstractPage(driver) {

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[3]")
    lateinit var bookmarksBtn: AndroidElement

    @AndroidFindBy(accessibility = "More options")
    lateinit var moreOptionBtn: AndroidElement

    @AndroidFindBy(id = "com.twitter.android:id/title")
    lateinit var deleteAllBtn: AndroidElement

    @AndroidFindBy(id = "android:id/button1")
    lateinit var approvalBtn: AndroidElement
}
