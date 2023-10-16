package ru.dumdumbich.deploy

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

/**
 * @Project : <h3>deploy</h3>
 * @File : FileOs
 * @description
 * <p>Defines the methods for working with target OS</p>
 * @author DumbIch
 * @date 2023-10-13 13:12
 **/

object FileOs {

    const val APP_NAME: String = "deploy"
    val userName: String = System.getProperty("user.name").lowercase()
    val homeDirectory: String = System.getProperty("user.home")
    val appDirectory: String = System.getProperty("user.dir")

    fun stringToPath(path: String): Path = Paths.get(path)

    fun isPathExists(path: Path): Boolean = Files.exists(path)

    fun isPathNotExists(path: Path): Boolean = !isPathExists(path)

    fun createDirectory(path: Path): Boolean = try {
        Files.createDirectory(path)
        true
    } catch (e: IOException) {
        e.printStackTrace()
        false
    }

}