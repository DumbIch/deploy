package ru.dumdumbich.deploy

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import kotlin.system.exitProcess
import kotlin.time.Duration.Companion.seconds


fun main(vararg args: String): Unit = runBlocking {
    val r = withTimeout(3.seconds) {
        FileOs.executeShellCommand("ping", "-c 3", "192.168.0.1")
    }
    print(r.stdout)
    System.err.print(r.stderr)
    exitProcess(r.exitCode)
}
