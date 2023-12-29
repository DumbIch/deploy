package ru.dumdumbich.deploy

import ru.dumdumbich.deploy.data.db.registrars.RegistrarsDatabase

fun main(args: Array<String>) {
    RegistrarsDatabase.init()
    RegistrarsDatabase.run()
    RegistrarsDatabase.done()
}
