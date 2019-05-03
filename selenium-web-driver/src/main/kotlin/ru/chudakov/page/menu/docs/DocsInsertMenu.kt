package ru.chudakov.page.menu.docs

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.WebDriverWait
import ru.chudakov.textAreaTest

class DocsInsertMenu(driver: WebDriver, wait: WebDriverWait) : AbstractDocsMenu(driver, wait) {
    @FindBy(xpath = "//*[@id=\"docs-insert-menu\"]")
    override lateinit var docsMenuButton: WebElement

    @FindBy(xpath = "/html/body/div[38]")
    override lateinit var dropdown: WebElement

    override val xpathListOfDropdown = listOf("div[1]", "div[4]", "div[6]", "div[8]")

    @FindBy(xpath = "//*[@id=\"workspace-container\"]")
    lateinit var workSpace: WebElement

    fun insertTextArea() {
        clickDropdownButton("div[2]")
        workSpace.click()
    }

    fun insertVideo() {
        clickDropdownButton("div[3]", "/html/body/div[@class='picker modal-dialog picker-dialog']")
    }

    fun insertTable() {
        clickDropdownButton("div[5]")

        val table = driver.findElement(By.xpath("//*[@class=\"goog-dimension-picker\"]/div[1]/div[3]"))
        wait.until { table.isDisplayed }
        table.click()
    }

    fun insertArt() {
        clickDropdownButton("div[7]", "/html/body/div[@class='sketchy-bubble']")
    }

    fun insertNewSlide() {
        clickDropdownButton("div[17]")
    }

    fun insertSlideNumbers() {
        clickDropdownButton("div[18]")

        val toAll = driver.findElement(By.xpath("/html/body/div[@class='modal-dialog docs-dialog sketchy-slidenumber-dialog']/div[3]/button[2]"))
        wait.until { toAll.isDisplayed }
        toAll.click()
        Thread.sleep(500)
    }

    fun insertDiagram() {
        clickDropdownButton("div[6]")

        val xpath = "/html/body/div[45]/div[4]"
        //wait.until { driver.findElements(By.xpath(xpath)).isNotEmpty() }
        val diagram = driver.findElement(By.xpath(xpath))
        wait.until { diagram.isDisplayed }
        diagram.click()

        Thread.sleep(2000)
    }
}
