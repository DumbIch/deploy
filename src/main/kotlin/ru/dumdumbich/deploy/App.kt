package ru.dumdumbich.deploy

import kotlinx.coroutines.*
import java.nio.charset.StandardCharsets.UTF_8
import java.nio.file.Path
import kotlin.system.exitProcess
import org.apache.commons.text.StringEscapeUtils

import kotlin.time.Duration.Companion.seconds

data class CommandResult(
    val exitCode: Int,
    val stdout: String,
    val stderr: String,
)

/**
 * Executes a program. This needs to be a valid path on the
 * file system.
 *
 * See [executeShellCommand] for the version that executes
 * `/bin/sh` commands.
 */
suspend fun executeCommand(
    executable: Path,
    vararg args: String
): CommandResult =
    // Blocking I/O should use threads designated for I/O
    withContext(Dispatchers.IO) {
        val cmdArgs = listOf(executable.toAbsolutePath().toString()) + args
        val proc = Runtime.getRuntime().exec(cmdArgs.toTypedArray())
        try {
            // Concurrent execution ensures the stream's buffer doesn't
            // block processing when overflowing
            val stdout = async {
                runInterruptible {
                    // That `InputStream.read` doesn't listen to thread interruption
                    // signals; but for future development it doesn't hurt
                    String(proc.inputStream.readAllBytes(), UTF_8)
                }
            }
            val stderr = async {
                runInterruptible {
                    String(proc.errorStream.readAllBytes(), UTF_8)
                }
            }
            CommandResult(
                exitCode = runInterruptible { proc.waitFor() },
                stdout = stdout.await(),
                stderr = stderr.await()
            )
        } finally {
            // This interrupts the streams as well, so it terminates
            // async execution, even if thread interruption for that
            // InputStream doesn't work
            proc.destroy()
        }
    }

/**
 * Executes shell commands.
 *
 * WARN: command arguments need be given explicitly because
 * they need to be properly escaped.
 */
suspend fun executeShellCommand(
    command: String,
    vararg args: String
): CommandResult =
    executeCommand(
        Path.of("/bin/sh"),
        "-c",
        (listOf(command) + args).joinToString(" ", transform = StringEscapeUtils::escapeXSI)
    )

fun main(vararg args: String): Unit = runBlocking {
    // Dealing with timeouts
    val r = withTimeout(3.seconds) {
        //executeShellCommand("ls", "-alh")
        executeShellCommand("ping", "-c 3", "192.168.0.1")
    }
    print(r.stdout)
    System.err.print(r.stderr)
    exitProcess(r.exitCode)
}
/*
suspend fun main(args: Array<String>) {
//    Deploy.initArgs(args)
//    Deploy.initConsole()
//    Deploy.createDirectoryStructure()
//    ConfigFile.createRegistrarsListTemplateFile()
    val r = withTimeout(3.seconds){
        executeShellCommand("ping","192.168.0.30")
    }

}*/
