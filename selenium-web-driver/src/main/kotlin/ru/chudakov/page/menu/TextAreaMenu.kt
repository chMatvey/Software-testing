package ru.chudakov.page.menu

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.WebDriverWait

class TextAreaMenu(driver: WebDriver, wait: WebDriverWait) : AbstractMenu(driver, wait)  {

    @FindBy(xpath = "//*[@id=\"workspace-container\"]")
    lateinit var workSpace: WebElement

    @FindBy(xpath = "//*[@id=\"fillColorMenuButton\"]")
    lateinit var fillColorMenuButton: WebElement

    @FindBy(xpath = "//*[@id=\"lineColorMenuButton\"]")
    lateinit var lineColorMenuButton: WebElement

    @FindBy(xpath = "//*[@id=\"lineWidthMenuButton\"]")
    lateinit var lineWidthMenuButton: WebElement

    @FindBy(xpath = "//*[@id=\"lineDashingMenuButton\"]")
    lateinit var lineDashingMenuButton: WebElement

    @FindBy(xpath = "//*[@id=\"docs-font-family\"]")
    lateinit var docsFontFamilyButton: WebElement

    @FindBy(xpath = "//*[@id=\"fontSizeSelect\"]")
    lateinit var fontSizeSelect: WebElement

    @FindBy(xpath = "//*[@id=\"boldButton\"]")
    lateinit var boldButton: WebElement

    @FindBy(xpath = "//*[@id=\"italicButton\"]")
    lateinit var italicButton: WebElement

    @FindBy(xpath = "//*[@id=\"underlineButton\"]")
    lateinit var underlineButton: WebElement

    @FindBy(xpath = "//*[@id=\"textColorButton\"]")
    lateinit var textColorButton: WebElement

    @FindBy(xpath = "//*[@id=\"bgColorButton\"]")
    lateinit var bgColorButton: WebElement

    @FindBy(xpath = "//*[@id=\"insertLinkButton\"]")
    lateinit var insertLinkButton: WebElement

    @FindBy(xpath = "//*[@id=\"alignButton\"]")
    lateinit var alignButton: WebElement

    @FindBy(xpath = "//*[@id=\"lineSpacingMenuButton\"]")
    lateinit var lineSpacingMenuButton: WebElement

    @FindBy(xpath = "//*[@id=\"numberedListPresetMenuButton\"]")
    lateinit var numberedListPresetMenuButton: WebElement

    @FindBy(xpath = "//*[@id=\"bulletedListPresetMenuButton\"]")
    lateinit var bulletListPresetMenuButton: WebElement

    @FindBy(xpath = "//*[@id=\"outdentButton\"]")
    lateinit var outDentButton: WebElement

    @FindBy(xpath = "//*[@id=\"indentButton\"]")
    lateinit var indentButton: WebElement

    @FindBy(xpath = "//*[@id=\"formatOptionsButton\"]")
    lateinit var formatOptionsButton: WebElement

    @FindBy(xpath = "//*[@id=\"moreButton\"]")
    lateinit var moreButton: WebElement

    private fun clickMenuButtons(menuButton: WebElement, elementsXpath: String) {
        workSpace.click()
        wait.until { menuButton.isDisplayed }

        menuButton.click()

        val elements = driver.findElements(By.xpath(elementsXpath))
        wait.until { elements.first().isDisplayed }
        elements.forEach {
            it.click()
            menuButton.click()
            wait.until { elements.first().isDisplayed }
        }
    }

    fun inputText(text: String = "qwerty ", count: Int = 3) {
        workSpace.click()

        //val textArea = driver.findElement(By.xpath("//div[@id=\"pages\"]/*[local-name()='svg'][2]/*[local-name()='g']/*[local-name()='g']/*[local-name()='g'][3]/*[local-name()='g']//*[local-name()='g'][5]/*[local-name()='g']/*[local-name()='g'][2]/*[local-name()='path'][1]"))
        //textArea.sendKeys("qwerty")

        val action = Actions(driver)
        var key: String = ""
        for (i in 1..count) {
            key = key.plus(text)
        }
        action.sendKeys(key).perform()
    }

    fun fillColorMenuButtonClick() {
        workSpace.click()
        wait.until { formatOptionsButton.isDisplayed }

        val elementsXpath = "/html/body/div[@class='goog-menu goog-menu-vertical docs-material docs-colormenuitems goog-menu-noaccel sketchy-gradientmenuitems']/div[4]/table/tbody/tr/td"
        clickMenuButtons(fillColorMenuButton, elementsXpath)
    }

    fun lineColorMenuButtonClick() {
        val elementsXpath = "/html/body/div[@class='goog-menu goog-menu-vertical docs-material docs-colormenuitems goog-menu-noaccel']/div[3]/table/tbody/tr/td"
        clickMenuButtons(lineColorMenuButton, elementsXpath)
    }

    fun lineWidthMenuButtonClick() {
        val elementsXpath = "/html/body/div[@class='goog-menu goog-menu-vertical docs-material goog-menu-noaccel'][2]/div"
        clickMenuButtons(lineWidthMenuButton, elementsXpath)
    }

    fun lineDashingMenuButtonClick() {
        val elementsXpath = "/html/body/div[@class='goog-menu goog-menu-vertical docs-material goog-menu-noaccel'][3]/div"
        clickMenuButtons(lineDashingMenuButton, elementsXpath)
    }

