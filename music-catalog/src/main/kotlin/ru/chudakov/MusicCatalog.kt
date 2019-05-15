package ru.chudakov

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.Banner
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import ru.chudakov.service.HelpService

@SpringBootApplication
open class MusicCatalog @Autowired constructor(private val helpService: HelpService) : CommandLineRunner {

    override fun run(vararg args: String?) {
        println(helpService.getHelp())
    }
}

fun main(args: Array<String>) {
    val app = SpringApplication(MusicCatalog::class.java)
    app.setBannerMode(Banner.Mode.OFF)
    app.run(*args)
}
