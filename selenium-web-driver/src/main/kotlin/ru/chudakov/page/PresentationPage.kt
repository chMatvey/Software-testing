package ru.chudakov.page

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.util.*
import kotlin.collections.HashMap

class PresentationPage(driver: WebDriver, wait: WebDriverWait) : AbstractPage(driver, wait) {
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

    @FindBy(xpath = "//*[@id=\"zoomButton\"]")
    lateinit var zoomButton: WebElement

    @FindBy(xpath = "//*[@id=\"zoomButtonDropdown\"]")
    lateinit var zoomButtonDropdown: WebElement

    @FindBy(xpath = "//*[@id=\"textboxButton\"]")
    lateinit var textBoxButton: WebElement

    @FindBy(xpath = "//*[@id=\"workspace-container\"]")
    lateinit var workSpace: WebElement

    @FindBy(xpath = "//*[@id=\"insertImageMenuButton\"]")
    lateinit var insertImageMenuButton: WebElement

    @FindBy(xpath = "//*[@id=\"insertCommentButton\"]")
    lateinit var insertCommentButton: WebElement

    fun clickDocsMenuButtons() {
        val docsButtons = getDocsMenuButtons()
        val docsDropDowns = ArrayList<WebElement>()

        val byXpath = By.xpath("//div[@class='goog-menu goog-menu-vertical docs-material docs-menu-hide-mnemonics']")
        val byXpathExtensions = By.xpath("//div[@class='goog-menu goog-menu-vertical docs-material goog-menu-noaccel docs-menu-hide-mnemonics']")
        val byXpathHelp = By.xpath("/html/body/div[10]")

        docsButtons.first().click()

        val action = Actions(driver)

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
            val buttonsFromDropDowns = dropDown.findElements(By.xpath("div"))
                    .filter { webElement ->
                        webElement.getAttribute("class") == "goog-menuitem apps-menuitem" &&
                                webElement.getAttribute("id") != ":gd" && webElement.getAttribute("id") != ":ge"
                    }.toSet()
            action.sendKeys(Keys.ESCAPE).build().perform()

            val docsButton = docsButtons.get(docsDropDowns.indexOf(dropDown))

            for (button in buttonsFromDropDowns) {
                Thread.sleep(1000)
                wait.until(ExpectedConditions.visibilityOf(docsButton))
                docsButton.click()

                Thread.sleep(1000)
                wait.until(ExpectedConditions.visibilityOf(button))
                button.click()

                wait.until {
                    !driver.findElements(By.xpath("//div[@role='dialog']")).none { webElement -> webElement.isDisplayed && webElement.getAttribute("aria-hidden") != "true" }
                            || driver.findElement(By.xpath("//*[@id=\"docs-parent-collections-container-outer\"]")).isDisplayed
                }
                Thread.sleep(1000)

                action.sendKeys(Keys.ESCAPE).build().perform()
                Thread.sleep(1000)
                action.sendKeys(Keys.ESCAPE).build().perform()
            }
            System.out.println("One iteration")
            Thread.sleep(2000)
        }
    }

    fun createNewSlide() {
        val countSlides = getSlides().count()

        newSlideButton.click()
        wait.until { countSlides + 1 == getSlides().count() }

        undoButton.click()
        wait.until { countSlides == getSlides().count() }

        redoButton.click()
        wait.until { countSlides + 1 == getSlides().count() }
    }

    fun printButtonClick() {
        printButton.click()
        Thread.sleep(3000)
        val action = Actions(driver)
        val cancelButton = driver.findElement(By.xpath("//*[@id=\"button-strip\"]/paper-button[2]")).click()
    }

    fun zoomButtonsClick() {
        zoomButton.click()

        workSpace.click()
        workSpace.click()

        val actions = Actions(driver)
        actions.contextClick(workSpace).perform()

        zoomButtonDropdown.click()

        val byDropdown = By.xpath("")
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[@class='goog-menu goog-menu-vertical docs-material'][2]")))
        driver.findElement(By.xpath("//*[@id=\":fp\"]")).click()
    }

    fun textBoxButtonClick() {
        textBoxButton.click()
        workSpace.click()
        Thread.sleep(1000)
        undoButton.click()
    }

    fun insertImageMenuButtonClick() {
        val action = Actions(driver)

        val insertImageMenuDropdownXpath = hashMapOf(
                "//div[@class='goog-menu goog-menu-vertical docs-material goog-menu-noaccel'][2]/div[2]" to "//*[@id=\"yDmH0d\"]//div[@class='xS3Cpf']",
                "//div[@class='goog-menu goog-menu-vertical docs-material goog-menu-noaccel'][2]/div[4]" to "//*[@id=\"yDmH0d\"]//div[@class='ye3Lg']",
                "//div[@class='goog-menu goog-menu-vertical docs-material goog-menu-noaccel'][2]/div[5]" to "//*[@id=\"yDmH0d\"]/div/div[6]//div[@class='r7c0H']",
                "//div[@class='goog-menu goog-menu-vertical docs-material goog-menu-noaccel'][2]/div[6]" to "//div[@class='google-url-picker modal-dialog']",
                "//div[@class='goog-menu goog-menu-vertical docs-material goog-menu-noaccel'][2]/div[7]" to "//*[@id=\"yDmH0d\"]//div[@class='iOrYl']"
        )

        insertImageMenuDropdownXpath.forEach { t, u ->
            insertImageMenuButton.click()
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='goog-menu goog-menu-vertical docs-material goog-menu-noaccel'][2]")))
            driver.findElement(By.xpath(t)).click()
            //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(u)))
            Thread.sleep(1000)
            action.sendKeys(Keys.ESCAPE).build().perform()
        }
        action.sendKeys(Keys.ESCAPE).build().perform()
    }

    fun insertCommentButtonClick() {

    }

    private fun getDocsMenuButtons(): List<WebElement> {
        return driver.findElements(By.xpath("//*[@id=\"docs-menubar\"]/div"))
                .filter { webElement -> webElement.isDisplayed }
    }

    private fun getSlides(): List<WebElement> {
        return driver.findElements(By.cssSelector("#filmstrip > div > svg > g"))
    }
}
