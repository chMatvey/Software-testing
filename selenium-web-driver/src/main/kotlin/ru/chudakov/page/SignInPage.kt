package ru.chudakov.page

import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory

class SignInPage(private val driver: WebDriver) : AbstractPage(driver) {
    override val pageUrl = "https://accounts.google.com/signin/v2/identifier"
}
