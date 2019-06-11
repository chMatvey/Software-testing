package ru.chudakov

import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.remote.MobileCapabilityType
import org.junit.jupiter.api.Test
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.support.ui.WebDriverWait
import java.net.URL


class SimpleTest {
    private val driver: AndroidDriver<MobileElement>
    private val wait: WebDriverWait
    private val deviceName = "Custom Device"

    init {
        val capabilities = DesiredCapabilities()

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android")
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Custom Phone")
        capabilities.setCapability(MobileCapabilityType.UDID, "192.168.147.105:5555")
        //capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2")
        capabilities.setCapability("appPackage", "com.android.calculator2");
        capabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
        capabilities.setCapability("appWaitActivity","com.android.calculator2.Calculator");
        driver = AndroidDriver(URL("http://127.0.0.1:4723/wd/hub"), capabilities)

        wait = WebDriverWait(driver, 10)
    }

    @Test
    fun test() {

    }
}
