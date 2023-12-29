package ru.dumdumbich.deploy.domain

/**
 * @Project : <h3>deploy</h3>
 * @File : RegistrarEntity
 * @description
 * <p>Entity: Registrar device</p>
 * @author DumbIch
 * @date 2023-10-19 12:46
 **/
data class RegistrarEntity(
    val installNumber: String,
    val registrarType: String,
    val locationUnit: String,
    val locationBlock: String,
    val ipAddress: String,
    val ipMask: String,
    )
