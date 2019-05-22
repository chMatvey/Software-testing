package ru.chudakov

import org.junit.Test

import org.junit.Assert.*

class PgDBManagerTest {
    private val dbManager: DBManager = PgDBManager()

    @Test
    fun addComposition() {
        print(dbManager.addComposition("name1", "author", "pop"))
    }
}
