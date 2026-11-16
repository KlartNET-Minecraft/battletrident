package com.battletrident.game

enum class GameState {
	LOBBY,
	STARTING,
	IN_PROGRESS,
	ENDING
}

data class Game(
	val state: GameState,
)

fun Game.start() {
	
}