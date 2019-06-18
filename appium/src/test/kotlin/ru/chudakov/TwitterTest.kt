package ru.chudakov

import io.appium.java_client.MobileElement
import io.appium.java_client.android.Activity
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidTouchAction
import io.appium.java_client.remote.MobileCapabilityType
import io.appium.java_client.touch.WaitOptions
import io.appium.java_client.touch.offset.PointOption
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.openqa.selenium.By
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.support.ui.WebDriverWait
import ru.chudakov.pages.*
import java.net.URL
import java.time.Duration.ofMillis
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TwitterTest {
    private val driver: AndroidDriver<MobileElement>
    private val wait: WebDriverWait
    private val action: AndroidTouchAction

    init {
        val capabilities = DesiredCapabilities()

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android")
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Custom Phone")
        capabilities.setCapability(MobileCapabilityType.UDID, "192.168.147.105:5555")
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2")
        capabilities.setCapability("appPackage", "com.twitter.android")
        capabilities.setCapability("appActivity", "com.twitter.app.main.MainActivity")
        capabilities.setCapability("appWaitActivity", "com.twitter.app.main.MainActivity")

        driver = AndroidDriver(URL("http://127.0.0.1:4723/wd/hub"), capabilities)
        wait = WebDriverWait(driver, 10)
        action = AndroidTouchAction(driver)

        wait.until { driver.findElementById("android:id/button1").isDisplayed }
        val firstButton = driver.findElementById("android:id/button1")
        firstButton.click()

        wait.until { driver.findElementById("com.android.packageinstaller:id/permission_allow_button").isDisplayed }
        val allowButton = driver.findElementById("com.android.packageinstaller:id/permission_allow_button")
        allowButton.click()
        wait.until { driver.findElementById("com.twitter.android:id/toolbar").isDisplayed }
    }

    @Test
    fun createAccountWithPhone() {
        val accManager = AccountsManagePage(driver)

        accManager.run {
            wait.until { imgBtn.isDisplayed }
            imgBtn.click()

            wait.until { accountBtn.isDisplayed }
            accountBtn.click()

            wait.until { addExistAccountBtn.isDisplayed }
            addExistAccountBtn.click()
        }

        val registrationPage = RegistrationPage(driver)

        registrationPage.run {
            wait.until { signUpButton.isDisplayed }
            signUpButton.click()

            wait.until { nameInput.isDisplayed }
            nameInput.sendKeys("Matvey")

            phoneNumberInput.sendKeys("1")
            nextButton.click()
            assertFalse { nextButton.isEnabled }

            phoneNumberInput.clear()
            phoneNumberInput.sendKeys(System.getenv("PHONE_NUMBER"))
            nextButton.click()
            assertFalse { nextButton.isEnabled }

            backBtn.click()
        }

        val authorizationPage = AuthorizationPage(driver)

        authorizationPage.run {
            wait.until { loginInput.isDisplayed }
            loginInput.sendKeys(System.getenv("PHONE_NUMBER"))
            passwordInput.clear()
            passwordInput.sendKeys(System.getenv("TWITTER_PASSWORD"))
            loginButton.click()
        }
    }

    @Test
    fun createAccountWithEmail() {
        val accManager = AccountsManagePage(driver)

        accManager.run {
            wait.until { imgBtn.isDisplayed }
            imgBtn.click()

            wait.until { accountBtn.isDisplayed }
            accountBtn.click()

            wait.until { addExistAccountBtn.isDisplayed }
            addExistAccountBtn.click()
        }

        val registrationPage = RegistrationPage(driver)

        registrationPage.run {
            wait.until { signUpButton.isDisplayed }
            signUpButton.click()

            wait.until { nameInput.isDisplayed }
            nameInput.sendKeys("Matvey")

            wait.until { emailInsteadButton.isDisplayed }
            emailInsteadButton.click()
            wait.until { emailInput.isDisplayed }

            emailInput.sendKeys("1")
            assertFalse { nextButton.isEnabled }
            assertFalse { nextButton.isEnabled }

            emailInput.clear()
            emailInput.sendKeys(System.getenv("EMAIL"))
            emailInput.sendKeys("1")
            assertFalse { nextButton.isEnabled }

            backBtn.click()
        }

        val authorizationPage = AuthorizationPage(driver)

        authorizationPage.run {
            wait.until { loginInput.isDisplayed }
            loginInput.sendKeys(System.getenv("PHONE_NUMBER"))
            passwordInput.clear()
            passwordInput.sendKeys(System.getenv("TWITTER_PASSWORD"))
            loginButton.click()
        }
    }

    @Test
    fun loginWithExistAccount() {
        val authorizationPage = AuthorizationPage(driver)

        driver.startActivity(Activity("com.twitter.android", "LoginActivity"))

        authorizationPage.run {
            wait.until { resetPasswordButton.isDisplayed }
            resetPasswordButton.click()

            wait.until { navigateUp.isDisplayed }
            navigateUp.click()

            wait.until { loginInput.isDisplayed }
            loginInput.clear()
            loginInput.sendKeys(System.getenv("PHONE_NUMBER"))
            passwordInput.clear()
            passwordInput.sendKeys(System.getenv("TWITTER_PASSWORD"))
            loginButton.click()

            wait.until { driver.findElement(By.id("com.twitter.android:id/toolbar")) != null }

            assertNotEquals("LoginActivity", driver.currentActivity())
        }
    }

    @Test
    fun loginWithUnExistAccount() {
        val accManager = AccountsManagePage(driver)

        accManager.run {
            wait.until { imgBtn.isDisplayed }
            imgBtn.click()

            wait.until { accountBtn.isDisplayed }
            accountBtn.click()

            wait.until { addExistAccountBtn.isDisplayed }
            addExistAccountBtn.click()
        }

        val authorizationPage = AuthorizationPage(driver)

        authorizationPage.run {
            wait.until { loginInput.isDisplayed }
            loginInput.sendKeys("1")
            passwordInput.sendKeys("1")
            loginButton.click()

            wait.until { loginInput.isDisplayed }
            loginInput.clear()
            passwordInput.clear()

            wait.until { loginInput.isDisplayed }
            loginInput.sendKeys(System.getenv("PHONE_NUMBER"))
            passwordInput.clear()
            passwordInput.sendKeys(System.getenv("TWITTER_PASSWORD"))
            loginButton.click()
        }
    }

    @Test
    fun home() {
        val homePage = HomePage(driver)

        homePage.run {
            wait.until { view.isDisplayed }
            view.click()

            wait.until { toolbar.isDisplayed }
            tweetCurationAction.click()

            wait.until { gripper.isDisplayed }
            action.press(PointOption.point(400, 150))
            action.release()
            action.perform()

            wait.until { toolbar.isDisplayed }
            timelineSwitch.click()
            wait.until { gripper.isDisplayed }
            action.press(PointOption.point(400, 150))
            action.release()
            action.perform()

            wait.until { toolbar.isDisplayed }
            imageButton.click()
            wait.until { drawer.isDisplayed }
            action.press(PointOption.point(700, 400))
            action.release()
            action.perform()
            wait.until { toolbar.isDisplayed }
        }
    }

    @Test
    fun createTwit() {
        //driver.startActivity(Activity("com.twitter.composer", "ComposerActivity"))
        wait.until { driver.findElement(By.id("com.twitter.android:id/composer_write")).isDisplayed }

        val createTwitBtn = driver.findElement(By.id("com.twitter.android:id/composer_write"))
        wait.until { createTwitBtn.isDisplayed }
        createTwitBtn.click()

        val createTwitPage = CreateTwitPage(driver)

        createTwitPage.run {
            wait.until { textArea.isDisplayed }

            imageView.click()
            wait.until { accountFrame.isDisplayed }
            action.press(PointOption.point(350, 350))
            action.release()
            action.perform()
            wait.until { textArea.isDisplayed }

            textInput.sendKeys("qwerty")

            gifBtn.click()
            wait.until { gifView.isDisplayed }

            gifView.click()
            wait.until { gifImageView.isDisplayed }

            gifImageView.click()
            wait.until { textArea.isDisplayed }

            twitButton.click()
        }

        wait.until { driver.findElement(By.id("com.twitter.android:id/composer_write")).isDisplayed }
    }

    @Test
    fun search() {
        val searchPage = SearchPage(driver)

        searchPage.run {
            wait.until { searchBtn.isDisplayed }
            searchBtn.click()

            wait.until { searchTwitBtn.isDisplayed }
            searchTwitBtn.click()

            wait.until { searchTwitInput.isDisplayed }
            searchTwitInput.sendKeys("a")

            wait.until { searchResult.isDisplayed }
            searchResult.click()

            wait.until { settingBtn.isDisplayed }
            settingBtn.click()

            wait.until { title.isDisplayed }
            title.click()

            wait.until { settingsToolbar.isDisplayed }
            closeBtn.click()

            wait.until { settingBtn.isDisplayed }
            closeBtn.click()

            wait.until { searchBtn.isDisplayed }
        }
    }

    @Test
    fun notification() {
        val notificationPage = NotificationPage(driver)

        notificationPage.run {
            wait.until { notificationBtn.isDisplayed }
            notificationBtn.click()

            wait.until { mentions.isDisplayed }
            mentions.click()

            wait.until { settingsBtn.isDisplayed }
            settingsBtn.click()

            wait.until { backwardBtn.isDisplayed }
            backwardBtn.click()
            wait.until { settingsBtn.isDisplayed }
        }
    }

    @Test
    fun message() {
        val messagePage = MessagePage(driver)

        messagePage.run {
            wait.until { messageBtn.isDisplayed }
            messageBtn.click()

            wait.until { createMessage.isDisplayed }
            createMessage.click()

            wait.until { profile.isDisplayed }
            profile.click()

            wait.until { nextBtn.isDisplayed }
            nextBtn.click()

            wait.until { tweetText.isDisplayed }
            tweetText.click()

            wait.until { tweetText.isDisplayed }
            tweetText.sendKeys("qwerty")

            wait.until { sendBtn.isDisplayed }
            sendBtn.click()

            wait.until { backwardBtn.isDisplayed }
            backwardBtn.click()

            wait.until { messageBtn.isDisplayed }
        }
    }

    @Test
    fun widget() {
        driver.navigate().back()
        if (driver.findElementsByAccessibilityId("Apps list").isNotEmpty()) {
            val appsListBtn = driver.findElementByAccessibilityId("Apps list") as MobileElement
            appsListBtn.click()
        }
        wait.until { driver.findElementById("com.android.launcher3:id/search_box_input").isDisplayed }

        val size = driver.manage().window().size
        val anchor = (size.width * 0.1).toInt()
        val startPoint = (size.height * 0.9).toInt()
        val endPoint = (size.height * 0.1).toInt()

        action
                .press(PointOption.point(anchor, startPoint))
                .waitAction(WaitOptions.waitOptions(ofMillis(1000)))
                .moveTo(PointOption.point(anchor, endPoint))
                .release().perform()

        action
                .longPress(PointOption.point(290, 1060))
                .moveTo(PointOption.point(anchor, startPoint))
                .release().perform()
    }

    @Test
    fun profile() {

    }

    @AfterAll
    fun afterAll() {
        driver.quit()
    }
}
