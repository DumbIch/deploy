package ru.dumdumbich.deploy.data.db.registrars.tables

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

/**
 * @Project : <h3>deploy</h3>
 * @File : PlacementLocations
 * @description
 * <p>Registrars placement locations</p>
 * @author DumbIch
 * @date 2023-11-21 15:25
 *
 *CREATE TABLE t_placement_locations(
 * id SERIAL PRIMARY KEY,
 * facility text,
 * installation text);
 *
 * INSERT INTO t_placement_locations(facility,installation)
 * VALUES ('Блок 1','РЩГ-1-1'),
 *        ('Блок 2','РЩГ-2-1'),
 *        ('Блок 1','ОЩПТ-1'),
 *        ('Блок 1','ДГ-1GV'),
 *        ('Блок 1','ДГ-1GW'),
 *        ('Блок 1','ДГ-1GX'),
 *        ('Блок 2','ДГ-2GV'),
 *        ('Блок 2','ДГ-2GW'),
 *        ('Блок 2','ДГ-2GX'),
 *        ('Блок 1','1BV'),
 *        ('Блок 1','1BW'),
 *        ('Блок 1','1BX'),
 *        ('Блок 2','2BV'),
 *        ('Блок 2','2BW'),
 *        ('Блок 2','2BX'),
 *        ('Блок 3','РЩГ-3-1'),
 *        ('Блок 3','РЩГ-3-2'),
 *        ('Блок 3','ЩПТ ЛБК'),
 *        ('Блок 4','4JV03-4BV'),
 *        ('Блок 4','4JW03-4BW'),
 *        ('Блок 4','4JX03-4BX'),
 *        ('Блок 4','4JV03-4CP'),
 *        ('Блок 4','4JW03-4CQ'),
 *        ('Блок 4','4JX03-4CT'),
 *        ('Блок 4','РЩГ-4-1'),
 *        ('Блок 4','4HKQ2-4BA'),
 *        ('Блок 4','4HKQ3-4BB'),
 *        ('Блок 4','4HKQ4-4BC'),
 *        ('Блок 4','4HKQ5-4BD'),
 *        ('Блок 4','4HKQ6-4BKJ'),
 *        ('Блок 4','РЩГ-4-7'),
 *        ('Блок 4','РЩГ-4-8'),
 *        ('Блок 4','НС-2 Бл4'),
 *        ('ПС ЗАПАД','РЩ110-1-З'),
 *        ('ПС ВОСТОК','РЩ330-1-В'),
 *        ('ПС ВОСТОК','РЩ330-2-В'),
 *        ('ПС ВОСТОК','РЩ110-1-В'),
 *        ('ПС ВОСТОК','Пристрой_КРУ_6кВ'),
 *        ('Блок 3','РО-3-1'),
 *        ('Блок 3','РО-3-2'),
 *        ('Блок 3','РО-3-3'),
 *        ('ОРУ','БВС-1-1'),
 *        ('ОРУ','БВС-1-2'),
 *        ('ОРУ','БВС-1-3'),
 *        ('ОРУ','БВС-1-4'),
 *        ('ОРУ','БВС-2-1'),
 *        ('ОРУ','БВС-2-2'),
 *        ('ОРУ','БВС-2-3'),
 *        ('ПС 110 кВ ЦОД','ЗРУ пом_202 РАС-1'),
 *        ('ПС 110 кВ ЦОД','ЗРУ пом_202 РАС-2'),
 *        ('ПС 110 кВ ЦОД','ОПУ п_26 РАС-3'),
 *        ('ПС 110 кВ ЦОД','ЗРУ пом_201 РАС-4'),
 *        ('ПС 110 кВ ЦОД','ЗРУ пом_202 РАС-5');
 *
 **/

interface PlacementLocation : Entity<PlacementLocation> {
    companion object : Entity.Factory<PlacementLocation>()

    val id: Int
    var facility: String
    var installation: String
}

object PlacementLocations : Table<PlacementLocation>("t_placement_locations") {
    val id = int("id").primaryKey().bindTo { it.id }
    val facility = varchar("facility").bindTo { it.facility }
    val installation = varchar("installation").bindTo { it.installation }
}
