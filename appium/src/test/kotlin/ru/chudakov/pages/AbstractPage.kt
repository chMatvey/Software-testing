package ru.chudakov.pages

import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.pagefactory.AppiumFieldDecorator
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.WebDriverWait

abstract class AbstractPage(protected val driver: AndroidDriver<MobileElement>, protected val wait: WebDriverWait) {

    init {
        initElements()
    }

    private fun initElements() {
        PageFactory.initElements(AppiumFieldDecorator(driver), this)
    }
}
