package ru.chudakov.repository

import org.springframework.data.repository.CrudRepository
import ru.chudakov.domain.Genre

interface GenreRepository : CrudRepository<Genre, Long> {
}
