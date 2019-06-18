package ru.chudakov.pages

import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidElement
import io.appium.java_client.pagefactory.AndroidFindBy

class ListsPage(driver: AndroidDriver<MobileElement>): AbstractPage(driver) {

    @AndroidFindBy(id = "com.twitter.android:id/dock")
    lateinit var dock: AndroidElement

    @AndroidFindBy(id = "com.twitter.android:id/plus_fab")
    lateinit var createBtn: AndroidElement

    @AndroidFindBy(id = "com.twitter.android:id/name")
    lateinit var nameInput: AndroidElement

    @AndroidFindBy(id = "com.twitter.android:id/description")
    lateinit var descriptionInput: AndroidElement

    @AndroidFindBy(id = "com.twitter.android:id/save")
    lateinit var saveBtn: AndroidElement

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[2]")
    lateinit var listsBtn: AndroidElement
}
