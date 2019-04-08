package ru.chudakov

import org.openqa.selenium.TimeoutException
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import ru.chudakov.page.*
import java.lang.Exception

fun main() {
    System.setProperty("webdriver.chrome.driver", "c:\\chromedriver_win32\\chromedriver.exe")
    val driver = ChromeDriver()
    driver.manage().window().maximize()

    val wait = WebDriverWait(driver, 5)

    val slidesPage = SlidesPage(driver, wait)
    val signInPage = SignInPage(driver, wait)
    val signUpPage = SignUpPage(driver, wait)
    val createOrOpenPresentationPage = CreateOrOpenPresentationPage(driver, wait)
    val presentationPage = PresentationPage(driver, wait)

    //System.out.println(slidesPageTest(slidesPage, signInPage.pageUrl))
    //System.out.println(signInAndSignUpPageTest(signInPage, signUpPage))
    System.out.println(authorizationTest(signInPage, createOrOpenPresentationPage.pageUrl))
    System.out.println(presentationPagesTest(createOrOpenPresentationPage, presentationPage))

//    try {
////        System.out.println(slidesPageTest(slidesPage, signInPage.pageUrl))
////        System.out.println(signInAndSignUpPageTest(signInPage, signUpPage))
//        System.out.println(authorizationTest(signInPage, createOrOpenPresentationPage.pageUrl))
//        System.out.println(presentationPagesTest(createOrOpenPresentationPage, presentationPage))
//    } catch (exception: TimeoutException) {
//        System.out.println("Test failed")
//        System.out.println(exception.message)
//    } finally {
//        driver.quit()
//    }
}

fun slidesPageTest(slidesPage: SlidesPage, signInPageUrl: String): String {
    slidesPage.run {
        open()
        redirectToSignInPage(signInPageUrl)
    }
    return "SlidesPageTest passed successfully"
}

fun authorizationTest(signInPage: SignInPage, resultPageUrl: String): String {
    signInPage.run {
        open()
        signIn(resultPageUrl)
    }
    return "SignInPageTest passed successfully"
}

fun signInAndSignUpPageTest(signInPage: SignInPage, signUpPage: SignUpPage): String {
    signInPage.run {
        open()
        redirectToSignUpPage(signUpPage.pageUrl)
    }

    signUpPage.run {
        open()
        inputNameAndSurname()
        inputLatinCharsToUserNameInput()
        inputShortPassword()
        clickNextButton(ExpectedConditions.visibilityOfAllElements(passwordMustBeEightCharOrMoreDiv))

        inputNotEqualPasswords()
        clickNextButton(ExpectedConditions.visibilityOfAllElements(passwordMustBeMatchDiv))

        inputPassword()
        clickNextButton(ExpectedConditions.visibilityOfAllElements(userNameMustContainOnlyLatinCharsDiv))

        inputShortUserName()
        clickNextButton(ExpectedConditions.visibilityOfAllElements(userNameMustContainMinSixCharsDiv))

        signInButton.click()
    }
    return "SignInAndSignUpPageTest passed successfully"
}

fun presentationPagesTest(createOrOpenPresentationPage: CreateOrOpenPresentationPage,
                          presentationPage: PresentationPage): String {
    createOrOpenPresentationPage.run {
        open()
        createEmptyPresentation(presentationPage.pageUrl)
    }

    presentationPage.run {
        //clickDocsMenuButtons()
        //createNewSlide()
        //zoomButtonsClick()
        //textBoxButtonClick()
        insertImageMenuButtonClick()
    }

    return "CreateNewPresentationTest passed successfully"
}
