package ru.chudakov.page.menu.rightClickDropdown

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.WebDriverWait
import ru.chudakov.page.menu.AbstractMenu

class TextAreaRightButtonDropdown(driver: WebDriver, wait: WebDriverWait) : AbstractRightClickDropdown(driver, wait) {
    @FindBy(xpath = "//*[@id=\"workspace-container\"]")
    override lateinit var workSpace: WebElement

    @FindBy(xpath = "/html/body/div[14]")
    override lateinit var dropdown: WebElement

    private fun rightButtonClick(count: Int) {
        var rightClick = action.contextClick(workSpace)

        for (i in 1..count) {
            rightClick = action.sendKeys(Keys.ARROW_DOWN)
        }
        rightClick.sendKeys(Keys.RETURN).build().perform()
    }

    fun findByText() {
        //rightButtonClick(3)
        action.contextClick(workSpace).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
        val closeDialog = driver.findElement(By.xpath("/html/body/div[@class='docs-explore-sidebar docs-material']/div[1]/div[5]/div"))
        wait.until { closeDialog.isDisplayed }
        closeDialog.click()
    }

    fun alterText() {
        rightButtonClick(5)
        val dialog = driver.findElement(By.xpath("/html/body/div[@class='modal-dialog docs-dialog']"))
        wait.until { dialog.isDisplayed }
        action.sendKeys(Keys.ESCAPE).build().perform()
        Thread.sleep(500)
        action.sendKeys(Keys.ESCAPE).build().perform()
    }

    fun animation() {
        rightButtonClick(13)
        val closeDialog = driver.findElement(By.xpath("/html/body/div[@class='punch-animation-sidebar docs-material']/div[1]/div[3]/div"))
        wait.until { closeDialog.isDisplayed }
        closeDialog.click()
    }
}
