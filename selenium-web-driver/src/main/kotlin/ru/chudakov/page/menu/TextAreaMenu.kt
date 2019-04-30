package ru.chudakov.page.menu

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.WebDriverWait
import javax.xml.xpath.XPath

class TextAreaMenu(driver: WebDriver, wait: WebDriverWait) : AbstractMenu(driver, wait)  {

    @FindBy(xpath = "//*[@id=\"workspace-container\"]")
    lateinit var workSpace: WebElement

    @FindBy(xpath = "//*[@id=\"fillColorMenuButton\"]")
    lateinit var fillColorMenuButton: WebElement

    @FindBy(xpath = "//*[@id=\"lineColorMenuButton\"]")
    lateinit var lineColorMenuButton: WebElement

    @FindBy(xpath = "//*[@id=\"lineWidthMenuButton\"]")
    lateinit var lineWidthMenuButton: WebElement

    @FindBy(xpath = "//*[@id=\"lineDashingMenuButton\"]")
    lateinit var lineDashingMenuButton: WebElement

    @FindBy(xpath = "//*[@id=\"docs-font-family\"]")
    lateinit var docsFontFamilyButton: WebElement

    fun inputText() {
        val textArea = driver.findElement(By.xpath("//div[@id=\"pages\"]/*[local-name()='svg'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][3]"))
        textArea.sendKeys("qwerty")
    }

    fun fillColorMenuButtonClick() {
        val elementsXpath = "/html/body/div[@class='goog-menu goog-menu-vertical docs-material docs-colormenuitems goog-menu-noaccel sketchy-gradientmenuitems']/div[4]/table/tbody/tr/td"

        clickMenuButtons(fillColorMenuButton, elementsXpath)
    }

    fun lineColorMenuButtonClick() {
        val elementsXpath = "/html/body/div[@class='goog-menu goog-menu-vertical docs-material docs-colormenuitems goog-menu-noaccel']/div[3]/table/tbody/tr/td"

        clickMenuButtons(lineColorMenuButton, elementsXpath)
    }

    fun lineWidthMenuButtonClick() {
        val elementsXpath = "/html/body/div[@class='goog-menu goog-menu-vertical docs-material goog-menu-noaccel'][2]/div"

        clickMenuButtons(lineWidthMenuButton, elementsXpath)
    }

    fun lineDashingMenuButtonClick() {
        val elementsXpath = "/html/body/div[@class='goog-menu goog-menu-vertical docs-material goog-menu-noaccel'][2]/div"

        clickMenuButtons(lineDashingMenuButton, elementsXpath)
    }

    fun docsFontFamilyButtonClick() {
        workSpace.click()

        val elementsXpath = ""
    }

    private fun clickMenuButtons(menuButton: WebElement, elementsXpath: String) {
        workSpace.click()
        wait.until { menuButton.isDisplayed }

        menuButton.click()

        val elements = driver.findElements(By.xpath(elementsXpath))
        wait.until { elements.first().isDisplayed }
        elements.forEach {
            it.click()
            menuButton.click()
            wait.until { elements.first().isDisplayed }
        }
    }
}
