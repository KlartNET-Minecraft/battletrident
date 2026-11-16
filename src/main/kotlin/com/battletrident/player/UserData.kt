package com.battletrident.player

import org.bukkit.entity.Player

enum class PlayerState {
	LOBBY,
	PLAYING,
	DEAD,
	SPECTATING
}

data class PlayerData(
	val player: Player,
	var state: PlayerState
)