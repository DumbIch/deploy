package ru.dumdumbich.deploy.data.db.registrars.tables

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

/**
 * @Project : <h3>deploy</h3>
 * @File : Manufacturers
 * @description
 * <p>Table manufacturers</p>
 * @author DumbIch
 * @date 2023-11-21 11:20
 *
 * CREATE TABLE t_manufacturers(
 * id SERIAL PRIMARY KEY,
 * name text);
 *
 * INSERT INTO t_manufacturers(name)
 * VALUES ('ООО ПАРМА');
 *
 **/


interface Manufacturer : Entity<Manufacturer> {
    companion object : Entity.Factory<Manufacturer>()

    val id: Int
    var name: String
}

object Manufacturers : Table<Manufacturer>("t_manufacturers") {
    val id = int("id").primaryKey().bindTo { it.id }
    val name = varchar("name").bindTo { it.name }
}
