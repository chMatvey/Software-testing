package ru.chudakov.page.menu

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

abstract class AbstractMenu(driver: WebDriver, xpathElements: Map<String, String>) {
    val elements: Map<WebElement, WebElement>

    init {
        xpathElements.forEach { key, value ->  elements.plus()}
    }
}
