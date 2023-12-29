package ru.dumdumbich.deploy.data.db.registrars

import org.ktorm.database.Database
import org.ktorm.dsl.from
import org.ktorm.dsl.select
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList
import ru.dumdumbich.deploy.data.db.registrars.tables.*
import kotlin.system.exitProcess

/**
 * @Project : <h3>deploy</h3>
 * @File : RegistrarsDatabase
 * @description
 * <p>Registrars Database</p>
 * @author DumbIch
 * @date 2023-11-21 11:13
 **/

val Database.manufacturers get() = this.sequenceOf(Manufacturers)
val Database.registrarsTypes get() = this.sequenceOf(RegistrarsTypes)
val Database.placementLocations get() = this.sequenceOf(PlacementLocations)
val Database.registrars get() = this.sequenceOf(Registrars)
val Database.registrarsLocations get() = this.sequenceOf(RegistrarsLocations)

object RegistrarsDatabase {
    private lateinit var db: Database

    private var dbIsExist: Boolean = false


    fun init() {
        try {
            db = Database.connect(
                url = "jdbc:postgresql://localhost:5432/db_registrars",
                driver = "org.postgresql.Driver",
                user = "dumdumbich", //"postgres", //
                password = "registrars" //"postgres" //
            )
            dbIsExist = true
        } catch (e: Exception) {
            println("Указанной базы данных не существует : $e")
            exitProcess(99)
        }

    }

    fun run() {
        if (dbIsExist) {

            println("Table 't_manufacturers'")
            for (row in db.from(Manufacturers).select()) {
                println("${row[Manufacturers.id]} : ${row[Manufacturers.name]}")
            }

            println()
            println("Sequences API")

            val manufacturersList = db.manufacturers.toList()
            println(manufacturersList)
            println()

            val registrarsTypesList = db.registrarsTypes.toList()
            println(registrarsTypesList)
            println()

            val placementLocationsList = db.placementLocations.toList()
            println(placementLocationsList)
            println()

            val registrarsList = db.registrars.toList()
            println(registrarsList)
            println()

            val registrarsLocationsList = db.registrarsLocations.toList()
            println(registrarsLocationsList)
            println()

        }
    }

    fun done() {

    }

}