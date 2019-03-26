package ru.chudakov.page

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class PresentationPage(private val driver: WebDriver) : AbstractPage(driver) {
    override val pageUrl = "https://docs.google.com/presentation/d";

    @FindBy(xpath = "//*[@id=\"docs-menubar\"]")
    lateinit var docsMenu: WebElement

//    @FindBy(xpath = "//*[@id=\"docs-menubar\"]/div")
//    lateinit var docsMenuButtons: List<WebElement>

    fun getDocsMenuButtons(): List<WebElement> {
        return driver.findElements(By.xpath("//*[@id=\"docs-menubar\"]/div"))
    }

    fun getDocsMenuSelects(): List<WebElement> {
        val selects = driver.findElements(By.xpath("/html/body/div"))
        System.out.println(selects[32].getAttribute("class"))
        System.out.println(selects[33].getAttribute("class"))
        System.out.println(selects[34].getAttribute("class"))
        return selects
    }

}
