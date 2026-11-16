package com.battletrident.skills.trident

import com.battletrident.instance
import com.battletrident.skills.SkillData
import com.battletrident.skills.setCooldown
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.ProjectileHitEvent
import org.bukkit.event.entity.ProjectileLaunchEvent
import org.bukkit.scheduler.BukkitRunnable


val trident = SkillData(
	item = Material.TRIDENT,
	entity = EntityType.TRIDENT,
	damage = 10.0,
	explosionSize = 2.0f,
	cooldown = 5,
)

class OnTrident : Listener {
	@EventHandler
	fun onTridentThrow(event: ProjectileLaunchEvent) {
		val projectile = event.entity
		val shooter = projectile.shooter as? Player ?: return
		
		if (projectile.type == trident.entity) {
			trident.setCooldown(shooter)
			
			object : BukkitRunnable() {
				override fun run() {
					if (projectile.isDead) {
						return this.cancel()
					}
					
					shooter.world.spawnParticle(
						Particle.SONIC_BOOM,
						projectile.location,
						1
					)
				}
			}.runTaskTimer(instance, 0, 1)
		}
	}
	
	@EventHandler
	fun onTridentHit(event: ProjectileHitEvent) {
		val projectile = event.entity
		val shooter = projectile.shooter as? Player ?: return
		
		if (projectile.type == trident.entity) {
			if (event.hitEntity != null) {
				val world = projectile.world

				shooter.playSound(
					shooter.location,
					Sound.ENTITY_ARROW_HIT_PLAYER,
					100f, 1.0f
				)
				world.createExplosion(
					projectile.location,
					trident.explosionSize,
					false, false
				)
				world.strikeLightningEffect(
					projectile.location
				)
			}

			projectile.remove()
		}
	}
}