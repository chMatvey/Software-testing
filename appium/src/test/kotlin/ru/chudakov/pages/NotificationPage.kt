package ru.chudakov.pages

import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidElement
import io.appium.java_client.pagefactory.AndroidBy
import io.appium.java_client.pagefactory.AndroidFindAll
import io.appium.java_client.pagefactory.AndroidFindBy
import org.openqa.selenium.support.FindBy

class NotificationPage(driver: AndroidDriver<MobileElement>) : AbstractPage(driver) {

    @AndroidFindBy(id = "com.twitter.android:id/notifications")
    lateinit var notificationBtn: AndroidElement

    @AndroidFindBy(xpath = "//androidx.appcompat.app.a.c[@content-desc=\"Mentions\"]")
    lateinit var mentions: AndroidElement

    @AndroidFindBy(id = "com.twitter.android:id/toolbar_settings_notif")
    lateinit var settingsBtn: AndroidElement

    @AndroidFindBy(className = "android.widget.ImageButton")
    lateinit var backwardBtn: AndroidElement
}
