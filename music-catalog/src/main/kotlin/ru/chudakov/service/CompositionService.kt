package ru.chudakov.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.chudakov.repository.CompositionRepository

@Service
class CompositionService @Autowired constructor(private val repository: CompositionRepository) {

}
