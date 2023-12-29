package ru.dumdumbich.deploy.data.db.registrars.tables

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

/**
 * @Project : <h3>deploy</h3>
 * @File : RegistrarsLocations
 * @description
 * <p>Table of registrars locations</p>
 * @author DumbIch
 * @date 2023-11-21 16:08
 *
 * CREATE TABLE t_registrars_locations(
 * id SERIAL PRIMARY KEY,
 * r_id integer REFERENCES t_registrars(id),
 * ep_id integer REFERENCES t_placement_locations(id),
 * install_number text);
 *
 * INSERT INTO t_registrars_locations(r_id,ep_id,install_number)
 * VALUES (1,1,'b1'),
 *        (2,2,'b2'),
 *        (3,3,'b3'),
 *        (4,4,'d1'),
 *        (5,5,'d2'),
 *        (6,6,'d3'),
 *        (7,7,'d4'),
 *        (8,8,'d5'),
 *        (9,9,'d6'),
 *        (10,10,'f1'),
 *        (11,11,'f2'),
 *        (12,12,'f3'),
 *        (13,13,'f4'),
 *        (14,14,'f5'),
 *        (15,15,'f6'),
 *        (16,16,'g1'),
 *        (17,17,'g2'),
 *        (18,18,'g3'),
 *        (19,19,'k1'),
 *        (20,20,'k2'),
 *        (21,21,'k3'),
 *        (22,22,'k4'),
 *        (23,23,'k5'),
 *        (24,24,'k6'),
 *        (25,25,'m1'),
 *        (26,26,'m2'),
 *        (27,27,'m3'),
 *        (28,28,'m4'),
 *        (29,29,'m5'),
 *        (30,30,'m6'),
 *        (31,31,'m7'),
 *        (32,32,'m8'),
 *        (33,33,'m9'),
 *        (34,34,'n1'),
 *        (35,35,'p1'),
 *        (36,36,'p2'),
 *        (37,37,'p3'),
 *        (38,38,'p4'),
 *        (39,39,'r1'),
 *        (40,40,'r2'),
 *        (41,41,'r3'),
 *        (42,42,'s1'),
 *        (43,43,'s2'),
 *        (44,44,'s3'),
 *        (45,45,'s4'),
 *        (46,46,'s6'),
 *        (47,47,'s7'),
 *        (48,48,'s8'),
 *        (49,49,'z1'),
 *        (50,50,'z2'),
 *        (51,51,'z3'),
 *        (52,52,'z4'),
 *        (53,53,'z5');
 *
 **/

interface RegistrarLocation : Entity<RegistrarLocation> {
    companion object : Entity.Factory<RegistrarLocation>()

    val id: Int
    var registrar: Int
    var location: Int
    var label: String
}

object RegistrarsLocations : Table<RegistrarLocation>("t_registrars_locations") {
    val id = int("id").primaryKey().bindTo { it.id }
    val registrar = int("r_id").bindTo { it.registrar }
    val location =
        int("ep_id").bindTo { it.location } // переименовать ep_id в pl_id - это надо сделать сначала в таблицах в базе данных
    val label = varchar("install_number").bindTo { it.label }
}
