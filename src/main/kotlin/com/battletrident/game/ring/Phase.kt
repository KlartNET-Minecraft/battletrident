package com.battletrident.game.ring

import com.battletrident.instance
import net.kyori.adventure.util.Ticks
import org.bukkit.scheduler.BukkitRunnable

enum class PhaseState {
	SHRINKING,
	ENDED,
}

object Phase {
	val ringList = listOf(
		Ring(0, 300.0, 2, 0.0, 10),
		Ring(1, 200.0, 30, 0.0001, 30),
		Ring(2, 100.0, 20, 0.1, 30),
		Ring(3, 1.0, 60, 0.5, -1),
	)

	var ringIter = ringList.iterator()
	var ringState: PhaseState = PhaseState.ENDED

	fun repeat() {
		val currentRing = ringIter.next()
		
		currentRing.ender = object : BukkitRunnable() {
			override fun run() {
				currentRing.applyInGame()
				
				if (ringIter.hasNext()) {
					currentRing.starter = object : BukkitRunnable() {
						override fun run() {
							ringIter.next()
							repeat()
						}
					}
					
					currentRing.starter?.runTaskLater(
						instance,
						currentRing.delay * Ticks.SINGLE_TICK_DURATION_MS
					)
				} else {
					ringState = PhaseState.ENDED
				}
			}
		}
		
		currentRing.ender?.runTaskLater(
			instance,
			currentRing.speed * Ticks.SINGLE_TICK_DURATION_MS
		)
	}

	fun reset() {
		ringState = PhaseState.ENDED
		ringIter = ringList.iterator()
		
		ringList[0].applyInGame()
	}
	
	fun start() {
		reset()
		
		repeat()
	}
}