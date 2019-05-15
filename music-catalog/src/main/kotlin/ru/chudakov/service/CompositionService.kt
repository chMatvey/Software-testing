package ru.chudakov.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.chudakov.repository.AuthorRepository
import ru.chudakov.repository.CompositionRepository
import ru.chudakov.repository.GenreRepository

@Service
class CompositionService @Autowired constructor(
        private val compositionRepository: CompositionRepository,
        private val authorRepository: AuthorRepository,
        private val genreRepository: GenreRepository) {
}
