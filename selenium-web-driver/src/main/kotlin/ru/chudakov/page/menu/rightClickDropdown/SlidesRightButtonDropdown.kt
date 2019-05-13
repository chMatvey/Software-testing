package ru.chudakov.page.menu.rightClickDropdown

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.WebDriverWait
import ru.chudakov.page.menu.AbstractMenu

class SlidesRightButtonDropdown (driver: WebDriver, wait: WebDriverWait) : AbstractRightClickDropdown(driver, wait) {

    @FindBy(xpath = "//*[@id=\"filmstrip\"]/div")
    override lateinit var workSpace: WebElement

    @FindBy(xpath = "/html/body/div[@class='goog-menu goog-menu-vertical docs-material'][1]")
    override lateinit var dropdown: WebElement

    fun cut() {
        rightButtonClick()
        val button = dropdown.findElement(By.xpath("div[4]"))
        button.click()
        val dialog = driver.findElement(By.xpath("/html/body/div[@class='modal-dialog docs-dialog']"))
        wait.until { dialog.isDisplayed }
        action.sendKeys(Keys.ESCAPE).build().perform()
        Thread.sleep(500)
    }

    fun copy() {
        rightButtonClick()
        val button = dropdown.findElement(By.xpath("div[5]"))
        button.click()
        val dialog = driver.findElement(By.xpath("/html/body/div[@class='modal-dialog docs-dialog']"))
        wait.until { dialog.isDisplayed }
        action.sendKeys(Keys.ESCAPE).build().perform()
        Thread.sleep(500)
    }
}
