package ru.chudakov.page

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Action
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.util.ArrayList

class PresentationPage(private val driver: WebDriver, private val wait: WebDriverWait) :
        AbstractPage(driver, wait) {
    override val pageUrl = "https://docs.google.com/presentation/d";

    @FindBy(xpath = "//*[@id=\"docs-menubar\"]")
    lateinit var docsMenu: WebElement

    @FindBy(xpath = "//*[@id=\"newSlideButton\"]")
    lateinit var newSlideButton: WebElement

    @FindBy(xpath = "//*[@id=\"newSlideMenuButton\"]")
    lateinit var newSlideMenuButton: WebElement

    @FindBy(xpath = "//*[@id=\"undoButton\"]")
    lateinit var undoButton: WebElement

    @FindBy(xpath = "//*[@id=\"redoButton\"]")
    lateinit var redoButton: WebElement

    @FindBy(xpath = "//*[@id=\"printButton\"]")
    lateinit var printButton: WebElement

    @FindBy(xpath = "//*[@id=\"paintFormatButton\"]")
    lateinit var paintFormatButton: WebElement

//    @FindBy(tagName = "body")
//    lateinit var body: WebElement
//
//    @FindBy(tagName = "html")
//    lateinit var html: WebElement

    private fun getDocsMenuButtons(): List<WebElement> {
        return driver.findElements(By.xpath("//*[@id=\"docs-menubar\"]/div"))
                .filter { webElement -> webElement.isEnabled && webElement.isDisplayed }
    }

    fun clickDocsMenuButtons() {
        val docsButtons = getDocsMenuButtons()
        //val docsButtonsAndDropDowns = HashMap<WebElement, WebElement>()
        val docsDropDowns = ArrayList<WebElement>()

        val byXpath = By.xpath("//div[@class='goog-menu goog-menu-vertical docs-material docs-menu-hide-mnemonics']")
        val byXpathExtensions = By.xpath("//div[@class='goog-menu goog-menu-vertical docs-material goog-menu-noaccel docs-menu-hide-mnemonics']")
        //val byXpathHelp = By.xpath("//div[@class='goog-menu goog-menu-vertical docs-material docs-omnibox-parent docs-menu-hide-mnemonics']")
        val byXpathHelp = By.xpath("/html/body/div[10]")

        val action = Actions(driver)
        docsButtons.first().click()
        //body.sendKeys(Keys.ESCAPE)
        //action.sendKeys(Keys.ESCAPE).build().perform()

        for (button in docsButtons) {
            button.click()
            when (button.getAttribute("id")) {
                "docs-extensions-menu" -> {
                    wait.until { !driver.findElements(byXpathExtensions).isEmpty() }
                    //docsButtonsAndDropDowns.put(button, driver.findElement(byXpathExtensions))
                    docsDropDowns.add(driver.findElement(byXpathExtensions))
                }
                "docs-help-menu" -> {
                    Thread.sleep(1000)
                    //wait.until { !driver.findElements(byXpathHelp).isEmpty() }
                    //docsButtonsAndDropDowns.put(button, driver.findElement(byXpathHelp))
                    docsDropDowns.add(driver.findElement(byXpathHelp))
                }
                else -> {
                    wait.until { !driver.findElements(byXpath).isEmpty() }
                    //docsButtonsAndDropDowns.put(button, driver.findElements(byXpath).last())
                    docsDropDowns.add(driver.findElements(byXpath).last())
                }
            }
        }
        action.sendKeys(Keys.ESCAPE).build().perform()

        for (dropDown in docsDropDowns) {
            docsButtons.get(docsDropDowns.indexOf(dropDown)).click()
            //buttonAndDropDown.key.click()
            val buttonsFromDropDowns = dropDown.findElements(By.xpath("div"))
                    .filter { webElement ->
                        webElement.isDisplayed && webElement.isEnabled && webElement.getAttribute("role") == "menuitem"
                                && webElement.getAttribute("class") != "goog-menuitem goog-menuitem-disabled apps-menuitem"
                    }
            action.sendKeys(Keys.ESCAPE).build().perform()

            for (button in buttonsFromDropDowns) {
                Thread.sleep(1000)
                val docsButton = docsButtons.get(docsDropDowns.indexOf(dropDown))
                wait.until(ExpectedConditions.visibilityOf(docsButton))
                docsButton.click()

                wait.until(ExpectedConditions.visibilityOf(button))
                button.click()

                action.sendKeys(Keys.ESCAPE).build().perform()
                action.sendKeys(Keys.ESCAPE).build().perform()
                action.sendKeys(Keys.ESCAPE).build().perform()
            }
        }

//        val selects = driver.findElements(
//                By.xpath("//div[@class='goog-menu goog-menu-vertical docs-material docs-menu-hide-mnemonics']")
//        )
//        selects.add(driver.findElement(
//                By.xpath("//div[@class='goog-menu goog-menu-vertical docs-material goog-menu-noaccel docs-menu-hide-mnemonics']"))
//        )
//        selects.add(driver.findElement(
//                By.xpath("//div[@class='goog-menu goog-menu-vertical docs-material docs-omnibox-parent docs-menu-hide-mnemonics']"))
//        )
    }
}
