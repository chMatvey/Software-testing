package ru.chudakov.page.menu.docs

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.WebDriverWait
import ru.chudakov.page.menu.AbstractMenu

abstract class AbstractDocsMenu(driver: WebDriver, wait: WebDriverWait) : AbstractMenu(driver, wait) {
    protected abstract var docsMenuButton: WebElement

    protected abstract var dropdown: WebElement

    protected open val xpathListOfDropdown: List<String> = emptyList()

    protected val action = Actions(driver)

    protected fun docsMenuButtonClick() {
        docsMenuButton.click()
        wait.until { dropdown.isDisplayed }
    }

    protected fun clickDropdownButton(dropDownButtonXpath: String) {
        docsMenuButtonClick()

        val button = dropdown.findElement(By.xpath(dropDownButtonXpath))
        wait.until { button.isDisplayed }
        button.click()
    }

    protected fun clickDropdownButton(dropDownButtonXpath: String, dialogXpath: String) {
        docsMenuButtonClick()

        val button = dropdown.findElement(By.xpath(dropDownButtonXpath))
        button.click()

        val dialog = driver.findElement(By.xpath(dialogXpath))
        wait.until { dialog.isDisplayed }

        action.sendKeys(Keys.ESCAPE).build().perform()
        //wait.until { !dialog.isDisplayed }
        Thread.sleep(500)
    }

    public open fun clickDropDowns() {
        docsMenuButtonClick()

        xpathListOfDropdown.forEach {
            val button = dropdown.findElement(By.xpath(it))
            button.click()
        }
        docsMenuButton.click()
    }
}
