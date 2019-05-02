package ru.chudakov.page.menu

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class SlidesMenu(driver: WebDriver, wait: WebDriverWait) : AbstractMenu(driver, wait) {
    @FindBy(xpath = "//*[@id=\"newSlideButton\"]")
    lateinit var newSlideButton: WebElement

    @FindBy(xpath = "//*[@id=\"undoButton\"]")
    lateinit var undoButton: WebElement

    @FindBy(xpath = "//*[@id=\"redoButton\"]")
    lateinit var redoButton: WebElement

    @FindBy(xpath = "//*[@id=\"printButton\"]")
    lateinit var printButton: WebElement

    @FindBy(xpath = "//*[@id=\"zoomButton\"]")
    lateinit var zoomButton: WebElement

    @FindBy(xpath = "//*[@id=\"zoomButtonDropdown\"]")
    lateinit var zoomButtonDropdown: WebElement

    @FindBy(xpath = "//*[@id=\"textboxButton\"]")
    lateinit var textBoxButton: WebElement

    @FindBy(xpath = "//*[@id=\"insertImageMenuButton\"]")
    lateinit var insertImageMenuButton: WebElement

    @FindBy(xpath = "//*[@id=\"shapeButton\"]")
    lateinit var shapeButton: WebElement

    @FindBy(xpath = "//*[@id=\"lineMenuButton\"]")
    lateinit var lineMenuButton: WebElement

    @FindBy(xpath = "//*[@id=\"insertCommentButton\"]")
    lateinit var insertCommentButton: WebElement

    @FindBy(xpath = "//*[@id=\"workspace-container\"]")
    lateinit var workSpace: WebElement

    @FindBy(xpath = "//*[@id=\"slideBackgroundButton\"]")
    lateinit var slideBackgroundButton: WebElement

    @FindBy(xpath = "//*[@id=\"slideLayoutMenuButton\"]")
    lateinit var slideLayoutMenuButton: WebElement

    @FindBy(xpath = "//*[@id=\"slideThemeButton\"]")
    lateinit var slideThemeButton: WebElement

    @FindBy(xpath = "//*[@id=\"slideTransitionButton\"]")
    lateinit var slideTransitionButton: WebElement

    fun createNewSlide() {
        val countSlides = getSlides().count()

        newSlideButton.click()
        //wait.until { countSlides + 1 == getSlides().count() }

        undoButton.click()
        //wait.until { countSlides == getSlides().count() }

        redoButton.click()
        //wait.until { countSlides + 1 == getSlides().count() }
    }

    fun changeBackground() {
        slideBackgroundButton.click()
        val dialog = driver.findElement(By.xpath("/html/body/div[@class='modal-dialog']"))
        wait.until { dialog.isDisplayed }

        val dropdown = driver.findElement(By.xpath("//*[@id=\"punch-id-bg-color-button-container\"]/div"))
        val reset = driver.findElement(By.xpath("//*[@id=\"punch-id-bg-reset-button-container\"]/div"))
        val ready = driver.findElement(By.xpath("/html/body/div[@class='modal-dialog']/div[3]/button[2]"))
        val selectImage = driver.findElement(By.xpath("//*[@id=\"punch-id-bg-image-container\"]/div"))

        dropdown.click()
        val colors = driver.findElement(By.xpath("/html/body/div[@class='goog-menu goog-menu-vertical docs-material docs-colormenuitems goog-menu-noaccel sketchy-gradientmenuitems']"))
        wait.until { colors.isDisplayed }

        val green = driver.findElement(By.xpath("/html/body/div[@class='goog-menu goog-menu-vertical docs-material docs-colormenuitems goog-menu-noaccel sketchy-gradientmenuitems']"))
        green.click()
        dropdown.click()
        wait.until { reset.isDisplayed }
        reset.click()

        selectImage.click()
        wait.until { driver.findElement(By.xpath("/html/body/div[@class='picker modal-dialog picker-dialog']")) }

        Thread.sleep(1000)
        driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='picker modal-dialog picker-dialog']/div[2]/iframe")))

        val insertUrl = driver.findElement(By.xpath("//*[@id=\":7\"]"))
        wait.until { insertUrl.isDisplayed }
        insertUrl.click()
        val input = driver.findElement(By.xpath("//*[@id=\":v\"]"))
        wait.until { input.isDisplayed }
        input.sendKeys("https://avatars.mds.yandex.net/get-pdb/770122/25849f56-e571-489c-bbf3-739e63ac5a5a/s1200")
        Thread.sleep(500)
        val button = driver.findElement(By.xpath("//*[@id=\"picker:ap:2\"]"))
        wait.until { button.isDisplayed }
        button.click()

        driver.switchTo().defaultContent();

        val toAll = driver.findElement(By.xpath("/html/body/div[@class='modal-dialog']/div[3]/button[1]"))
        wait.until { toAll.isDisplayed }
        toAll.click()

        ready.click()
    }

    fun changeLayout() {
        slideLayoutMenuButton.click()
        val dropdown = driver.findElement(By.xpath("/html/body/div[@class='goog-menu goog-menu-vertical docs-material'][3]"))
        wait.until { dropdown.isDisplayed }

        val layouts = driver.findElements(By.xpath("/html/body/div[@class='goog-menu goog-menu-vertical docs-material'][3]/div/table/tbody/tr/td"))
        var size = layouts.size

        while (size != 0) {
            val layout = driver.findElements(By.xpath("/html/body/div[@class='goog-menu goog-menu-vertical docs-material'][3]/div/table/tbody/tr/td"))[size - 1]
            wait.until { dropdown.isDisplayed }
            layout.click()
            slideLayoutMenuButton.click()
            size--
        }
    }

    fun changeThemes() {
        slideThemeButton.click()
        val menu = driver.findElement(By.xpath("/html/body/div[@class='punch-theme-sidebar docs-material']"))
        wait.until { menu.isDisplayed }

        val themes = driver.findElements(By.xpath("/html/body/div[@class='punch-theme-sidebar docs-material']/div[2]/div/div[3]/div"))
        var size = themes.size

        themes.forEach {
            it.click()
            Thread.sleep(750)
        }
        themes[0].click()
    }

    fun zoomButtonsClick() {
        zoomButton.click()

        workSpace.click()
        workSpace.click()

        val actions = Actions(driver)
        actions.contextClick(workSpace).perform()

        zoomButtonDropdown.click()
        val dropdown = driver.findElement(By.xpath("/html/body/div[@class='goog-menu goog-menu-vertical docs-material'][4]"))
        wait.until { dropdown.isDisplayed }
        val stdSize = driver.findElement(By.xpath("/html/body/div[@class='goog-menu goog-menu-vertical docs-material'][4]/div[1]"))
        stdSize.click()
    }

    fun insertCommentButtonClick() {
        insertCommentButton.click()
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"workspace\"]/div[2]/div/div[1]")))
        driver.findElement(By.xpath("//*[@id=\"workspace\"]/div[2]/div/div[1]/div/div[2]/textarea"))
                .sendKeys("Comment")
        driver.findElement(By.xpath("//*[@id=\"workspace\"]/div[2]/div/div[1]/div[1]/div[2]/div[7]/div[1]"))
                .click()

        val decideComment = driver.findElement(By.xpath("//*[@id=\"workspace\"]/div[2]/div/div/div/div[1]/div/div/div[1]/div[3]/div[1]"))
        wait.until { decideComment.isDisplayed }
        decideComment.click()
    }

    fun shapeButtonClick() {
        shapeButton.click()
        val figures = driver.findElements(By.xpath("/html/body/div[@class='goog-menu goog-menu-vertical docs-material goog-menu-noaccel'][2]/div"))
        wait.until { figures.first().isDisplayed }

        var index = 5
        figures.forEach {
            it.click()
            val elements = driver.findElements(By.xpath("/html/body/div[@class='goog-menu goog-menu-vertical docs-material'][$index]/div[1]/table/tbody/tr/td"))
            elements.forEach { element ->
                wait.until { element.isDisplayed }
                element.click()
                workSpace.click()
                shapeButton.click()
                wait.until { figures.first().isDisplayed }
                it.click()
            }
            index++
        }
    }

    fun lineMenuButtonClick() {
        lineMenuButton.click()
        val dropdown = driver.findElement(By.xpath("/html/body/div[@class='goog-menu goog-menu-vertical docs-material goog-menu-noaccel'][3]"))
        wait.until { dropdown.isDisplayed }
        val lines = driver.findElements(By.xpath("/html/body/div[@class='goog-menu goog-menu-vertical docs-material goog-menu-noaccel'][3]/div"))

        lines.forEach {
            wait.until { lines.first().isDisplayed }
            it.click()
            workSpace.click()
            lineMenuButton.click()
        }
    }

    fun printButtonClick() {
        printButton.click()
        Thread.sleep(3000)
        val action = Actions(driver)
        val cancelButton = driver.findElement(By.xpath("//*[@id=\"button-strip\"]/paper-button[2]")).click()
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

    private fun getSlides(): List<WebElement> {
        return driver.findElements(By.cssSelector("#filmstrip > div > svg > g"))
    }
}
