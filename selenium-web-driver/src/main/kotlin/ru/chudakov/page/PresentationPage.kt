package ru.chudakov.page

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class PresentationPage(private val driver: WebDriver) : AbstractPage(driver) {
    override val pageUrl = "https://docs.google.com/presentation/u/0/";

    @FindBy(xpath = "//*[@id=\":1e\"]/div[1]")
    lateinit var createNewPresentationButton: WebElement

    @FindBy(xpath = "//*[@id=\"docs-file-menu\"]")
    lateinit var fileButton: WebElement

    @FindBy(xpath = "/html/body/div[34]")
    lateinit var fileMenuDiv: WebElement

    ///////////////////////////////////////////////File
    @FindBy(xpath = "//*[@id=\":fp\"]")
    lateinit var accessButtonFromFileMenu: WebElement

    @FindBy(xpath = "//*[@id=\":fr\"]")
    lateinit var create

    @FindBy(xpath = "//*[@id=\":fs\"]")
    lateinit var open

    @FindBy(xpath = "//*[@id=\":ft\"]")
    lateinit var importSlides

    @FindBy(xpath = "//*[@id=\":fu\"]")
    lateinit var createCopy

    @FindBy(xpath = "//*[@id=\":fx\"]")
    lateinit var downloadLike

    @FindBy(xpath = "//*[@id=\":fy\"]")
    lateinit var attachToEmail

    @FindBy(xpath = "//*[@id=\":g0\"]")
    lateinit var versionHistory

    @FindBy(xpath = "//*[@id=\":g2\"]")
    lateinit var rename

    @FindBy(xpath = "//*[@id=\":g3\"]")
    lateinit var changeLocation

    @FindBy(xpath = "//*[@id=\":g5\"]")
    lateinit var delete

    @FindBy(xpath = "//*[@id=\":g7\"]")
    lateinit var publish

    @FindBy(xpath = "//*[@id=\":g8\"]")
    lateinit var sendToСollaborators

    @FindBy(xpath = "//*[@id=\":ga\"]")
    lateinit var informationAboutDocument

    @FindBy(xpath = "//*[@id=\":gb\"]")
    lateinit var language

    @FindBy(xpath = "//*[@id=\":gc\"]")
    lateinit var pageSettings

    @FindBy(xpath = "//*[@id=\":gd\"]")
    lateinit var printSettings

    @FindBy(xpath = "//*[@id=\":ge\"]")
    lateinit var print

}
