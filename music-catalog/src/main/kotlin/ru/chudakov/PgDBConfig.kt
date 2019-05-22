package ru.chudakov

import java.lang.System.getenv

object PgDBConfig {
    val url = getenv("DATABASE_URL")!!
    val driver = getenv("DATABASE_DRIVER")!!
    val userName = getenv("DATABASE_USERNAME")!!
    val password = getenv("DATABASE_PASSWORD")!!
}
