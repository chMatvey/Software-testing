package ru.chudakov.page.menu

class Menu(val openDialogButtons: Map<String, String>) {

    fun getFileMenuMap(): Map<String, String> {
        return mapOf(
                "//*[@id=\":fq\"]" to "/html/body/div[38]",
                "//*[@id=\":ft\"]" to "//*[@id=\"yzn9znss8rqo\"]",
                "//*[@id=\":fu\"]" to "//*[@id=\"750pz3jz83mj\"]",
                "//*[@id=\":fv\"]" to "/html/body/div[40]",
                "//*[@id=\":fz\"]" to "",
                "//*[@id=\":g3\"]" to "",
                "" to "",
                "" to "",
                "" to "",
                "" to "",
                "" to "",
                "" to ""
        )
    }
}
