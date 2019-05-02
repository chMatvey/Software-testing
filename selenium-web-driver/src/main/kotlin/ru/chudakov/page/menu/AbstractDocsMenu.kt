package ru.chudakov.page.menu

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.WebDriverWait

abstract class AbstractDocsMenu(driver: WebDriver, wait: WebDriverWait) : AbstractMenu(driver, wait) {
    protected abstract var docsMenuButton: WebElement

    //protected abstract val dropdownXpath: String

    protected abstract var dropdown: WebElement

    protected val action = Actions(driver)

    protected fun clickDropdownButton(dropDownButtonXpath: String, dialogXpath: String) {
        docsMenuButton.click()
        wait.until { dropdown.isDisplayed }

        val button = dropdown.findElement(By.xpath(dropDownButtonXpath))
        button.click()

        val dialog = driver.findElement(By.xpath(dialogXpath))
        wait.until { dialog.isDisplayed }

        action.sendKeys(Keys.ESCAPE).build().perform()
        Thread.sleep(500)
    }
}
