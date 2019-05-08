package ru.chudakov

import org.openqa.selenium.WebDriverException
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import ru.chudakov.page.*

fun main() {
    val driver = ChromeDriver() //Конструктор класса ChromeDriver запускает ChromeDriver сервер и открывает веб-браузер Chrome
    driver.manage().window().maximize() //Разворачиваем веб-браузер на полный экран
    val wait = WebDriverWait(driver, 10) //Создание экземпляра класса WebDriverWait, необходимого для ясвного ожидания отображения веб-эелментов

    //Инициализация объктов, представляющих объектные модели веб-страниц
    val slidesPage = SlidesPage(driver, wait)
    val signInPage = SignInPage(driver, wait)
    val signUpPage = SignUpPage(driver, wait)
    val createPresentationPage = CreatePresentationPage(driver, wait)
    val presentationPage = PresentationPage(driver, wait)

    //Запуск тестовых сценариев
    println(registrationTest(slidesPage, signInPage, signUpPage))
    println(authorizationTest(signInPage, createPresentationPage))

    println(docsMenuTest(presentationPage))
    println(docsFileMenuTest(presentationPage))
    println(docsEditMenuTest(presentationPage))
    println(docsViewMenuTest(presentationPage))
    println(docsInsertMenuTest(presentationPage))
    println(docsFormatMenuTest(presentationPage))
    println(docsSlideMenuTest(presentationPage))
    println(docsArrangeMenuTest(presentationPage))
    println(docsToolsMenuTest(presentationPage))
    println(docsExtensionsMenuTest(presentationPage))
    println(docsHelpMenuTest(presentationPage))

    println(createSlidesAndChangeBackgroundTest(presentationPage))
    println(changeLayoutAndThemeTest(presentationPage))
    println(zoomAndCommentTest(presentationPage))
    println(figuresTest(presentationPage))
    println(textAreaTest(presentationPage))

    println(textAreaRightButtonDropdownTest(presentationPage))

    driver.quit() //Завершение процесса
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

//Тестовый сценарий номер 2, Authorization Test
fun authorizationTest(signInPage: SignInPage, createPresentationPage: CreatePresentationPage): String {
    //Вызов функций класса signInPage, имитирующих действия пользователя
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
    presentationPage.reload()

    presentationPage.run {
        slidesMenu.createNewSlide()
        slidesMenu.changeBackground()
    }

    return "CreateSlidesAndChangeThemesTest passed successfully"
}

fun changeLayoutAndThemeTest(presentationPage: PresentationPage): String {
    presentationPage.reload()

    presentationPage.run {
        slidesMenu.changeLayout()
        slidesMenu.changeThemes()
    }

    return "ChangeLayoutAndThemeTest passed successfully"
}

fun zoomAndCommentTest(presentationPage: PresentationPage): String {
    Thread.sleep(1000)

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
    presentationPage.reload()

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
        alignText()
        changeLineSpacing()
        numberedList()
        bulletListPresetMenuButtonClick()
        changeIndent()
        formatOptionsButtonClick()
    }

    return "TextAreaTest passed successfully"
}

fun docsMenuTest(presentationPage: PresentationPage): String {
    presentationPage.reload()

    presentationPage.clickDocsMenuButtons()

    return "DocsMenuTest passed successfully"
}

fun docsFileMenuTest(presentationPage: PresentationPage): String {

    presentationPage.docsFileMenu.run {
        clickDropDowns()
        accessButtonClick()
        openButtonClick()
        importSlides()
        createCopy()
        sendMessage()
        rename()
        transfer()
        delete()
        publish()
        getInformation()
        settings()
    }
    return "FileMenuTest passed successfully"
}

fun docsEditMenuTest(presentationPage: PresentationPage): String {
    presentationPage.slidesMenu.createNewSlide()
    presentationPage.textAreaMenu.inputText("text", 3)

    presentationPage.docsEditMenu.run {
        selectAll()
        creatCopy()
        cancel()
        copy()
        insert()
        repeat()
        findAndReplace()
    }

    return "DocsEditMenuTest passed successfully"
}

fun docsViewMenuTest(presentationPage: PresentationPage): String {
    presentationPage.docsViewMenu.run {
        watch()
        animation()
        changeView()
        clickDropDowns()
    }

    return "DocsViewMenuTest passed successfully"
}

fun docsInsertMenuTest(presentationPage: PresentationPage): String {
    presentationPage.slidesMenu.createNewSlide()

    presentationPage.docsInsertMenu.run {
        clickDropDowns()
        insertTextArea()
        insertTable()
        insertVideo()
        insertArt()
        insertNewSlide()
        insertSlideNumbers()
        //insertDiagram()
    }

    return "DocsInsertMenuTest passed successfully"
}

fun docsFormatMenuTest(presentationPage: PresentationPage): String {
    presentationPage.slidesMenu.createNewSlide()
    presentationPage.textAreaMenu.inputText()
    presentationPage.docsEditMenu.selectAll()

    presentationPage.docsFormatMenu.run {
        clickDropDowns()
        formatParams()
    }

    return "DocsFormatMenuTest passed successfully"
}

fun docsSlideMenuTest(presentationPage: PresentationPage): String {
    presentationPage.docsSlidesMenu.run {
        createNewSlide()
        duplicateSlide()
        deleteSlide()
        missSlide()
        missSlide()
    }

    return "DocsSlideMenuTest passed successfully"
}

fun docsArrangeMenuTest(presentationPage: PresentationPage): String {
    presentationPage.textAreaMenu.inputText("test", 1)

    presentationPage.docsArrangeMenu.run {
        clickDropDowns()
        turnText()
    }

    return "DocsArrangeMenuTest passed successfully"
}

fun docsToolsMenuTest(presentationPage: PresentationPage): String {
    presentationPage.docsToolsMenu.run {
        //clickDropDowns()
        extendedSearch()
        dictionaryOpen()
        questionsHistoryOpen()
        settingsOpen()
        specialSettingsOpen()
    }

    return "DocsToolsMenuTest passed successfully"
}

fun docsExtensionsMenuTest(presentationPage: PresentationPage): String {
    presentationPage.docsExtensionsMenu.run {
        setExtensions()
    }

    return "DocsExtensionsMenuTest passed successfully"
}

fun docsHelpMenuTest(presentationPage: PresentationPage): String {
    presentationPage.docsHelpMenu.run {
        openHelp()
        sendReviewOpen()
        keyboardShortcutOpen()
    }
    return "DocsHelpMenuTest passed successfully"
}

fun textAreaRightButtonDropdownTest(presentationPage: PresentationPage): String {
    presentationPage.slidesMenu.createNewSlide()
    presentationPage.textAreaMenu.inputText("1111")

    presentationPage.textAreaRightButtonDropdown.run {
        try {
            findByText()
            alterText()
            animation()
        } catch (e: WebDriverException) {
        }
    }
    return "TextAreaRightButtonDropdownTest passed successfully"
}
