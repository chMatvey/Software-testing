package ru.chudakov

import org.jetbrains.exposed.dao.with
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.SizedCollection
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.junit.Test
import kotlin.test.assertTrue
import org.jetbrains.exposed.sql.transactions.transaction
import ru.chudakov.dao.*
import ru.chudakov.data.Composition

class PgDBManagerTest {
    private val dbManager: DBManager = PgDBManager()

    @Test
    fun deleteComposition() {
        dbManager.deleteCompositions("name", "author")
        val compositions = dbManager.getAllCompositions()
        assertTrue { !compositions.any { it.name == "name" && it.author.name == "author" } }
    }

    @Test
    fun addCompositionToPlaylist() {
        transaction {
            dbManager.addComposition("composition", "author", "pop")
            val playlist = PlaylistDao.find { Playlists.name eq "playlist" }
                    .firstOrNull() ?: PlaylistDao.new { name = "playlist" }
            val composition = CompositionDao.find { Compositions.name eq "name" }.firstOrNull() ?: return@transaction

            val compositions = playlist.compositions.toMutableList()
            compositions.add(composition)

            playlist.compositions = SizedCollection(compositions)
        }
    }

    @Test
    fun deletePlaylist() {
        transaction {
            PlaylistDao.new { name = "name1" }
            PlaylistDao.find { Playlists.name eq "name1" }.firstOrNull()?.delete()
        }
    }
}
