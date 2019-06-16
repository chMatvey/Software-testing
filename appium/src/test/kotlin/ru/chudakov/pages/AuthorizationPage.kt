package ru.chudakov.pages

import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidElement
import io.appium.java_client.pagefactory.AndroidFindBy

class AuthorizationPage(driver: AndroidDriver<MobileElement>) : AbstractPage(driver) {

    @AndroidFindBy(id = "com.twitter.android:id/password_reset")
    lateinit var resetPasswordButton: AndroidElement

    @AndroidFindBy(accessibility = "Navigate up")
    lateinit var navigateUp: AndroidElement

    @AndroidFindBy(id = "com.twitter.android:id/login_identifier")
    lateinit var loginInput: AndroidElement

    @AndroidFindBy(id = "com.twitter.android:id/login_password")
    lateinit var passwordInput: AndroidElement

    @AndroidFindBy(id = "com.twitter.android:id/login_login")
    lateinit var loginButton: AndroidElement
}
