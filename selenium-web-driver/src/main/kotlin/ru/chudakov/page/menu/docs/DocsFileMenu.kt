package ru.chudakov.page.menu.docs

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.WebDriverWait

class DocsFileMenu(driver: WebDriver, wait: WebDriverWait) : AbstractDocsMenu(driver, wait) {
    @FindBy(xpath = "//*[@id=\"docs-file-menu\"]")
    override lateinit var docsMenuButton: WebElement

    @FindBy(xpath = "/html/body/div[35]")
    override lateinit var dropdown: WebElement

    override val xpathListOfDropdown = listOf("div[5]", "div[11]", "div[14]", "div[25]")

    fun accessButtonClick() {
        wait.until { dropdown.isDisplayed }

        val accessButton = dropdown.findElement(By.xpath("div[3]"))
        accessButton.click()

        val xpath = "/html/body/div[@class='modal-dialog docs-rename-dialog docs-dialog']/div[2]/div/input"
        wait.until { driver.findElements(By.xpath(xpath)).isNotEmpty() }

        val presentationNameInput = driver.findElement(By.xpath(xpath))
        presentationNameInput.sendKeys("name")

        val saveButton = driver.findElement(By.xpath("/html/body/div[@class='modal-dialog docs-rename-dialog docs-dialog']/div[3]/button[2]"))
        saveButton.click()

//        driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='modal-dialog share-client-dialog']/div[2]/iframe")))
//        driver.switchTo().frame(driver.findElement(By.xpath("/html/body/iframe")))
//        xpath = "html/body/div[@class='modal-dialog data-dialog']/div[3]/div/div[7]/div[1]/div"
//        wait.until { driver.findElements(By.xpath(xpath)).isNotEmpty() }
//        val doneButton = driver.findElement(By.xpath(xpath))
//        doneButton.click()
//        driver.switchTo().defaultContent();

        action.sendKeys(Keys.ESCAPE).build().perform()
        Thread.sleep(500)
    }

    fun openButtonClick() {
        val x = "/html/body/div[35]/div[7]"

        val dropDownButtonXpath = "div[6]"
        val dialogXpath = "/html/body/div[@class='picker modal-dialog picker-dialog']/div[2]"

        clickDropdownButton(dropDownButtonXpath, dialogXpath)
    }

    fun importSlides() {
        val dropDownButtonXpath = "div[7]"
        val dialogXpath = "/html/body/div[@class='picker modal-dialog picker-dialog'][2]/div[2]"

        clickDropdownButton(dropDownButtonXpath, dialogXpath)
    }

    fun createCopy() {
        val dropDownButtonXpath = "div[8]"
        val dialogXpath = "/html/body/div[@class='modal-dialog docs-dialog docs-copydocdialog']"

        clickDropdownButton(dropDownButtonXpath, dialogXpath)
    }

    fun sendMessage() {
        val dropDownButtonXpath = "div[12]"
        val dialogXpath = "/html/body/div[@class='modal-dialog share-client-dialog']"

        clickDropdownButton(dropDownButtonXpath, dialogXpath)
    }

    fun rename() {
        docsMenuButton.click()
        wait.until { dropdown.isDisplayed }

        val rename = dropdown.findElement(By.xpath("div[16]"))
        rename.click()

        val inputName = driver.findElement(By.xpath("//*[@id=\"docs-title-widget\"]/input"))
        wait.until { inputName.isDisplayed }
        inputName.sendKeys("name1")

        action.sendKeys(Keys.ENTER).build().perform()
    }

    fun transfer() {
//        val dropDownButtonXpath = "div[17]"
//        val dialogXpath = "//*[@id=\"docs-chrome\"]/div[@class='picker-iframe picker-min goog-menu picker-minew picker-min-popup picker-min-top']"
//
//        clickDropdownButton(dropDownButtonXpath, dialogXpath)
    }

    fun delete() {
//        val dropDownButtonXpath = "div[19]"
//        val dialogXpath = "/html/body/div[@class='modal-dialog docs-dialog docs-restore-dialog']"
//
//        clickDropdownButton(dropDownButtonXpath, dialogXpath)
    }

    fun publish() {
//        val dropDownButtonXpath = "div[21]"
//        val dialogXpath = "/html/body/div[@class='modal-dialog docs-dialog']"
//
//        clickDropdownButton(dropDownButtonXpath, dialogXpath)
    }

    fun getInformation() {
        val dropDownButtonXpath = "div[24]"
        val dialogXpath = "/html/body/div[@class='modal-dialog docs-dialog docs-details-dialog']"

        clickDropdownButton(dropDownButtonXpath, dialogXpath)
    }

    fun settings() {
        val dropDownButtonXpath = "div[26]"
        val dialogXpath = "/html/body/div[@class='modal-dialog docs-dialog sketchy-pagesetup']"

        clickDropdownButton(dropDownButtonXpath, dialogXpath)
    }
}
