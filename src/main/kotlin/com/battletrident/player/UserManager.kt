package com.battletrident.player

import org.bukkit.entity.Player
import java.util.UUID
import kotlin.collections.mutableMapOf


typealias mutablePlayerMap = MutableMap<UUID, UserData>

object UserManager {
	val users: mutablePlayerMap = mutableMapOf()

	fun add(player: Player) {
		users[player.uniqueId] = UserData(
			player = player,
			state = UserState.LOBBY,
			skills = mutableListOf()
		)
	}

	fun remove(player: Player) {
		users.remove(
			key = player.uniqueId
		)
	}
	
	fun getAll(): mutablePlayerMap {
		return users
	}
	fun getByState(state: UserState): List<UserData> {
		return users.values.filter { it.state == state }
	}
	fun getByFilter(filter: (UserData) -> Boolean): List<UserData> {
		return users.values.filter(filter)
	}
	
	fun getPlaying(): List<UserData> {
		return getByState(UserState.PLAYING)
	}
}