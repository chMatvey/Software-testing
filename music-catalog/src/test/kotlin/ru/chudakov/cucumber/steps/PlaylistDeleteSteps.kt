package ru.chudakov.cucumber.steps

import cucumber.api.java.Before
import cucumber.api.java8.En
import org.jetbrains.exposed.sql.transactions.transaction
import ru.chudakov.DBManager
import ru.chudakov.PgDBManager
import ru.chudakov.dao.PlaylistDao
import ru.chudakov.dao.Playlists
import kotlin.test.assertFalse

class PlaylistDeleteSteps : En {
    private val dbManager: DBManager = PgDBManager()

    lateinit var name: String

    var playlists: List<PlaylistDao> = emptyList()

    init {
        Given("we have unnecessary playlist") {
            name = "name"
        }

        When("we try delete playlist") {
            dbManager.deletePlaylist(name)
        }

        When("we get all playlists") {
            playlists = transaction { PlaylistDao.all().toList() }
        }

        Then("playlists does not contain playlist") {
            assertFalse { playlists.any { it.name == name } }
        }
    }

    @Before("@deletePlaylist")
    fun before() {
        transaction {
            PlaylistDao.find { Playlists.name eq "name" }.firstOrNull() ?: PlaylistDao.new { name = "name" }
        }
    }
}
