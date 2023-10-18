package ru.dumdumbich.deploy

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.nio.charset.Charset

/**
 * @Project : <h3>deploy</h3>
 * @File : ConfigFile
 * @description
 * <p>Defines the rules for working with configuration files</p>
 * @author DumbIch
 * @date 2023-10-13 13:08
 **/

enum class DeployConfParams(val param: String) {
    DIR_ROOT("dirRoot"),
    DIR_FTP("dirFtp"),
    DIR_REGISTRARS("dirRegistrars")
}

object DeployConf {

    private var _dirRoot: String? = null
    val dirRoot: String
        get() = _dirRoot!!

    private var _dirFtp: String? = null
    val dirFtp: String
        get() = _dirFtp!!

    private var _dirRegistrars: String? = null
    val dirRegistrars: String
        get() = _dirRegistrars!!

    // Config dir (run app from IDE)
    private val configDir = "${FileOs.homeDirectory}/app/${FileOs.APP_NAME}"

    // Config dir (run jar file on development/target server)
//    private val configDir = "${FileOs.appDirectory}/${FileOs.APP_NAME}"

    private val templateDir = "${configDir}/temp"
    private val pathTemplateDir = FileOs.stringToPath(templateDir)
    private val pathTemplateFile = FileOs.stringToPath("$templateDir/TempRegistrars.list")

    private val configFile = "$configDir/${FileOs.APP_NAME}.conf"
    private val pathConfigFile = FileOs.stringToPath(configFile)

    private val registrarsListFile = "$configDir/Registrars.list"
    private val pathRegistrarsListFile = FileOs.stringToPath(registrarsListFile)

    init {
        if (FileOs.isPathExists(pathConfigFile)) {
            val file = File(configFile)
            val fileReader = FileReader(file, Charset.forName("utf-8"))
            val bufReader = BufferedReader(fileReader)
            var line = bufReader.readLine()
            while (line != null) {
                if (line.trim().startsWith("#")) continue
                val paramName = line.substringBefore("=").trim().lowercase()
                val paramValue = line.substringAfter("=").trim()
                if (paramValue.isNotEmpty()) {
                    when (paramName) {
                        DeployConfParams.DIR_ROOT.param.lowercase() -> {
                            _dirRoot = paramValue
                        }

                        DeployConfParams.DIR_FTP.param.lowercase() -> {
                            _dirFtp = paramValue
                        }

                        DeployConfParams.DIR_REGISTRARS.param.lowercase() -> {
                            _dirRegistrars = paramValue
                        }
                    }
                }
                line = bufReader.readLine()
            }
            bufReader.close()
            fileReader.close()
        }
    }

    fun createRegistrarsListTemplateFile() {
        if (FileOs.isPathNotExists(pathTemplateDir)) {
            FileOs.createDirectory(pathTemplateDir)
        }
        if (FileOs.isPathExists(pathTemplateDir)) {
            if (FileOs.isPathNotExists(pathTemplateFile)) {
                FileOs.createFile(pathTemplateFile)
                if (FileOs.isPathExists(pathTemplateFile) && FileOs.isPathExists(pathRegistrarsListFile)) {
                    val file = File(registrarsListFile)
                    val fileReader = FileReader(file, Charset.forName("utf-8"))
                    val bufReader = BufferedReader(fileReader)
                    var line = bufReader.readLine()
                    while (line != null) {
                        val registrarInstallNumber = line.substringBefore("|").trim().lowercase()
                        FileOs.addTextToFile("$registrarInstallNumber|\n", pathTemplateFile)
                        line = bufReader.readLine()
                    }
                    bufReader.close()
                    fileReader.close()
                }
            }
        }
    }

}
