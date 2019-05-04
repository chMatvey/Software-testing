package ru.chudakov.page

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.NoAlertPresentException as NoAlertPresentException1

//Базовый класс для всех объектных моделей веб-страниц
abstract class AbstractPage(protected val driver: WebDriver, protected val wait: WebDriverWait) {
    abstract val pageUrl: String

    init {
        initElements()
    }

    private fun initElements() {
        //Фабрика, инициализурующая поля унаследованных классов
        PageFactory.initElements(driver, this)
    }

    //Функция открывающий веб-страницу по заддонуму url в поле pageUrl
    fun open() {
        if (!driver.currentUrl.startsWith(pageUrl)) {
            driver.get(pageUrl)
        }
        //Явное ожиадание откртия веб-страницы веб-браузером
        wait.until { it.currentUrl.startsWith(pageUrl) }
    }

    //Функция перезагружающая веб-страницу
    fun reload() {
        if (driver.currentUrl.startsWith(pageUrl)) {
            driver.navigate().refresh()
        }
        //Во время перезангрузки веб страницы приложение может запросить подтверждение
        closeAlert()

        wait.until { it.currentUrl.startsWith(pageUrl) }
        wait.until { driver.findElement(By.xpath("html")).isDisplayed }
    }

    //Закрытие алерта, в случае если приложение его отобразило
    private fun closeAlert() {
        try {
            Thread.sleep(500)
            val alert = driver.switchTo().alert()
            alert.accept()
            driver.switchTo().defaultContent()
        }
        catch (e: NoAlertPresentException1) {
        }
    }
}

