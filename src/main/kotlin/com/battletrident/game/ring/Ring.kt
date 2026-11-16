package com.battletrident.game.ring

import com.battletrident.instance
import net.kyori.adventure.util.Ticks
import org.bukkit.scheduler.BukkitRunnable

data class Ring(
	val id: Int,
	val size: Double,
	val speed: Long,
	val damage: Double,
	val delay: Long,
	var ender: BukkitRunnable? = null,
	var starter: BukkitRunnable? = null,
)

val worldBorder = instance.server.worlds.first().worldBorder

fun Ring.applyInGame() {
	worldBorder.changeSize(
		this.size,
		this.speed * Ticks.TICKS_PER_SECOND
	)
	worldBorder.damageAmount = this.damage
	worldBorder.damageBuffer = 0.0
	worldBorder.warningDistance = 30
	worldBorder.warningTimeTicks = 0
}