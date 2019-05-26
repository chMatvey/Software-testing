package ru.chudakov

fun main() {
    val manager = PgDBManager()
    val app = ConsoleApplication(manager)

    app.run()
}
