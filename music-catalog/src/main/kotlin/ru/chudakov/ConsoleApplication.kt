package ru.chudakov

class ConsoleApplication(val dbManager: DBManager) {

    fun run() {
        var isRun = true
        var input: String?

        println(getHelp())

        loop@ while (isRun) {
            println(mainMenuInfo)

            input = readLine()

            when (input) {
                "1" -> {
                    println("Введите название композиции и имя автора")
                    //val (composition, author) = readLine()!!.split(' ')
                    val composition = readLine() ?: continue@loop
                    val author = readLine() ?: continue@loop

                    dbManager.findCompositionByName(composition, author)?.let {
                        println("${it.name} - ${it.author.name} - ${it.genre.name}")
                    } ?: println("Данная композиция не существует")
                }
                "2" -> {
                    dbManager.getAllCompositions().forEach {
                        println("${it.name} - ${it.author.name} - ${it.genre.name}")
                    }
                }
                "3" -> {
                    println("Введите название композиции, имя автора и жанр")
                    val composition = readLine() ?: continue@loop
                    val author = readLine() ?: continue@loop
                    val genre = readLine() ?: continue@loop

                    dbManager.addComposition(composition, author, genre)?.let {
                        println("Композиция добавлена!")
                    } ?: println("Ошибка: ${dbManager.getMessage()}")
                }
                "4" -> {
                    println("Введите название композиции и имя автора")
                    val composition = readLine() ?: continue@loop
                    val author = readLine() ?: continue@loop

                    dbManager.deleteCompositions(composition, author)
                    println("Композиция удалена")
                }
                "5" -> {
                    println("Введите имя автора")
                    val name = readLine() ?: continue@loop

                    dbManager.findCompositionsByAuthor(name).forEach {
                        println("${it.name} - ${it.author.name} - ${it.genre.name}")
                    }
                }
                "6" -> {
                    println("Введите название жанра")
                    val name = readLine() ?: continue@loop

                    dbManager.findCompositionsByGenre(name).forEach {
                        println("${it.name} - ${it.author.name} - ${it.genre.name}")
                    }
                }
                "7" -> {
                    dbManager.getAllGenres().forEach {
                        println(it.name)
                    }
                }
                "8" -> {
                    println("Введите название жанра")
                    val name = readLine() ?: continue@loop

                    dbManager.addGenre(name)?.let {
                        println("Жанр добавлен!")
                    } ?: println("Ошибка: ${dbManager.getMessage()}")
                }
                "9" -> {
                    println("Введите название жанра")
                    val name = readLine() ?: continue@loop

                    dbManager.deleteGenres(name)
                    println("Жанр удален")
                }
                "10" -> {
                    dbManager.getAllAuthors().forEach {
                        println(it.name)
                    }
                }
                "11" -> {
                    println("Введите название плейлиста")
                    val name = readLine() ?: continue@loop

                    dbManager.createPlaylist(name)?.let {
                        println("Плейлист создан!")
                    } ?: println("Ошибка: ${dbManager.getMessage()}")
                }
                "12" -> {
                    println("Введите название плейлиста")
                    val name = readLine() ?: continue@loop

                    dbManager.deletePlaylist(name)
                    println("Плейлист удален")
                }
                "13" -> {
                    println("Введите название плейлиста, название композиции и имя автора")
                    val playlist = readLine() ?: continue@loop
                    val composition = readLine() ?: continue@loop
                    val author = readLine() ?: continue@loop

                    if (dbManager.addCompositionToPlaylist(playlist, composition, author))
                        println("Композиция добавлена в плейлист!")
                    else
                        println("Ошибка: ${dbManager.getMessage()}")
                }
                "14" -> {
                    println("Введите название плейлиста, название композиции и имя автора")
                    val playlist = readLine() ?: continue@loop
                    val composition = readLine() ?: continue@loop
                    val author = readLine() ?: continue@loop

                    if (dbManager.deleteCompositionFromPlaylist(playlist, composition, author))
                        println("Композиция удалена из плейлиста!")
                    else
                        println("Ошибка: ${dbManager.getMessage()}")
                }
                "15" -> {
                    println("Введите название плейлиста")
                    val name = readLine() ?: continue@loop

                    dbManager.getPlaylist(name)?.let {
                        it.compositions.forEach { println("${it.name} - ${it.author.name} - ${it.genre.name}") }
                    } ?: println("Ошибка: ${dbManager.getMessage()}")
                }
                "16" -> {
                    dbManager.getAllPlaylistNames().forEach { println(it) }
                }
                "17" -> {
                    println(getHelp())
                }
                "18" -> {
                    isRun = false
                }
                else -> {
                    println("Ошибка ввода, пожалуйста повторите.")
                }
            }
        }
    }

    private val mainMenuInfo = "\nВыберите пункт:\n" +
            "1 - Найти музыкальную композицию.\n" +
            "2 - Вывести информацию о всех существующий композициях в каталоге.\n" +
            "3 - Добавить информацию о композиции в каталог.\n" +
            "4 - Удалить существующую в каталоге запись о композиции.\n" +
            "5 - Вывести все композиции одного автора.\n" +
            "6 - Вывести все композиции одного жанра.\n" +
            "7 - Вывести информацию о всех существующих жанрах.\n" +
            "8 - Добавить информаицю о новом жанре.\n" +
            "9 - Удалить информацию о существующем жанре вместе со всеми комопзициями.\n" +
            "10 - Вывести информацию о всех существующих авторах.\n" +
            "11 - Создать плейлист.\n" +
            "12 - Удалить существующий плейлист.\n" +
            "13 - Добавит композицию в существующий плейлист.\n" +
            "14 - Удалить композицию из плейлиста.\n" +
            "15 - Просмотр композиций плейлиста.\n" +
            "16 - Просмотр всех существующих плейлистов.\n" +
            "17 - Получить справочную информацию.\n" +
            "18 - Выйти из программы.\n"

    fun getHelp() = "Музыкальный каталог!\n" +
            "Для перехода в соответвующий раздел введите команду, список команд представлен ниже.\n" +
            "При добавлении новой композиции необходимо последовательно вваести название, имя автора, жанр.\n" +
            "Просмотреть все жанры или добавить новый можно в ссответсвующем разделе.\n" +
            "Если в приложении нет информации о введеном авторе, новая запись создастся автоматически.\n" +
            "Вы можете создать свой плейлист с набором разных композиций.\n" +
            "В плейлист можно добавить только существующие композиции."
}
