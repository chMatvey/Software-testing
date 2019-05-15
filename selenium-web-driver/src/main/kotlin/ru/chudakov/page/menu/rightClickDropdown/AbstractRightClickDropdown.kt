package ru.chudakov.page.menu.rightClickDropdown

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.WebDriverWait
import ru.chudakov.page.menu.AbstractMenu

abstract class AbstractRightClickDropdown(driver: WebDriver, wait: WebDriverWait) : AbstractMenu(driver, wait) {

    protected abstract var workSpace: WebElement

    protected abstract var dropdown: WebElement

    protected val action = Actions(driver)

    protected open fun rightButtonClick() {
        action.contextClick(workSpace).build().perform()
        wait.until { dropdown.isDisplayed }
    }

    override fun clickButton(buttonXpath: String, dialogXpath: String) {
        val button = dropdown.findElement(By.xpath(buttonXpath))
        button.click()

        val dialog = driver.findElement(By.xpath(dialogXpath))
        wait.until { dialog.isDisplayed }

        action.sendKeys(Keys.ESCAPE).build().perform()
        Thread.sleep(500)
    }
}
