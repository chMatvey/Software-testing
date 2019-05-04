package ru.chudakov.page.menu.docs

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.WebDriverWait

class DocsArrangeMenu(driver: WebDriver, wait: WebDriverWait) : AbstractDocsMenu(driver, wait) {
    @FindBy(xpath = "//*[@id=\"sketchy-arrange-menu\"]")
    override lateinit var docsMenuButton: WebElement

    @FindBy(xpath = "/html/body/div[41]")
    override lateinit var dropdown: WebElement

    override val xpathListOfDropdown = listOf("div[1]", "div[5]")

    fun turnText() {
        clickDropdownButton("div[7]")

//        val turnHorizon = driver.findElement(By.xpath("/html/body/div[83]/div[4]"))
//        wait.until { turnHorizon.isDisplayed }
//        turnHorizon.click()
    }
}
