package com.battletrident.player

import com.battletrident.skills.SkillData
import org.bukkit.entity.Player


enum class UserState {
	LOBBY,
	PLAYING,
	DEAD,
	SPECTATING
}

data class UserData(
	val player: Player,
	var state: UserState,
	var skills: MutableList<SkillData>
)