package ru.chudakov.domain

import javax.persistence.*

@Entity
data class Composition(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        val name: String,
        @OneToOne
        val author: Author,
        @OneToOne
        val genre: Genre
)
