package ru.chudakov.cucumber.steps

import cucumber.api.java.After
import cucumber.api.java.Before
import cucumber.api.java8.En
import org.jetbrains.exposed.sql.transactions.transaction
import ru.chudakov.DBManager
import ru.chudakov.PgDBManager
import ru.chudakov.dao.PlaylistDao
import ru.chudakov.dao.Playlists
import kotlin.test.assertFalse

class PlaylistDeleteCompositionSteps : En {
    private val dbManager: DBManager = PgDBManager()

    lateinit var playListName: String
    lateinit var compositionName: String
    lateinit var authorName: String

    var result = false

    init {
        Given("we have playlist and composition names") {
            playListName = "playlist"
            compositionName = "name"
            authorName = "author"
        }
        When("we try delete composition from playlist") {
            dbManager.deleteCompositionFromPlaylist(playListName, compositionName, authorName)
        }

        Then("playlist does not contains composition") {
            assertFalse {
                dbManager.getPlaylist(playListName)?.compositions
                        ?.any { it.name == compositionName && it.author.name == authorName } ?: false
            }
        }
    }

    @Before("@DeleteCompositionFromPlaylist")
    fun before() {
        transaction {
            val playlist = PlaylistDao.new { name = "playlist" }
            playlist.compositions.plus(PlaylistDao.find { Playlists.name eq "name" }.firstOrNull())
        }
    }

    @After("@DeleteCompositionFromPlaylist")
    fun after() {
        transaction {
            PlaylistDao.find { Playlists.name eq "playlist" }.firstOrNull()?.delete()
        }
    }
}
