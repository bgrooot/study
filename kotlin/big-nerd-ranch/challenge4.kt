fun main() {
	val name: String = "Estragon"
	val race: String = "gnome"
	var healthPoints: Int = 75
	var arrowInQuiver = 2
	val isBlessed: Boolean = true
	val isImmortal: Boolean = false 
	val auraVisible: Boolean = isBlessed && healthPoints > 50 || isImmortal
	val karma = (Math.pow(Math.random(), (110 - healthPoints) / 100.0) * 20).toInt()

	val faction: String = faction(race)
	val auraColor: String = auraColor(auraVisible, healthPoints, isImmortal, karma)
	val healthSummary: String = if (healthPoints != 100) "치유가 필요함!" else "건강함."
	val healthStatus: String = formatHealthStatus(healthPoints, isBlessed) 
	val arrowStatus: String = formatArrowStatus(arrowInQuiver)

	printPlayerStatus(auraColor, isBlessed, name, healthStatus)
	castFireball(5)	
	castFireball()
}

private fun faction(race: String): String =
	when (race) {
		"dwarf" -> "Keepers of the Mines"
		"gnome" -> "Keepers of the Mines"
		"orc" -> "Free People of the Rolling Hills"
		"human" -> "Free People of the Rolling Hills"
		else -> "None"
	}

private fun formatHealthStatus(healthPoints: Int, isBlessed: Boolean): String =
	when (healthPoints) {
		100 -> "최상의 상태임!"
		in 90..99 -> "약간의 찰과상만 있음."
		in 75..89 -> if (isBlessed) "경미한 상처가 있지만 빨리 치유되고 있음!" else "경미한 상처만 있음."
		in 15..74 -> "많이 다친 것 같음. "
		else -> "최악의 상태임!"
	}

private fun formatArrowStatus(arrowInQuiver: Int): String =
	when {
		arrowInQuiver > 5 -> """
			화살이 충분함.
			더 이상의 화살을 가질 수 없음.
			"""
		else -> "화살이 충분하지 않음."
	}
								
private fun printPlayerStatus(auraColor: String, isBlessed: Boolean, name: String, healthStatus: String) {
	println("(Aura: $auraColor) (Blessed: ${if (isBlessed) "YES" else "NO"})")
	println("$name $healthStatus")
}

private fun auraColor(isBlessed: Boolean, healthPoints: Int, isImmortal: Boolean, karma: Int): String =
	 if (isBlessed && healthPoints > 50 || isImmortal) {
		when (karma) {
			in 0..5 -> "RED"
			in 6..10 -> "ORANGE"
			in 11..15 -> "PURPLE"
			in 16..20 -> "GREEN"
			else -> "NONE"
		}
	 } else {
		 "NONE"
	 }

private fun castFireball(numFireballs: Int = 2) {
	println("한 덩어리의 파이어볼이 나타난다. (x$numFireballs)")
}
