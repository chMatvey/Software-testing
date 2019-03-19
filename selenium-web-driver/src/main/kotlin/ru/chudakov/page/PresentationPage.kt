package ru.chudakov.page

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class PresentationPage(private val driver: WebDriver) : AbstractPage(driver) {
    override val pageUrl = "https://docs.google.com/presentation/d/1jHmtJrjlENMu0j_ncd4b9BkjkTQfFoHdY8gpN6RpZRM/";

    @FindBy(id = "docs-menubars")
    lateinit var menu: WebElement
}
