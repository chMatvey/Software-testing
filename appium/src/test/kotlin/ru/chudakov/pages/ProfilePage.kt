package ru.chudakov.pages

import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidElement
import io.appium.java_client.pagefactory.AndroidFindBy

class ProfilePage(driver: AndroidDriver<MobileElement>): AbstractPage(driver) {

    @AndroidFindBy(id = "com.twitter.android:id/title")
    lateinit var title: AndroidElement

    @AndroidFindBy(id = "com.twitter.android:id/empty_title")
    lateinit var emptyTitle: AndroidElement

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[1]")
    lateinit var profileBtn: AndroidElement

    @AndroidFindBy(accessibility = "Tweets")
    lateinit var tweetsBtn: AndroidElement

    @AndroidFindBy(accessibility = "Tweets & replies")
    lateinit var tweetsAndRepliesBtn: AndroidElement

    @AndroidFindBy(accessibility = "Media")
    lateinit var mediaBtn: AndroidElement

    @AndroidFindBy(accessibility = "Likes")
    lateinit var likesBtn: AndroidElement
}
