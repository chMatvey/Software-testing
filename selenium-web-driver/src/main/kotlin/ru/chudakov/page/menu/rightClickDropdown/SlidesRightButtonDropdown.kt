package ru.chudakov.page.menu.rightClickDropdown

import org.openqa.selenium.*
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
        clickButton("div[4]", "/html/body/div[@class='modal-dialog docs-dialog']")
    }

    fun copy() {
        rightButtonClick()
        clickButton("div[5]", "/html/body/div[@class='modal-dialog docs-dialog']")
    }

    fun insert() {
        rightButtonClick()
        clickButton("div[6]", "/html/body/div[@class='modal-dialog docs-dialog']")
    }

    fun insertWithoutFormatting() {
        rightButtonClick()
        clickButton("div[7]", "/html/body/div[@class='modal-dialog docs-dialog']")
    }

    fun delete() {
        val slidesXpath = "//*[@id=\"filmstrip\"]/div/*[local-name()='svg']/*[local-name()='g']"
        val slidesCount = driver.findElements(By.xpath(slidesXpath)).size

        rightButtonClick()
        val button = dropdown.findElement(By.xpath("div[8]"))
        button.click()

        wait.until { driver.findElements(By.xpath(slidesXpath)).size == slidesCount - 1 }
    }

    fun createSlide() {
        val slidesXpath = "//*[@id=\"filmstrip\"]/div/*[local-name()='svg']/*[local-name()='g']"
        val slidesCount = driver.findElements(By.xpath(slidesXpath)).size

        rightButtonClick()
        val button = dropdown.findElement(By.xpath("div[12]"))
        button.click()

        wait.until { driver.findElements(By.xpath(slidesXpath)).size == slidesCount + 1 }
    }

    fun duplicateSlide() {
        val slidesXpath = "//*[@id=\"filmstrip\"]/div/*[local-name()='svg']/*[local-name()='g']"
        val slidesCount = driver.findElements(By.xpath(slidesXpath)).size

        rightButtonClick()
        val button = dropdown.findElement(By.xpath("div[13]"))
        button.click()

        wait.until { driver.findElements(By.xpath(slidesXpath)).size == slidesCount + 1 }
    }

    fun missSlide() {
        rightButtonClick()
        var button = dropdown.findElement(By.xpath("div[14]"))
        button.click()

        rightButtonClick()
        wait.until { dropdown.findElement(By.xpath("div[14]")).getAttribute("aria-checked") == "true" }
        button = dropdown.findElement(By.xpath("div[14]"))
        button.click()
    }

    fun changeBackground() {
        rightButtonClick()
        val button = dropdown.findElement(By.xpath("div[16]"))
        button.click()

        //val dialog = driver.findElement(By.xpath("/html/body/div[@class='modal-dialog']"))
        val colorButton = driver.findElement(By.xpath("//*[@id=\"punch-id-bg-color-button-container\"]/div/div[1]"))
        wait.until { colorButton.isDisplayed }

        colorButton.click()
        val colors = driver.findElements(By.xpath("/html/body/div[@class='goog-menu goog-menu-vertical docs-material docs-colormenuitems goog-menu-noaccel sketchy-gradientmenuitems']/div[5]/table/tbody/tr/td"))
        colors.forEach {
            wait.until { colors.first().isDisplayed }
            it.click()
            colorButton.click()
        }

        action.sendKeys(Keys.ESCAPE).build().perform()
        Thread.sleep(500)
        action.sendKeys(Keys.ESCAPE).build().perform()
        Thread.sleep(500)
    }

    fun changeLayout() {
        rightButtonClick()
        val button = dropdown.findElement(By.xpath("div[17]"))
        button.click()

        val layouts = driver.findElements(By.xpath("/html/body/div[@class='goog-menu goog-menu-vertical docs-material'][3]/div/table/tbody/tr/td"))

        wait.until { layouts.first().isDisplayed }
        layouts[8].click()
    }

    fun changeTheme() {
        rightButtonClick()
        val button = dropdown.findElement(By.xpath("div[17]"))
        button.click()

        val themes = driver.findElements(By.xpath("/html/body/div[@class='punch-theme-sidebar docs-material']/div[2]/div/div[3]/div"))
        var size = themes.size

        themes[1].click()
        Thread.sleep(750)
    }

    fun transfer() {
        rightButtonClick()
        val button = dropdown.findElement(By.xpath("div[20]"))
        button.click()

        val div = driver.findElement(By.xpath("/html/body/div[@class='punch-animation-sidebar docs-material']"))
        wait.until { div.isDisplayed }
    }

    fun toFirstSlide() {
        rightButtonClick()
        val button = dropdown.findElement(By.xpath("div[20]"))
        button.click()
    }

    fun comment() {
        rightButtonClick()
        val button = dropdown.findElement(By.xpath("div[24]"))
        button.click()

        val textArea = driver.findElement(By.xpath("//*[@id=\"workspace\"]/div[2]/div/div[1]/div/div[2]/textarea"));
        wait.until { textArea.isDisplayed }
        textArea.sendKeys("TPO lab1")

        val send = driver.findElement(By.xpath("//*[@id=\"workspace\"]/div[2]/div/div[1]/div[1]/div[2]/div[7]/div[1]"))
        wait.until { send.isDisplayed }
        send.click()
    }
}
