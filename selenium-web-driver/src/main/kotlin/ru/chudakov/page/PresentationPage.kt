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
import ru.chudakov.page.menu.SlidesMenu
import ru.chudakov.page.menu.TextAreaMenu

class PresentationPage(driver: WebDriver, wait: WebDriverWait) : AbstractPage(driver, wait) {
    override val pageUrl = "https://docs.google.com/presentation/d";

    val slidesMenu = SlidesMenu(driver, wait)

    val textAreaMenu = TextAreaMenu(driver, wait)

    @FindBy(xpath = "//*[@id=\"docs-menubar\"]")
    lateinit var docsMenu: WebElement

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

    private fun getDocsMenuButtons(): List<WebElement> {
        return driver.findElements(By.xpath("//*[@id=\"docs-menubar\"]/div"))
                .filter { webElement -> webElement.isDisplayed }
    }

    private fun filterDropDownButtons(buttons: MutableList<WebElement>) {
        if (buttons.size == 10) {
            buttons.removeAt(9)
            buttons.removeAt(8)
            buttons.removeAt(0)
        }
    }
}
