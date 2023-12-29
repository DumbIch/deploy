package ru.dumdumbich.deploy.data.db.registrars.tables

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

/**
 * @Project : <h3>deploy</h3>
 * @File : Registrars
 * @description
 * <p>Table registrars list</p>
 * @author DumbIch
 * @date 2023-11-21 15:43
 *
 * CREATE TABLE t_registrars(
 * id SERIAL PRIMARY KEY,
 * rt_id integer REFERENCES t_registrars_types(id),
 * manufactured_number text);
 *
 * INSERT INTO t_registrars(rt_id,manufactured_number)
 * VALUES (4,NULL),
 *        (1,NULL),
 *        (4,NULL),
 *        (1,NULL),
 *        (1,NULL),
 *        (1,NULL),
 *        (3,NULL),
 *        (1,NULL),
 *        (1,NULL),
 *        (1,NULL),
 *        (1,NULL),
 *        (1,NULL),
 *        (1,NULL),
 *        (1,NULL),
 *        (1,NULL),
 *        (1,NULL),
 *        (1,NULL),
 *        (4,NULL),
 *        (2,NULL),
 *        (2,NULL),
 *        (2,NULL),
 *        (2,NULL),
 *        (2,NULL),
 *        (2,NULL),
 *        (2,NULL),
 *        (2,NULL),
 *        (2,NULL),
 *        (2,NULL),
 *        (2,NULL),
 *        (2,NULL),
 *        (2,NULL),
 *        (2,NULL),
 *        (1,NULL),
 *        (1,NULL),
 *        (1,NULL),
 *        (1,NULL),
 *        (1,NULL),
 *        (2,NULL),
 *        (1,NULL),
 *        (1,NULL),
 *        (1,NULL),
 *        (1,NULL),
 *        (1,NULL),
 *        (1,NULL),
 *        (1,NULL),
 *        (1,NULL),
 *        (1,NULL),
 *        (1,NULL),
 *        (4,NULL),
 *        (4,NULL),
 *        (4,NULL),
 *        (4,NULL),
 *        (4,NULL);
 *
 **/

interface Registrar : Entity<Registrar> {
    companion object : Entity.Factory<Registrar>()

    val id: Int
    var registrarType: Int
    var manufacturedNumber: String
}

object Registrars : Table<Registrar>("t_registrars") {
    val id = int("id").primaryKey().bindTo { it.id }
    val registrarType = int("rt_id").bindTo { it.registrarType }
    val manufacturedNumber = varchar("manufactured_number").bindTo { it.manufacturedNumber }
}
