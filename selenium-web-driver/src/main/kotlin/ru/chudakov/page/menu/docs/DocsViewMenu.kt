package ru.chudakov.page.menu.docs

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.WebDriverWait

class DocsViewMenu(driver: WebDriver, wait: WebDriverWait) : AbstractDocsMenu(driver, wait) {
    @FindBy(xpath = "//*[@id=\"docs-view-menu\"]")
    override lateinit var docsMenuButton: WebElement

    @FindBy(xpath = "/html/body/div[37]")
    override lateinit var dropdown: WebElement

    override val xpathListOfDropdown = listOf("div[8]", "div[10]", "div[11]")

    fun watch() {
        docsMenuButtonClick()

        val watch = dropdown.findElement(By.xpath("div[1]"))
        watch.click()

        val dialogXpath = "/html/body/div[@class='punch-full-screen-element punch-full-window-overlay']"
        wait.until { driver.findElements(By.xpath(dialogXpath)).isNotEmpty() }
        Thread.sleep(2000)

        action.sendKeys(Keys.ESCAPE).build().perform()
        wait.until { driver.findElements(By.xpath(dialogXpath)).isEmpty() }
    }

    fun animation() {
        docsMenuButtonClick()

        val animation = dropdown.findElement(By.xpath("div[3]"))
        animation.click()

        val close = driver.findElement(By.xpath("/html/body/div[@class='punch-animation-sidebar docs-material']/div[1]/div[3]/div"))
        wait.until { close.isDisplayed }
        close.click()
        wait.until { !close.isDisplayed }
    }

    fun changeView() {
        clickDropdownButton("div[5]")
        clickDropdownButton("div[6]")
        clickDropdownButton("div[6]")

        clickDropdownButton("div[9]")
        clickDropdownButton("div[9]")

        clickDropdownButton("div[13]")
        clickDropdownButton("div[13]")

        clickDropdownButton("div[15]")
        Thread.sleep(1000)
        action.sendKeys(Keys.ESCAPE).build().perform()
        Thread.sleep(500)
    }
}
