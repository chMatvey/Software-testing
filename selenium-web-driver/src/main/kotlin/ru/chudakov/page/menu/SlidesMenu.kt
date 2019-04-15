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
        val ready = driver.findElement(By.xpath("/html/body/div[33]/div[3]/button[2]"))
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
        val insertUrl = driver.findElement(By.xpath("//div[@class='Nf-ml-oi Nf-ll-Zb-oi']"))
        wait.until { insertUrl.isDisplayed }
        insertUrl.click()
        val input = driver.findElement(By.xpath("//*[@id=\":v\"]"))
        wait.until { input.isDisplayed }
        input.sendKeys("https://images.wallpaperscraft.ru/image/gepard_lezhat_dikaya_koshka_pyatnistyy_1182_1920x1080.jpg")
        val button = driver.findElement(By.xpath("//*[@id=\"picker:ap:2\"]"))
        wait.until { button.isDisplayed }
        button.click()

        val toAll = driver.findElement(By.xpath("/html/body/div[@class='modal-dialog']/div[3]/button[1]"))
        toAll.click()

        ready.click()
    }

    fun changeLayout() {
        val dropdown = driver.findElement(By.xpath("/html/body/div[@class='goog-menu goog-menu-vertical docs-material'][3]"))
        val trs = dropdown.findElements(By.xpath("/div/table/tbody/tr"))

        val layouts = mutableListOf<WebElement>()
        trs.forEach { layouts.addAll(it.findElements(By.xpath("/td"))) }

        layouts.forEach { layout ->
            slideLayoutMenuButton.click()
            wait.until { dropdown.isDisplayed }
            layout.click()
            Thread.sleep(100)
        }
    }

    fun changeThemes() {

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

    private fun getSlides(): List<WebElement> {
        return driver.findElements(By.cssSelector("#filmstrip > div > svg > g"))
    }
}
