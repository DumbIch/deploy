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

enum class ConfigParams(val param: String) {
    DIR_ROOT("dirRoot"),
    DIR_FTP("dirFtp"),
    DIR_REGISTRARS("dirRegistrars")
}

object ConfigFile {

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
    private val pathConfigFile = "${FileOs.homeDirectory}/app/${FileOs.APP_NAME}/${FileOs.APP_NAME}.conf"

    // Config dir (run jar file on development/target server)
//    private val pathConfigFile = "${FileOs.appDirectory}/${FileOs.APP_NAME}/${FileOs.APP_NAME}.conf"

    init {
        val pathConfig = FileOs.stringToPath(pathConfigFile)
        println(pathConfigFile)
        if (FileOs.isPathExists(pathConfig)) {
            val file = File(pathConfigFile)
            val fileReader = FileReader(file, Charset.forName("utf-8"))
            val bufReader = BufferedReader(fileReader)
            var line = bufReader.readLine()
            while (line != null) {
                if (line.trim().startsWith("#")) continue
                val paramName = line.substringBefore("=").trim().lowercase()
                val paramValue = line.substringAfter("=").trim()
                if (paramValue.isNotEmpty()) {
                    when (paramName) {
                        ConfigParams.DIR_ROOT.param.lowercase() -> {
                            _dirRoot = paramValue
                        }

                        ConfigParams.DIR_FTP.param.lowercase() -> {
                            _dirFtp = paramValue
                        }

                        ConfigParams.DIR_REGISTRARS.param.lowercase() -> {
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
}
