package ru.chudakov.page.menu.docs

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.WebDriverWait

class DocsEditMenu(driver: WebDriver, wait: WebDriverWait) : AbstractDocsMenu(driver, wait) {
    @FindBy(xpath = "//*[@id=\"docs-edit-menu\"]")
    override lateinit var docsMenuButton: WebElement

    @FindBy(xpath = "/html/body/div[36]")
    override lateinit var dropdown: WebElement

    fun selectAll() {
        clickDropdownButton("div[12]")
    }

    fun creatCopy() {
        selectAll()
        clickDropdownButton("div[11]")
    }

    fun cancel() {
        clickDropdownButton("div[3]")
    }

    fun copy() {
        selectAll()
        //clickDropdownButton("div[7]")
        clickDropdownButton("div[7]", "/html/body/div[@class='modal-dialog docs-dialog']")
    }

    fun insert() {
        //clickDropdownButton("div[8]")
        clickDropdownButton("div[8]", "/html/body/div[@class='modal-dialog docs-dialog']")
    }

    fun repeat() {
        clickDropdownButton("div[4]")
    }

    fun findAndReplace(find: String = "text", replace: String = "text1") {
        clickDropdownButton("div[14]")

        val findInput = driver.findElement(By.xpath("//*[@id=\"docs-findandreplacedialog-input\"]"))
        wait.until { findInput.isDisplayed }
        val replaceInput = driver.findElement(By.xpath("//*[@id=\"docs-findandreplacedialog-replace-input\"]"))

        findInput.sendKeys(find)
        replaceInput.sendKeys(replace)

        val replaceAll = driver.findElement(By.xpath("//*[@id=\"docs-findandreplacedialog-button-replace-all\"]"))
        replaceAll.click()

        action.sendKeys(Keys.ESCAPE).build().perform()
        //wait.until { !replaceAll.isDisplayed }
        Thread.sleep(500)
    }
}
