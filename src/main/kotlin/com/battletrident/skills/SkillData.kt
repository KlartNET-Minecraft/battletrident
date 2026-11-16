package com.battletrident.skills

import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player

data class WeaponData(
	val item: Material,
	val entity: EntityType,
	val damage: Double = 0.0,
	val explosionSize: Float = 0f,
	val cooldown: Int = 0,
	val duration: Long = 0L,
)

fun WeaponData.setCooldown(player: Player) {
	player.setCooldown(this.item, this.cooldown)
}