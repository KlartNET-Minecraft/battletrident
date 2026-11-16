package com.battletrident.eventManager

import net.kyori.adventure.text.Component
import net.kyori.adventure.title.Title
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerJoinEvent
import java.util.EventListener

class EventManager : EventListener {
	@EventHandler
	fun sayHello(event: PlayerJoinEvent) {
		val player = event.player
		
		player.showTitle(
			Title.title(
				Component.text(
					"안녕하세요!"
				),
				Component.text(
					"저희 서버에 오신 것을 안 환영합니다!"
				)
			)
		)
	}
}