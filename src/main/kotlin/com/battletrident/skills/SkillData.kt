package com.battletrident.skills

import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player

data class SkillData(
	val item: Material,
	val entity: EntityType,
	val damage: Double = 0.0,
	val explosionSize: Float = 0f,
	val cooldown: Int = 0,
	val duration: Int = 0,
)

fun SkillData.setCooldown(player: Player) {
	player.setCooldown(this.item, this.cooldown)
}
fun SkillData.hasCooldown(player: Player): Boolean {
	return player.hasCooldown(this.item)
}