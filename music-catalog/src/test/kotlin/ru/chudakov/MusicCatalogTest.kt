package ru.chudakov

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class MusicCatalogTest {
    private val musicCatalog = MusicCatalog()

    @Test
    fun getHelp() {
        assertEquals("Music Catalog", musicCatalog.getHelp())
    }
}
