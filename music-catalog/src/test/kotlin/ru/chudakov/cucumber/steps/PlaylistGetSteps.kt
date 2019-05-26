package ru.chudakov.cucumber.steps

import cucumber.api.java.After
import cucumber.api.java.Before
import cucumber.api.java8.En
import org.jetbrains.exposed.sql.transactions.transaction
import ru.chudakov.DBManager
import ru.chudakov.PgDBManager
import ru.chudakov.dao.PlaylistDao
import ru.chudakov.dao.Playlists
import ru.chudakov.data.Playlist
import kotlin.test.assertTrue

class PlaylistGetSteps : En {
    private val dbManager: DBManager = PgDBManager()

    lateinit var name: String

    var playlist: Playlist? = null

    init {
        Given("we have playlist {string}") { name: String ->
            this.name = name
        }
        When("we try get playlist compositions") {
            playlist = dbManager.getPlaylist(name)
        }

        Then("we get playlist with compositions") {
            assertTrue { playlist != null }
        }
    }

    @Before("@GetPlaylist")
    fun before() {
        transaction {
            PlaylistDao.find { Playlists.name eq "name" }.firstOrNull() ?: PlaylistDao.new { name = "name" }
        }
    }

    @After("@GetPlaylist")
    fun after() {
        transaction {
            PlaylistDao.find { Playlists.name eq "name" }.firstOrNull()?.delete()
        }
    }
}
