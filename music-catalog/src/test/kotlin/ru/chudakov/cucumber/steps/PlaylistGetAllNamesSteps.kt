package ru.chudakov.cucumber.steps

import cucumber.api.java.After
import cucumber.api.java.Before
import cucumber.api.java8.En
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import ru.chudakov.DBManager
import ru.chudakov.PgDBManager
import ru.chudakov.dao.PlaylistDao
import ru.chudakov.dao.Playlists
import kotlin.test.assertTrue

class PlaylistGetAllNamesSteps : En {
    private val dbManager: DBManager = PgDBManager()

    var playlistNames: List<String> = emptyList()

    init {
        When("we try get all playlist names") {
            playlistNames = dbManager.getAllPlaylistNames()
        }

        Then("playlist names list is not empty") {
            assertTrue { playlistNames.isNotEmpty() }
        }
    }

    @Before("@GetAllPlaylistNames")
    fun before() {
        transaction {
            PlaylistDao.find { Playlists.name eq "name" }.firstOrNull() ?: PlaylistDao.new { name = "name" }
        }
    }

    @After("@GetAllPlaylistNames")
    fun after() {
        transaction {
            PlaylistDao.find { Playlists.name eq "name" }.firstOrNull()?.delete()
        }
    }
}
