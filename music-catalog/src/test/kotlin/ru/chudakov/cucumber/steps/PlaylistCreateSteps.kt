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
import kotlin.test.assertEquals

class PlaylistCreateSteps : En {
    private val dbManager: DBManager = PgDBManager()

    lateinit var name: String

    var playlist: Playlist? = null

    init {
        Given("we have new playlist {string}") { name: String ->
            this.name = name
        }

        When("we try create playlist") {
            playlist = dbManager.createPlaylist(name)
        }

        Then("we get new playlist? {string}") { result: String ->
            assertEquals(result.toBoolean(), playlist != null)
        }
    }

    @Before("@CreatePlaylist")
    fun before() {
        transaction {
            PlaylistDao.new { name = "name1" }
        }
    }

    @After("CreatePlaylist")
    fun after() {
        transaction {
            PlaylistDao.find { Playlists.name eq "name1" }.firstOrNull()?.delete()
            PlaylistDao.find { Playlists.name eq "name" }.firstOrNull()?.delete()
        }
    }
}
