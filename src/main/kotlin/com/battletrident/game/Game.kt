package com.battletrident.gameManager

enum class GameStateType {
	LOBBY,
	STARTING,
	IN_PROGRESS,
	ENDING
}

data class GameState(
	val state: GameStateType
)