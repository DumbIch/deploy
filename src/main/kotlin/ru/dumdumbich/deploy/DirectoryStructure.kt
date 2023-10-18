package ru.dumdumbich.deploy

import ru.dumdumbich.deploy.FileOs.createDirectory
import ru.dumdumbich.deploy.FileOs.isPathExists
import ru.dumdumbich.deploy.FileOs.isPathNotExists
import ru.dumdumbich.deploy.FileOs.stringToPath
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.nio.charset.Charset

/**
 * @Project : <h3>deploy</h3>
 * @File : DirectoryStructure
 * @description
 * <p>Directory structure project</p>
 * @author DumbIch
 * @date 2023-10-13 12:47
 **/

object DirectoryStructure {

    init {
        println("Create directory tree fo registrars files")

        val pathRoot = stringToPath(DeployConf.dirRoot)
        val pathFtp = stringToPath("${DeployConf.dirRoot}/${DeployConf.dirFtp}")
        val pathRegistrars = stringToPath("${DeployConf.dirRoot}/${DeployConf.dirRegistrars}")

        // Registrars list (run app from IDE)
        val pathRegistrarsList = "${FileOs.homeDirectory}/app/${FileOs.APP_NAME}/Registrars.list"

        // Registrars list (run jar file on development/target server)
//        val pathRegistrarsList = "${FileOs.appDirectory}/${FileOs.APP_NAME}/Registrars.list"

        val file = File(pathRegistrarsList)
        val fileReader = FileReader(file, Charset.forName("utf-8"))
        val bufReader = BufferedReader(fileReader)

        if (isPathNotExists(pathRoot)) {
            println("Root directory not exist. Create root directory")
            createDirectory(pathRoot)
        }
        if (isPathExists(pathRoot)) {
            if (isPathNotExists(pathFtp)) {
                println("FTP directory not exist. Create FTP directory")
                createDirectory(pathFtp)
            }
            if (isPathExists(pathFtp)) {
                if (isPathNotExists(pathRegistrars)) {
                    println("Registrars directory not exist. Create registrars directory")
                    createDirectory(pathRegistrars)
                }
                if (isPathExists(pathRegistrars)) {
                    var line = bufReader.readLine()
                    while (line != null) {
                        val dirRegistrar = line.substringBefore("|").trim().lowercase()
                        val pathTarget = stringToPath("${DeployConf.dirRoot}/${DeployConf.dirRegistrars}/$dirRegistrar")
                        if (isPathNotExists(pathTarget)) createDirectory(pathTarget)
                        line = bufReader.readLine()
                    }
                }
            }
        }
        bufReader.close()
        fileReader.close()
    }
}
