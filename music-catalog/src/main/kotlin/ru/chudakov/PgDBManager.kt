package ru.chudakov

import org.jetbrains.exposed.dao.with
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import ru.chudakov.dao.*
import ru.chudakov.data.*

class PgDBManager : DBManager {
    private var message = ""

    init {
        Database.connect(PgDBConfig.url, PgDBConfig.driver, PgDBConfig.userName, PgDBConfig.password)

        transaction {
            addLogger()

            SchemaUtils.create(Authors)
            SchemaUtils.create(Genres)
            SchemaUtils.create(Compositions)
            SchemaUtils.create(Playlists)
            SchemaUtils.create(CompositionsPlaylists)

            if (GenreDao.all().empty()) {
                GenreDao.new { name = "pop" }
                GenreDao.new { name = "rap" }
                GenreDao.new { name = "classic" }
                GenreDao.new { name = "rock" }
            }
        }

        addComposition("name", "author", "pop")
    }

    override fun addComposition(compositionName: String, authorName: String, genreName: String): Composition? {
        var composition: Composition? = null

        transaction {
            val genre = GenreDao.find { Genres.name eq genreName }.firstOrNull()

            if (genre == null) {
                message = "This genre doesn't exist"
                return@transaction
            }

            val author = AuthorDao.find { Authors.name eq authorName }.firstOrNull()
                    ?: AuthorDao.new { name = authorName }

            val isExist = CompositionDao.find {
                Compositions.name eq compositionName
            }.any { it.author.id == author.id }

            if (isExist) {
                message = "This composition already exist"
                return@transaction
            }

            val dao = CompositionDao.new {
                name = compositionName
                this.author = author
                this.genre = genre
            }

            composition = Composition(dao.name, Author(author.name), Genre(genre.name))
        }

        return composition
    }

    override fun deleteCompositions(compositionName: String, authorName: String) {
        transaction {
            CompositionDao.find { Compositions.name eq compositionName }
                    .firstOrNull { it.author.name == authorName }?.delete()
        }
    }

    override fun addGenre(name: String): Genre? {
        var result: Genre? = null

        transaction {
            val isExist = !GenreDao.find { Genres.name eq name }.empty()

            if (isExist) {
                message = "This genre already exist"
                return@transaction
            }

            val dao = GenreDao.new {
                this.name = name
            }
            result = Genre(dao.name)
        }

        return result
    }

    override fun deleteGenres(name: String) {
        transaction {
            GenreDao.find { Genres.name eq name }.firstOrNull()?.delete()
        }
    }

    override fun getAllCompositions(): List<Composition> {
        return transaction {
            CompositionDao.all()
                    .with(CompositionDao::author, CompositionDao::genre)
                    .map { Composition(it.name, Author(it.author.name), Genre(it.genre.name)) }
        }
    }

    override fun getAllGenres(): List<Genre> {
        return transaction { GenreDao.all().map { Genre(it.name) } }
    }

    override fun findCompositionByName(compositionName: String, authorName: String): Composition? {
        var result: Composition? = null

        transaction {
            val author = AuthorDao.find { Authors.name eq authorName }.firstOrNull() ?: return@transaction

            CompositionDao.findById(1)

            result = CompositionDao.find { Compositions.name eq compositionName }
                    .with(CompositionDao::author, CompositionDao::genre).firstOrNull { it.author == author }
                    ?.let { Composition(it.name, Author(it.author.name), Genre(it.genre.name)) }
        }
        return result
    }

    override fun findCompositionsByAuthor(authorName: String): List<Composition> {
        var result = emptyList<Composition>()

        transaction {
            val authorId = AuthorDao.find { Authors.name eq authorName }.firstOrNull()?.id ?: return@transaction

            result = CompositionDao.find { Compositions.author eq authorId }
                    .with(CompositionDao::author, CompositionDao::genre)
                    .map { Composition(it.name, Author(it.author.name), Genre(it.genre.name)) }
        }
        return result
    }

    override fun findCompositionsByGenre(genreName: String): List<Composition> {
        var result = emptyList<Composition>()

        transaction {
            val genreId = GenreDao.find { Genres.name eq genreName }.firstOrNull()?.id ?: return@transaction

            result = CompositionDao.find { Compositions.genre eq genreId }
                    .with(CompositionDao::author, CompositionDao::genre)
                    .map { Composition(it.name, Author(it.author.name), Genre(it.genre.name)) }
        }
        return result
    }

    override fun getAllAuthors(): List<Author> {
        return transaction { AuthorDao.all().map { Author(it.name) } }
    }

    override fun createPlaylist(name: String): Playlist? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deletePlaylist(name: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAllPlaylistNames(): List<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPlaylist(name: String): Playlist? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addCompositionToPlaylist(playlistName: String, compositionName: String, authorName: String): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteCompositionFromPlaylist(playlistName: String, compositionName: String, authorName: String): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
