fun main() {
	val name: String = "Estragon"
	val race: String = "gnome"
	var healthPoints: Int = 75
	var arrowInQuiver = 2
	val isBlessed: Boolean = true
	val isImmortal: Boolean = false 
	val auraVisible: Boolean = isBlessed && healthPoints > 50 || isImmortal
	val karma = (Math.pow(Math.random(), (110 - healthPoints) / 100.0) * 20).toInt()

	val faction: String = when (race) {
		"dwarf" -> "Keepers of the Mines"
		"gnome" -> "Keepers of the Mines"
		"orc" -> "Free People of the Rolling Hills"
		"human" -> "Free People of the Rolling Hills"
		else -> "None"
	}

	val auraColor: String = when (karma) {
		in 0..5 -> "red"
		in 6..10 -> "orange"
		in 11..15 -> "purple"
		in 16..20 -> "green"
		else -> "black"
	}

	val healthSummary: String = if (healthPoints != 100) "치유가 필요함!" else "건강함."
	val healthStatus: String = when (healthPoints) {
		100 -> "최상의 상태임!"
		in 90..99 -> "약간의 찰과상만 있음."
		in 75..89 -> if (isBlessed) "경미한 상처가 있지만 빨리 치유되고 있음!" else "경미한 상처만 있음."
		in 15..74 -> "많이 다친 것 같음. "
		else -> "최악의 상태임!"
	}
	val arrowStatus: String  = when {
		arrowInQuiver > 5 -> """
			화살이 충분함.
			더 이상의 화살을 가질 수 없음.
			"""
		else -> "화살이 충분하지 않음."
	}

	val hpFormatString = "(HP: $healthPoints)"
	val blessedFormatString = "${if (isBlessed) "(Blessed: $isBlessed)" else ""}"
	val auraFormatString = "${if (auraVisible) "(Aura: $auraColor)" else ""}"
	val statusFormatString = "$hpFormatString$auraFormatString$blessedFormatString -> $name $healthStatus"

	println(statusFormatString)
}
