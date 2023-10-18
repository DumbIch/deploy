package ru.dumdumbich.deploy

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runInterruptible
import kotlinx.coroutines.withContext
import org.apache.commons.text.StringEscapeUtils
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

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

    fun createFile(path: Path): Boolean = try {
        Files.createFile(path)
        true
    } catch (e: IOException) {
        e.printStackTrace()
        false
    }

    fun addTextToFile(text: String, path: Path): Boolean = try {
        Files.write(
            path,
            text.toByteArray(),
            StandardOpenOption.APPEND,
            StandardOpenOption.CREATE
        )
        true
    } catch (e: IOException) {
        e.printStackTrace()
        false
    }


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
                        String(proc.inputStream.readAllBytes(), StandardCharsets.UTF_8)
                    }
                }
                val stderr = async {
                    runInterruptible {
                        String(proc.errorStream.readAllBytes(), StandardCharsets.UTF_8)
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


}