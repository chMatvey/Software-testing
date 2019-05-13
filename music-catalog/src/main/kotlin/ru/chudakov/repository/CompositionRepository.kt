package ru.chudakov.repository

import org.springframework.data.repository.CrudRepository
import ru.chudakov.domain.Composition

interface CompositionRepository : CrudRepository<Composition, Long> {
}
