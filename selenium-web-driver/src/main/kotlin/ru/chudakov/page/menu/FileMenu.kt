package ru.chudakov.page.menu

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class FileMenu(driver: WebDriver) : AbstractMenu(driver) {
    override val elements: Map<WebElement, WebElement>

    init {
        elements = HashMap<WebElement, WebElement>()
        var element = driver.findElement(By.xpath("//*[@id=\":fp\"]"))
    }
}
