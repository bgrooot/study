const val TAVERN_NAME = "Taernly's Folly"

fun main() {
	placeOrder("shandy,Dragon's Breath,5.91")
}

private fun toDragonSpeak(phrase: String) = 
	phrase.replace(Regex("[aeiou]", RegexOption.IGNORE_CASE)) {
		when (it.value) {
			"a", "A" -> "4"
			"e", "E" -> "3"
			"i", "I" -> "1"
			"o", "O" -> "e"
			"u", "U" -> "|_|"
			else -> it.value
		}
	}

private fun placeOrder(menuData: String) {
	val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
	val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
	val (type, name, price) = menuData.split(',')

	println("마드라갈은 $tavernMaster 에게 주문한다.")

	val phrase = if (name == "Dragon's Breath") {
		"마드라갈이 감탄한다: ${toDragonSpeak("와, $name 진짜 좋구나!")}"
	} else {
		"마드라갈이 말한다: 감사합니다 $name."
	}

	println(phrase)
	println(toDragonSpeak("DRAGON'S BREATH: IT'S GOT WHAT ADVENTURES CRAVE!"))
}
