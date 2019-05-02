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
    System.setProperty("webdriver.chrome.driver", "e:\\chromedriver_win32\\chromedriver.exe")
    val driver = ChromeDriver()
    driver.manage().window().maximize()

    val wait = WebDriverWait(driver, 10)

    val slidesPage = SlidesPage(driver, wait)
    val signInPage = SignInPage(driver, wait)
    val signUpPage = SignUpPage(driver, wait)
    val createPresentationPage = CreatePresentationPage(driver, wait)
    val presentationPage = PresentationPage(driver, wait)

    //System.out.println(registrationTest(slidesPage, signInPage, signUpPage))
    System.out.println(authorizationTest(signInPage, createPresentationPage))
    //System.out.println(createSlidesAndChangeBackgroundTest(presentationPage))
    //System.out.println(changeLayoutAndThemeTest(presentationPage))
    //System.out.println(zoomAndCommentTest(presentationPage))
    //System.out.println(figuresTest(presentationPage))
    System.out.println(textAreaTest(presentationPage))
}

fun registrationTest(slidesPage: SlidesPage, signInPage: SignInPage, signUpPage: SignUpPage): String {
    slidesPage.run {
        open()
        redirectToSignInPage(signInPage.pageUrl)
    }

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
    return "Registration test passed successfully"
}

fun authorizationTest(signInPage: SignInPage, createPresentationPage: CreatePresentationPage): String {
    signInPage.run {
        open()
        //inputUnExistLogin()
        inputRightLogin()
        //inputNotRightPassword()
        inputRightPassword(createPresentationPage.pageUrl)
    }

    createPresentationPage.run {
        open()
        createEmptyPresentation()
    }
    return "AuthorizationTest passed successfully"
}

fun createSlidesAndChangeBackgroundTest(presentationPage: PresentationPage): String {
    presentationPage.run {
        slidesMenu.createNewSlide()
        slidesMenu.changeBackground()
    }

    return "CreateSlidesAndChangeThemesTest passed successfully"
}

fun changeLayoutAndThemeTest(presentationPage: PresentationPage): String {
    presentationPage.run {
        slidesMenu.changeLayout()
        slidesMenu.changeThemes()
    }

    return "ChangeLayoutAndThemeTest passed successfully"
}

fun zoomAndCommentTest(presentationPage: PresentationPage): String {
    presentationPage.run {
        slidesMenu.zoomButtonsClick()
        slidesMenu.insertCommentButtonClick()
    }
    return "ZoomAndCommentTest passed successfully"
}

fun figuresTest(presentationPage: PresentationPage): String {
    presentationPage.slidesMenu.run {
        shapeButtonClick()
        lineMenuButtonClick()
    }
    return "FiguresTest passed successfully"
}

fun textAreaTest(presentationPage: PresentationPage): String {
    presentationPage.slidesMenu.createNewSlide()

    presentationPage.textAreaMenu.run {
        fillColorMenuButtonClick()
        lineColorMenuButtonClick()
        lineWidthMenuButtonClick()
        lineDashingMenuButtonClick()
        inputText()
        docsFontFamilyButtonClick()
        changeFontSize()
        changeText()
        changeTextColor()
        changeBackgroundTextColor()
        insetLink()
    }

    return "TextAreaTest passed successfully"
}

fun <T> List<T>.getElementsByIndexes(indexes: List<Int>): List<T> {
    val result = mutableListOf<T>()
    indexes.forEach { i -> result.add(this[i]) }
    return result
}
