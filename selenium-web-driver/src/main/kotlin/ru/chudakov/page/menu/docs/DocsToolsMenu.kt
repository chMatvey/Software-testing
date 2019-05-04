package ru.chudakov.page.menu.docs

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.WebDriverWait

class DocsToolsMenu(driver: WebDriver, wait: WebDriverWait) : AbstractDocsMenu(driver, wait) {
    @FindBy(xpath = "//*[@id=\"docs-tools-menu\"]")
    override lateinit var docsMenuButton: WebElement

    @FindBy(xpath = "/html/body/div[42]")
    override lateinit var dropdown: WebElement

    override val xpathListOfDropdown = listOf("div[1]")

    fun extendedSearch() {
        docsMenuButton.click()
        clickDropdownButton("div[3]")

        val closeDialog = driver.findElement(By.xpath("/html/body/div[@class='docs-explore-sidebar docs-material']/div[1]/div[5]"))
        wait.until { closeDialog.isDisplayed }
        closeDialog.click()
    }

    fun dictionaryOpen() {
        clickDropdownButton("div[4]")

        val closeDialog = driver.findElement(By.xpath("/html/body/div[@class='docs-dictionary-sidebar docs-material']/div[1]/div[4]"))
        wait.until { closeDialog.isDisplayed }
        closeDialog.click()
    }

    fun questionsHistoryOpen() {
        clickDropdownButton("div[5]")

        val closeDialog = driver.findElement(By.xpath("/html/body/div[@class='punch-qanda-sidebar docs-material']/div[1]/div[2]"))
        wait.until { closeDialog.isDisplayed }
        closeDialog.click()
    }

    fun settingsOpen() {
        clickDropdownButton("div[10]", "/html/body/div[@class='modal-dialog docs-dialog']")
    }

    fun specialSettingsOpen() {
        clickDropdownButton("div[11]", "/html/body/div[@class='docs-material-gm-dialog a11y-settings-dialog docs-gm']")
    }
}
