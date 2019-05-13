package ru.chudakov.repository

import org.springframework.data.repository.CrudRepository
import ru.chudakov.domain.Author

interface AuthorRepository : CrudRepository<Author, Long> {
}
