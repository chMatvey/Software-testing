package ru.chudakov

import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.selectAll
import org.junit.Test
import kotlin.test.assertTrue
import org.jetbrains.exposed.sql.transactions.transaction
import ru.chudakov.dao.Authors
import ru.chudakov.dao.CompositionDao
import ru.chudakov.dao.Compositions

class PgDBManagerTest {
    private val dbManager: DBManager = PgDBManager()

    @Test
    fun deleteComposition() {
        dbManager.deleteCompositions("name", "author")
        val compositions = dbManager.getAllCompositions()
        assertTrue { !compositions.any { it.name == "name" && it.author.name == "author" } }
    }
}
