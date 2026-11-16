package com.battletrident.event

import com.battletrident.player.UserManager
import net.kyori.adventure.text.Component
import net.kyori.adventure.title.Title
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerJoinEvent
import java.util.EventListener

class EventManager : EventListener {
	@EventHandler
	fun whenPlayerJoins(event: PlayerJoinEvent) {
		val player = event.player

		UserManager.add(player)
	}
	
	@EventHandler
	fun whenPlayerLeaves(event: PlayerJoinEvent) {
		val player = event.player

		UserManager.remove(player)
	}
}