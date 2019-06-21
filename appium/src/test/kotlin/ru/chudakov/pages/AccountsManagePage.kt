package ru.chudakov.pages

import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidElement
import io.appium.java_client.pagefactory.AndroidFindBy
import org.openqa.selenium.support.ui.WebDriverWait

class AccountsManagePage(driver: AndroidDriver<MobileElement>) : AbstractPage(driver) {

    @AndroidFindBy(className = "android.widget.ImageButton")
    lateinit var imgBtn: AndroidElement

    @AndroidFindBy(id = "com.twitter.android:id/accounts")
    lateinit var accountBtn: AndroidElement

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.TextView[1]")
    lateinit var createNewAccountBtn: AndroidElement

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.TextView[2]")
    lateinit var addExistAccountBtn: AndroidElement

    fun openCreateAccountActivity(wait: WebDriverWait) {
        wait.until { imgBtn.isDisplayed }
        imgBtn.click()

        wait.until { accountBtn.isDisplayed }
        accountBtn.click()

        wait.until { addExistAccountBtn.isDisplayed }
        addExistAccountBtn.click()
    }
}
