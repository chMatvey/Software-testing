package ru.chudakov

import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import ru.chudakov.page.*

fun main() {
    System.setProperty("webdriver.chrome.driver", "e:\\chromedriver_win32\\chromedriver.exe")
    val driver = ChromeDriver()
    val mainPage = MainPage(driver)
    val signInPage = SignInPage(driver)
    val signUpPage = SignUpPage(driver)
    val createOrOpenPresentationPage = CreateOrOpenPresentationPage(driver)
    val presentationPage = PresentationPage(driver)
    driver.quit()

//    System.out.println(redirectSignInPageFromMainPageTest(mainPage, signInPage.pageUrl))
//    System.out.println(signInAndSignUpPagesTest(signInPage, signUpPage))
    System.out.println(authorizationTest(signInPage))
    System.out.println(presentationPagesTest(createOrOpenPresentationPage, presentationPage))
}

fun redirectSignInPageFromMainPageTest(mainPage: MainPage, signInPageUrl: String): String {
    mainPage.run {
        open()
        openPresentationButton.click()
        if (!verifyUrl(signInPageUrl))
            return "Test failed, openPresentationButton doesn't function properly"
    }
    return "redirectSignInPageFromMainPageTest passed successfully"
}

fun signInAndSignUpPagesTest(signInPage: SignInPage, signUpPage: SignUpPage): String {
    val str = "lol"

    signInPage.run {
        open()
        signUpButton.click()
        Thread.sleep(1000)
        createAccountForYouButton.click()
        Thread.sleep(2000)
        if (!verifyUrl(signUpPage.pageUrl))
            return "Test failed, signUpButton doesn't function properly"

        signUpPage.run {
            firstNameInput.sendKeys(str)
            lastNameInput.sendKeys(str)
            userNameInput.sendKeys(str)
            passwordInput.sendKeys(str)

            confirmPasswordInput.sendKeys(str)
            nextButton.click()
            if (!passwordMustBeEightCharOrMoreDiv.isDisplayed)
                return "Test failed, passwordMustBeEightCharOrMore doesn't display"

            passwordInput.clear()
            confirmPasswordInput.clear()

            passwordInput.sendKeys(str + str + str)
            confirmPasswordInput.sendKeys(str + str)
            nextButton.click()
            if (!passwordMustBeMatchDiv.isDisplayed)
                return "Test failed, passwordMustBeMatch doesn't display"

            signInButton.click()
            if (!verifyUrl(signInPage.pageUrl))
                return "Test failed, signInButton doesn't function properly"
            Thread.sleep(2000)
        }

        signInPage.run {
            emailInput.sendKeys(str)
            nextEmailButton.click()
            if (!accountDoesNotExist.isDisplayed)
                return "Test failed, couldNotFindAccountDiv doesn't display"
            emailInput.clear()
            Thread.sleep(2000)
        }

        return "SignInAndSignUpPagesTest passed successfully"
    }
}

fun authorizationTest(signInPage: SignInPage): String {
    signInPage.run {
        open()
        emailInput.sendKeys(System.getenv("google_login"))
        nextEmailButton.click()
        Thread.sleep(2000)
        passwordInput.sendKeys(System.getenv("google_password"))
        nextPasswordButton.click()
        Thread.sleep(4000)
    }

    return "authorizationTest passed successfully"
}

fun presentationPagesTest(createOrOpenPresentationPage: CreateOrOpenPresentationPage,
                          presentationPage: PresentationPage): String {
    createOrOpenPresentationPage.run {
        open()
        createOrOpenPresentationPage.createEmptyPresentationButton.click()
        if (!verifyUrl(presentationPage.pageUrl))
            return "Test failed, createEmptyPresentationButton doesn't function properly"
    }

    presentationPage.run {
        for (button: WebElement in getDocsMenuButtons()) {
            if (button.isEnabled && button.isDisplayed) button.click()
            Thread.sleep(1000)
        }
        getDocsMenuSelects()
    }

    return "createNewPresentationTest passed successfully"
}
