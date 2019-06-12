package ru.chudakov.pages

import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidElement
import io.appium.java_client.pagefactory.AndroidFindBy
import org.openqa.selenium.support.ui.WebDriverWait

class RegistrationPage(driver: AndroidDriver<MobileElement>, wait: WebDriverWait): AbstractPage(driver, wait) {

    @AndroidFindBy(id = "com.twitter.android:id/primary_action")
    lateinit var createAccountButton: AndroidElement

    @AndroidFindBy(id = "com.twitter.android:id/signup")
    lateinit var signUpButton: AndroidElement

    @AndroidFindBy(id = "com.twitter.android:id/sign_in_text")
    lateinit var loginButton: AndroidElement

    @AndroidFindBy(id = "com.twitter.android:id/name_field")
    lateinit var nameInput: AndroidElement

    @AndroidFindBy(id = "com.twitter.android:id/phone_or_email_field")
    lateinit var phoneNumberInput: AndroidElement

    @AndroidFindBy(id = "com.twitter.android:id/cta_button")
    lateinit var nextButton: AndroidElement

    @AndroidFindBy(id = "android:id/button1")
    lateinit var approvalPhone: AndroidElement

    @AndroidFindBy(id = "com.twitter.android:id/secondary_button")
    lateinit var emailInsteadButton: AndroidElement

    @AndroidFindBy(id = "com.twitter.android:id/phone_or_email_field")
    lateinit var emailInput: AndroidElement
}
