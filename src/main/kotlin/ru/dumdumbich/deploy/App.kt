package ru.dumdumbich.deploy

fun main(args: Array<String>) {
    Deploy.initArgs(args)
//    Deploy.initConsole()
    Deploy.createDirectoryStructure()
}
