package ru.chudakov.page

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.WebDriverWait
import ru.chudakov.page.menu.SlidesMenu
import ru.chudakov.page.menu.TextAreaMenu
import ru.chudakov.page.menu.docs.*

class PresentationPage(driver: WebDriver, wait: WebDriverWait) : AbstractPage(driver, wait) {
    override val pageUrl = "https://docs.google.com/presentation/d";

    val slidesMenu = SlidesMenu(driver, wait)

    val textAreaMenu = TextAreaMenu(driver, wait)

    val docsFileMenu = DocsFileMenu(driver, wait)

    val docsEditMenu = DocsEditMenu(driver, wait)

    val docsViewMenu = DocsViewMenu(driver, wait)

    val docsInsertMenu = DocsInsertMenu(driver, wait)

    val docsFormatMenu = DocsFormatMenu(driver, wait)

    val docsSlidesMenu = DocsSlideMenu(driver, wait)

    val docsArrangeMenu = DocsArrangeMenu(driver, wait)

    val docsToolsMenu = DocsToolsMenu(driver, wait)

    val docsExtensionsMenu = DocsExtensionsMenu(driver, wait)

    val docsHelpMenu = DocsHelpMenu(driver, wait)

    fun clickDocsMenuButtons() {
        val docsButtons = getDocsMenuButtons()

        for (button in docsButtons) {
            button.click()
        }
    }

    private fun getDocsMenuButtons(): List<WebElement> {
        return driver.findElements(By.xpath("//*[@id=\"docs-menubar\"]/div"))
                .filter { webElement -> webElement.isDisplayed }
    }
}
