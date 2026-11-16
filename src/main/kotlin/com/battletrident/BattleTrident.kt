package com.battletrident

import org.bukkit.plugin.java.JavaPlugin

class BattleTrident : JavaPlugin() {
	override fun onEnable() {
		instance = this
	}

	override fun onDisable() {
		
	}
}