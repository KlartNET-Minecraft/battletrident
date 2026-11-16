package com.battletrident.playerManager

import org.bukkit.entity.Player
import java.util.UUID
import kotlin.collections.mutableMapOf

typealias mutablePlayerMap = MutableMap<UUID, PlayerData>

object UserManager {
	val users: mutablePlayerMap = mutableMapOf()
	
	fun getAll(): mutablePlayerMap {
		return users
	}
	
	fun add(player: Player) {
		users[player.uniqueId] = PlayerData(
			player = player,
			state = PlayerState.LOBBY
		)
	}
	
	fun remove(player: Player) {
		users.remove(player.uniqueId)
	}
}