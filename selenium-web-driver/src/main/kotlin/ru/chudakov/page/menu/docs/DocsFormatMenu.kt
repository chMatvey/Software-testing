package ru.chudakov.page.menu.docs

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.WebDriverWait

class DocsFormatMenu(driver: WebDriver, wait: WebDriverWait) : AbstractDocsMenu(driver, wait) {
    @FindBy(xpath = "//*[@id=\"docs-format-menu\"]")
    override lateinit var docsMenuButton: WebElement

    @FindBy(xpath = "/html/body/div[39]")
    override lateinit var dropdown: WebElement

    override val xpathListOfDropdown = listOf("div[4]", "div[5]", "div[6]", "div[7]", "div[13]")

    fun formatParams() {
        clickDropdownButton("div[15]")

        val dialog = driver.findElement(By.xpath("/html/body/div[@class='docs-tiled-sidebar docs-material']"))
        wait.until { dialog.isDisplayed }

        val alignText = dialog.findElement(By.xpath("div[2]/div/div[3]/div[1]"))
        alignText.click()

        val leftMargin = dialog.findElement(By.xpath("div[2]/div/div[3]/div[2]/div/div[1]/div[2]/div[1]/div/div[2]/input"))
        wait.until { leftMargin.isDisplayed }
        leftMargin.sendKeys("5")

        val rightMargin = dialog.findElement(By.xpath("div[2]/div/div[3]/div[2]/div/div[1]/div[2]/div[2]/div/div[2]/input"))
        rightMargin.sendKeys("5")

        val topField = dialog.findElement(By.xpath("div[2]/div/div[3]/div[2]/div/div[2]/div[2]/div[1]/div/div[2]/input"))
        topField.sendKeys("5")

        val downField = dialog.findElement(By.xpath("div[2]/div/div[3]/div[2]/div/div[2]/div[2]/div[2]/div/div[2]/input"))
        downField.sendKeys("5")

        val leftField = dialog.findElement(By.xpath("div[2]/div/div[3]/div[2]/div/div[2]/div[2]/div[3]/div/div[2]/input"))
        leftField.sendKeys("5")

        val rightFiled = dialog.findElement(By.xpath("div[2]/div/div[3]/div[2]/div/div[2]/div[2]/div[4]/div/div[2]/input"))
        rightFiled.sendKeys("5")

        val close = dialog.findElement(By.xpath("div[1]/div[3]/div"))
        close.click()
    }
}
