package ru.chudakov.pages

import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.pagefactory.AppiumFieldDecorator
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.WebDriverWait

abstract class AbstractPage(private val driver: AndroidDriver<MobileElement>) {

    init {
        initElements()
    }

    private fun initElements() {
        PageFactory.initElements(AppiumFieldDecorator(driver), this)
    }
}
