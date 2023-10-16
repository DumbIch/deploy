package ru.dumdumbich.deploy

import kotlin.system.exitProcess

/**
 * @Project : <h3>deploy</h3>
 * @File : Deploy
 * @description
 * <p>Project Deploy : main class</p>
 * @author DumbIch
 * @date 2023-10-13 12:42
 **/

object Deploy {

    private val console = System.console()
    private var isConsoleAvailable = false

    fun initConsole() {
        if (console == null) {
            println("Console not available")
            exitProcess(1)
        }
        isConsoleAvailable = true
        var tryCounter = 0
        do {
            val password = console.readPassword("Enter your password : ")
            val correctPassword = charArrayOf('p', 'a', 's', 's', 'w', 'o', 'r', 'd')
            val isPasswordCorrect = password contentEquals correctPassword
            tryCounter += 1
            if (tryCounter == 3 && !isPasswordCorrect) {
                console.printf("Number of input attempts has been exhausted - Password not correct\n")
                exitProcess(1)
            }
        } while (!isPasswordCorrect)
        console.printf("\nPassword correct\n")
    }

    fun initArgs(args: Array<String>) {
        println("Project Deploy")
        println("Program arguments: ${args.joinToString()}")
    }

    fun createDirectoryStructure() {
        println("Root deploy directory : ${ConfigFile.dirRoot}")
        println("FTP directory : ${ConfigFile.dirFtp}")
        println("Registrars directory : ${ConfigFile.dirRegistrars}")
        DirectoryStructure
    }

}