    fun docsFontFamilyButtonClick() {
        selectText()

        val elementsXpath = "/html/body//div[@class='docs-fontmenu-fonts']/div"

        docsFontFamilyButton.click()
        val firstFont = driver.findElement(By.xpath(elementsXpath))
        wait.until { firstFont.isDisplayed }
        firstFont.click()
    }

    fun changeFontSize() {
        selectText()

        val elementsXpath = "//div[@class='goog-menu goog-menu-vertical goog-menu-noicon goog-menu-noaccel']/div"

        clickMenuButtons(fontSizeSelect, elementsXpath)
    }

    fun changeText() {
        selectText()

        boldButton.click()
        italicButton.click()
        underlineButton.click()
    }

    fun changeTextColor() {
        selectText()

        val elementsXpath = "/html/body/div[@class='goog-menu goog-menu-vertical docs-material docs-colormenuitems goog-menu-noaccel'][2]/div[2]/table/tbody/tr/td"
        clickMenuButtons(textColorButton, elementsXpath)
    }

    fun changeBackgroundTextColor() {
        selectText()

        val elementsXpath = "html/body/div[@class='goog-menu goog-menu-vertical docs-material docs-colormenuitems goog-menu-noaccel'][3]/div[2]/table/tbody/tr/td"
        clickMenuButtons(bgColorButton, elementsXpath)
    }

    fun insetLink() {
        workSpace.click()
        wait.until { insertLinkButton.isDisplayed }

//        insertLinkButton.click()
//        val slideLink = driver.findElement(By.xpath("//div[@class='jfk-bubble docs-calloutbubble-bubble docs-linkbubble-bubble docs-material']/div/div/div[2]/div/div[2]"))
//        wait.until { slideLink.isDisplayed }
//        slideLink.click()
//
//        val previousSlide = driver.findElement(By.xpath("//div[@class='jfk-bubble docs-calloutbubble-bubble docs-linkbubble-bubble docs-material']/div/div/div[2]/div/div[1]/div[3]/div/div[2]/div/div[2]/div[1]"))
//        wait.until { previousSlide.isDisplayed }
//        previousSlide.click()
    }

    fun alignText() {
        selectText()

        val elementsXpath = "/html/body/div[@class='goog-menu goog-menu-vertical']/div/table/tbody/tr/td"

        clickMenuButtons(alignButton, elementsXpath)
    }

    fun changeLineSpacing() {
        selectText()

        lineSpacingMenuButton.click()
        lineSpacingMenuButton.click()

//        val elementsXpath = "/html/body/div[@class='goog-menu goog-menu-vertical docs-material goog-menu-noaccel'][4]/div"
//        val elements = driver.findElements(By.xpath(elementsXpath)).filter { it.getAttribute("class") != "goog-menuseparator" }
//
//        clickMenuButtons(lineSpacingMenuButton, listOf(elements[0], elements[1]))
    }

    fun numberedList() {
        selectText()

        val elementsXpath = "/html/body/div[@class='goog-menu goog-menu-vertical'][2]/div/table/tbody/tr/td"
        clickMenuButtons(numberedListPresetMenuButton, elementsXpath)
    }

    fun bulletListPresetMenuButtonClick() {
        selectText()

        val elementsXpath = "/html/body/div[@class='goog-menu goog-menu-vertical']/div/table/tbody/tr/td"
//        clickMenuButtons(bulletListPresetMenuButton, elementsXpath)
    }

    fun changeIndent() {
        outDentButton.click()
        indentButton.click()
    }

    fun formatOptionsButtonClick() {
        if (!formatOptionsButton.isDisplayed) {
            moreButton.click()
            wait.until { formatOptionsButton.isDisplayed }
        }

        formatOptionsButton.click()
        val sizeAndPosition = driver.findElement(By.xpath("/html/body/div[@class='docs-tiled-sidebar docs-material']/div[2]/div/div[2]/div[1]"))
        wait.until { sizeAndPosition.isDisplayed }
        sizeAndPosition.click()

        val weight = driver.findElement(By.xpath("/html/body/div[@class='docs-tiled-sidebar docs-material']/div[2]/div/div[2]/div[2]/div/div[1]/div[2]/div[1]/div/div[2]/input"))
        val height = driver.findElement(By.xpath("/html/body/div[@class='docs-tiled-sidebar docs-material']/div[2]/div/div[2]/div[2]/div/div[1]/div[2]/div[2]/div/div[2]/input"))
        val corner = driver.findElement(By.xpath("/html/body/div[@class='docs-tiled-sidebar docs-material']/div[2]/div/div[2]/div[2]/div/div[3]/div[2]/div[1]/div/div[2]/input"))

        wait.until { weight.isDisplayed }
        weight.sendKeys("23")
        height.sendKeys("9.5")
        corner.sendKeys("5")
    }

    private fun selectText() {
        workSpace.click()
        workSpace.click()
        workSpace.click()
    }

    private fun clickMenuButtons(menuButton: WebElement, elements: List<WebElement>) {
        workSpace.click()
        wait.until { menuButton.isDisplayed }

        menuButton.click()

        wait.until { elements.first().isDisplayed }
        elements.forEach {
            it.click()
            menuButton.click()
            wait.until { elements.first().isDisplayed }
        }
    }
}
