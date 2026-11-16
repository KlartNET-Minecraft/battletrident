package com.battletrident.skills.cinderella

import com.battletrident.instance
import com.battletrident.skills.SkillData
import com.battletrident.skills.cinderella.gui.CinderellaGUI
import com.battletrident.skills.setCooldown
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.meta.SkullMeta
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType


val cinderella = SkillData(
	item = Material.ENDER_PEARL,
	entity = EntityType.ENDER_PEARL,
	cooldown = 60,
	duration = 5,
)

class OnCinderella : Listener {
	@EventHandler
	fun onCinderella(event: InventoryClickEvent) {
		val clickedItem = event.currentItem ?: return
		val player = event.whoClicked as Player
		
		if (event.inventory.holder is CinderellaGUI) {
			event.isCancelled = true

			if (clickedItem.type == Material.PLAYER_HEAD) {
				val itemMeta = clickedItem.itemMeta as SkullMeta
				val clickedPlayer = itemMeta.owningPlayer as Player

				player.closeInventory()
				clickedPlayer.addPotionEffect(
					PotionEffect(
						PotionEffectType.GLOWING,
						cinderella.duration * 20,
						255,
						false, false
					)
				)
				cinderella.setCooldown(player)

				instance.server.broadcast(
					Component.text(
						player.name,
						NamedTextColor.BLUE
					).append(
						Component.text(
							"님이 ",
							NamedTextColor.AQUA
						)
					).append(
						Component.text(
							clickedPlayer.name,
							NamedTextColor.RED
						)
					).append(
						Component.text(
							"님의 위치를 ${cinderella.duration}초간 밝힙니다!",
							NamedTextColor.AQUA
						)
					)
				)
			}
		}
	}
}