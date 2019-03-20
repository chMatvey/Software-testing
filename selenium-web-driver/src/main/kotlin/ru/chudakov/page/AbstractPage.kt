package ru.chudakov.page

import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.WebDriverWait

abstract class AbstractPage(private val driver: WebDriver) {
    abstract val pageUrl: String

    init {
        initElements();
    }

    private fun initElements() {
        PageFactory.initElements(driver, this)
    }

//    fun verifyUrl() {
//        WebDriverWait(driver, 10).until { it.currentUrl == pageUrl }
//    }
}
