package ru.dumdumbich.deploy.data.db.registrars.tables

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

/**
 * @Project : <h3>deploy</h3>
 * @File : RegistrarsTypes
 * @description
 * <p>Table registrars types</p>
 * @author DumbIch
 * @date 2023-11-21 14:27
 *
 * CREATE TABLE t_registrars_types(
 * id SERIAL PRIMARY KEY,
 * m_id integer REFERENCES t_manufacturers(id),
 * name text);
 *
 * INSERT INTO t_registrars_types(m_id,name)
 * VALUES (1,'РП4-06'),
 *        (1,'РП4-06М'),
 *        (1,'РП4-08'),
 *        (1,'РП4-11'),
 *        (1,'РП4-12');
 *
 **/

interface RegistrarsType : Entity<RegistrarsType> {
    companion object : Entity.Factory<RegistrarsType>()

    val id: Int
    var name: String
}

object RegistrarsTypes : Table<RegistrarsType>("t_registrars_types") {
    val id = int("id").primaryKey().bindTo { it.id }
    val name = varchar("name").bindTo { it.name }
}
