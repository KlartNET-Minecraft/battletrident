package com.battletrident.skills.cinderella.gui

import com.battletrident.instance
import com.battletrident.player.UserState
import com.battletrident.player.UserManager
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta

class CinderellaGUI(opener: Player) : InventoryHolder {
	private val SIZE = 9
	private val TITLE = Component.text(
		"비비디 바비디 BOOM\uD83D\uDCA5"
	)
	private val inv = instance.server.createInventory(
		this,
		SIZE,
		TITLE
	)
	
	private var users = UserManager.getByFilter {
		it.state == UserState.PLAYING &&
		it.player.uniqueId != opener.uniqueId
	}
	
	init {
		for (i in 0..<SIZE) {
			var tmpItem: ItemStack

			if (i < users.size) {
				val player = this.users[i].player
				
				tmpItem = ItemStack(Material.PLAYER_HEAD)
				tmpItem.editMeta(SkullMeta::class.java) {
					it.owningPlayer = player
					
					it.displayName(
						Component.text(
							player.name
						)
					)
					it.lore(listOf(
						Component.text(
							player.name,
							NamedTextColor.YELLOW
						).append(
							Component.text(
								"님의 위치를 밝힙니다.",
								NamedTextColor.WHITE
							)
						)
					))
				}
			} else {
				tmpItem = ItemStack(Material.BARRIER)
				tmpItem.editMeta {
					it.displayName(
						Component.text(
							"X",
							NamedTextColor.RED
						)
					)
					it.lore(
						listOf(
							Component.text(
								"아무것도 없다 이 빡대가리야"
							)
						)
					)
				}
			}
			
			inv.setItem(i, tmpItem)
		}
	}
	
	override fun getInventory() = inv
}