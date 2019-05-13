package ru.chudakov.service

import org.springframework.stereotype.Service

@Service
class HelpService {

    fun getHelp(): String {
        return "Music Catalog"
    }
}
