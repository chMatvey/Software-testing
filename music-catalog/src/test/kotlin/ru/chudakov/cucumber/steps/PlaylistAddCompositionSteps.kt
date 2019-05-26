package ru.chudakov.cucumber.steps

import cucumber.api.java.After
import cucumber.api.java.Before
import cucumber.api.java8.En
import org.jetbrains.exposed.sql.transactions.transaction
import ru.chudakov.DBManager
import ru.chudakov.PgDBManager
import ru.chudakov.dao.PlaylistDao
import ru.chudakov.dao.Playlists
import kotlin.test.assertTrue

class PlaylistAddCompositionSteps : En {
    private val dbManager: DBManager = PgDBManager()

    lateinit var playListName: String
    lateinit var compositionName: String
    lateinit var authorName: String

    var result = false

    init {
        Given("we have playlist and composition") {
            playListName = "playlist"
            compositionName = "name"
            authorName = "author"
        }

        When("we try add composition to playlist") {
            result = dbManager.addCompositionToPlaylist(playListName, compositionName, authorName)
        }

        Then("playlist contains composition") {
            assertTrue { result }
        }
    }

    @Before("@AddCompositionToPlaylist")
    fun before() {
        transaction {
            PlaylistDao.new { name = "playlist" }
        }
    }

    @After("@AddCompositionToPlaylist")
    fun after() {
        transaction {
            PlaylistDao.find { Playlists.name eq "playlist" }.firstOrNull()?.delete()
        }
    }
}
