package ru.chudakov.pages

import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidElement
import io.appium.java_client.pagefactory.AndroidFindBy

class SearchPage(driver: AndroidDriver<MobileElement>): AbstractPage(driver) {

    @AndroidFindBy(id = "com.twitter.android:id/moments")
    lateinit var searchBtn: AndroidElement

    @AndroidFindBy(id = "com.twitter.android:id/query_view")
    lateinit var searchTwitBtn: AndroidElement

    @AndroidFindBy(id = "com.twitter.android:id/query")
    lateinit var searchTwitInput: AndroidElement

    @AndroidFindBy(className = "android.widget.LinearLayout")
    lateinit var searchResult: AndroidElement

    @AndroidFindBy(className = "android.widget.ImageView")
    lateinit var settingBtn: AndroidElement

    @AndroidFindBy(id = "com.twitter.android:id/title")
    lateinit var title: AndroidElement

    @AndroidFindBy(id = "com.twitter.android:id/toolbar")
    lateinit var settingsToolbar: AndroidElement

    @AndroidFindBy(className = "android.widget.ImageButton")
    lateinit var closeBtn: AndroidElement
}
