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
        driver.startActivity(Activity("com.twitter.android", "com.twitter.app.main.MainActivity"))

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
    }

    @Test
    fun createAccountWithEmail() {
        driver.startActivity(Activity("com.twitter.android", "com.twitter.app.main.MainActivity"))

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

            phoneNumberInput.click()

            wait.until { emailInsteadButton.isDisplayed }
            emailInsteadButton.click()
            wait.until { emailInput.isDisplayed }

            emailInput.sendKeys("1")
            assertFalse { nextButton.isEnabled }

            emailInput.clear()
            emailInput.sendKeys(System.getenv("EMAIL"))
            assertFalse { nextButton.isEnabled }

            backBtn.click()
        }
    }

    @Test
    fun loginWithExistAccount() {
        driver.startActivity(Activity("com.twitter.android", "com.twitter.app.main.MainActivity"))

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
        driver.startActivity(Activity("com.twitter.android", "com.twitter.app.main.MainActivity"))

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
        }
    }

    @Test
    fun viewHomePage() {
        driver.startActivity(Activity("com.twitter.android", "com.twitter.app.main.MainActivity"))

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
        driver.startActivity(Activity("com.twitter.android", "com.twitter.app.main.MainActivity"))

        wait.until { driver.findElement(By.id("com.twitter.android:id/composer_write")).isDisplayed }
        driver.findElementByAccessibilityId("Home Tab").click()

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
    fun searchInfo() {
        driver.startActivity(Activity("com.twitter.android", "com.twitter.app.main.MainActivity"))

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
    fun editNotification() {
        driver.startActivity(Activity("com.twitter.android", "com.twitter.app.main.MainActivity"))

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
    fun sendMessage() {
        driver.startActivity(Activity("com.twitter.android", "com.twitter.app.main.MainActivity"))

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
    fun createWidget() {
        driver.startActivity(Activity("com.twitter.android", "com.twitter.app.main.MainActivity"))

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
    fun viewProfilePage() {
        driver.startActivity(Activity("com.twitter.android", "com.twitter.app.main.MainActivity"))

        wait.until { driver.findElementByAccessibilityId("Show navigation drawer").isDisplayed }
        val navigation = driver.findElementByAccessibilityId("Show navigation drawer")
        navigation.click()

        val profilePage = ProfilePage(driver)

        profilePage.run {
            wait.until { profileBtn.isDisplayed }
            profileBtn.click()

            wait.until { tweetsBtn.isDisplayed }
            tweetsBtn.click()
            wait.until { title.isDisplayed }

            wait.until { tweetsAndRepliesBtn.isDisplayed }
            tweetsAndRepliesBtn.click()
            wait.until { title.isDisplayed }

            wait.until { mediaBtn.isDisplayed }
            mediaBtn.click()
            wait.until { emptyTitle.isDisplayed }

            wait.until { likesBtn.isDisplayed }
            likesBtn.click()
            wait.until { emptyTitle.isDisplayed }
        }

        driver.findElementByAccessibilityId("Navigate up").click()
    }

    @Test
    fun createLists() {
        driver.startActivity(Activity("com.twitter.android", "com.twitter.app.main.MainActivity"))

        wait.until { driver.findElementByAccessibilityId("Show navigation drawer").isDisplayed }
        val navigation = driver.findElementByAccessibilityId("Show navigation drawer")
        navigation.click()

        val listsPage = ListsPage(driver)

        listsPage.run {
            wait.until { listsBtn.isDisplayed }
            listsBtn.click()

            wait.until { createBtn.isDisplayed }
            createBtn.click()

            wait.until { nameInput.isDisplayed }
            nameInput.sendKeys("qwerty")
            descriptionInput.sendKeys("qwerty")

            saveBtn.click()
            wait.until { createBtn.isDisplayed }
        }

        driver.findElementByAccessibilityId("Navigate up").click()
    }

    @Test
    fun deleteAllBookmarks() {
        driver.startActivity(Activity("com.twitter.android", "com.twitter.app.main.MainActivity"))

        wait.until { driver.findElementByAccessibilityId("Show navigation drawer").isDisplayed }
        val navigation = driver.findElementByAccessibilityId("Show navigation drawer")
        navigation.click()

        val bookmarksPage = BookmarksPage(driver)

        bookmarksPage.run {
            wait.until { bookmarksBtn.isDisplayed }
            bookmarksBtn.click()

            wait.until { moreOptionBtn.isDisplayed }
            moreOptionBtn.click()

            wait.until { deleteAllBtn.isDisplayed }
            deleteAllBtn.click()

            wait.until { approvalBtn.isDisplayed }
            approvalBtn.click()

            wait.until { moreOptionBtn.isDisplayed }
        }

        driver.findElementByAccessibilityId("Navigate up").click()
    }

    @AfterAll
    fun afterAll() {
        driver.quit()
    }
}
