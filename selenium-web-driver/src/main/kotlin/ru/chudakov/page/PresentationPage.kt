package ru.chudakov.page

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import ru.chudakov.getElementsByIndexes

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

    @FindBy(xpath = "//*[@id=\"shapeButton\"]")
    lateinit var shapeButton: WebElement

    @FindBy(xpath = "//*[@id=\"lineMenuButton\"]")
    lateinit var lineMenuButton: WebElement

    @FindBy(xpath = "//*[@id=\"insertCommentButton\"]")
    lateinit var insertCommentButton: WebElement

    @FindBy(xpath = "//*[@id=\"slideBackgroundButton\"]")
    lateinit var slideBackgroundButton: WebElement

    @FindBy(xpath = "//*[@id=\"slideLayoutMenuButton\"]")
    lateinit var slideLayoutMenuButton: WebElement

    @FindBy(xpath = "//*[@id=\"slideThemeButton\"]")
    lateinit var slideThemeButton: WebElement

    @FindBy(xpath = "//*[@id=\"slideTransitionButton\"]")
    lateinit var slideTransitionButton: WebElement

    private val dropDownButtonFilters = listOf<(WebElement) -> Boolean>(
            { it.getAttribute("class") == "goog-menuitem apps-menuitem" },
            { true },
            { it.getAttribute("class") == "goog-menuitem apps-menuitem goog-option" },
            { true },
            { true },
            { true },
            { true },
            { true },
            { true },
            { it.getAttribute("class") == "goog-menuitem apps-menuitem" }
    )

    private val dropDownButtonWaits = listOf<(WebDriver) -> Boolean>(
            {
                !it.findElements(By.xpath("//div[@role='dialog']"))
                        .none { webElement ->
                            webElement.isDisplayed && webElement.getAttribute("aria-hidden") != "true"
                        } ||
                        it.findElement(By.xpath("//*[@id=\"docs-parent-collections-container-outer\"]"))
                                .isDisplayed
            },
            { true },
            { true },
            { true },
            { true },
            { true },
            { true },
            { true },
            { true },
            { !it.findElements(By.xpath("//*[@id=\"dialog\"]")).isEmpty() ||
            !it.findElements(By.xpath("/html/body/div[2]/div/div")).isEmpty()
            !it.findElements(By.xpath("/html/body/div[40]")).isEmpty()}
    )

    fun clickDocsMenuButtons() {
        val docsButtons = getDocsMenuButtons()
        val docsDropDowns = mutableListOf<WebElement>()

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

        /// 1 dropDown is 0
        val indexes = listOf(9)
        for (dropDown in docsDropDowns.getElementsByIndexes(indexes)) {
            docsButtons[docsDropDowns.indexOf(dropDown)].click()
            val dropDownButtons = dropDown.findElements(By.xpath("div"))
                    .filter(dropDownButtonFilters[docsDropDowns.indexOf(dropDown)])
                    .toMutableList()

            filterDropDownButtons(dropDownButtons)

            action.sendKeys(Keys.ESCAPE).build().perform()

            val docsButton = docsButtons[docsDropDowns.indexOf(dropDown)]

            for (button in dropDownButtons) {
                when (docsButtons.indexOf(docsButton)) {
                    0, 7, 8, 9 -> {
                        wait.until(ExpectedConditions.visibilityOf(docsButton))
                        docsButton.click()

                        wait.until(ExpectedConditions.visibilityOf(button))
                        button.click()

                        wait.until(dropDownButtonWaits[docsDropDowns.indexOf(dropDown)])

                        action.sendKeys(Keys.ESCAPE).build().perform()
                        Thread.sleep(500)
                        //action.sendKeys(Keys.ESCAPE).build().perform()
                    }
                    2 -> {
                        wait.until(ExpectedConditions.visibilityOf(docsButton))
                        docsButton.click()
                        wait.until(ExpectedConditions.visibilityOf(button))
                        button.click()
                        action.sendKeys(Keys.ESCAPE).build().perform()
                        Thread.sleep(500)

                        wait.until(ExpectedConditions.visibilityOf(docsButton))
                        docsButton.click()
                        wait.until(ExpectedConditions.visibilityOf(button))
                        button.click()
                        action.sendKeys(Keys.ESCAPE).build().perform()
                        Thread.sleep(500)
                    }
                }
            }
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
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='goog-menu goog-menu-vertical docs-material'][2]")))
        driver.findElement(By.xpath("//div[@class='goog-menu goog-menu-vertical docs-material'][2]/div[1]")).click()
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

    fun shapeAndLineButtonsClick() {
        shapeButton.click()
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[44]")))
        insertCommentButton.click()
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[48]")))
    }

    fun insertCommentButtonClick() {
        insertCommentButton.click()
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"workspace\"]/div[2]/div/div[1]")))
        driver.findElement(By.xpath("//*[@id=\"workspace\"]/div[2]/div/div[1]/div/div[2]/textarea"))
                .sendKeys("Comment")
        driver.findElement(By.xpath("//*[@id=\"workspace\"]/div[2]/div/div[1]/div[1]/div[2]/div[7]/div[1]"))
                .click()
    }

    fun slideButtonsClick() {
        slideBackgroundButton.click()
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[@class='modal-dialog']")))
        driver.findElement(By.xpath("/html/body/div[@class='modal-dialog']//span[2]")).click()

        slideLayoutMenuButton.click()
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/table[@class='goog-palette-table']")))
        driver.findElement(By.xpath("//div/table[@class='goog-palette-table']/tbody/tr[4]/td[1]")).click()

        slideThemeButton.click()
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[@class='punch-theme-sidebar docs-material']")))

        slideTransitionButton.click()
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[@class='punch-animation-sidebar docs-material']")))
    }

    private fun getDocsMenuButtons(): List<WebElement> {
        return driver.findElements(By.xpath("//*[@id=\"docs-menubar\"]/div"))
                .filter { webElement -> webElement.isDisplayed }
    }

    private fun getSlides(): List<WebElement> {
        return driver.findElements(By.cssSelector("#filmstrip > div > svg > g"))
    }

    private fun filterDropDownButtons(buttons: MutableList<WebElement>) {
        if (buttons.size == 10) {
            buttons.removeAt(9)
            buttons.removeAt(8)
            buttons.removeAt(0)
        }
    }
}
