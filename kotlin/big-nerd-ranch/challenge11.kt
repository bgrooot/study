import kotlin.math.roundToInt
import java.io.File

const val TAVERN_NAME = "Taernly's Folly"

val patronList = mutableListOf("Eli", "Mordoc", "Sophie")
val lastName = listOf("Ironfoot", "Fernsworth", "Baggins")
val uniquePatrons = mutableSetOf<String>()
val menuList = File("data/tavern-menu-items.txt")
				.readText()
				.split("\n")
val patronGold = mutableMapOf<String, Double>()

fun main() {
	printMenuList()

	(0..9).forEach {
		val first = patronList.shuffled().first()
		val last = lastName.shuffled().first()
		val name = "$first $last"
		uniquePatrons += name
	}

	uniquePatrons.forEach {
		println(it)
		patronGold[it] = 6.0
	}

	var orderCount = 0
	while (orderCount <= 9 && uniquePatrons.isNotEmpty()) {
		placeOrder(uniquePatrons.shuffled().first(),
			menuList.shuffled().first())
		orderCount++
	}

	displayPatronBalances()
}

private fun displayPatronBalances() {
	patronGold.forEach { patron, balance ->
		println("$patron, balance: ${"%.2f".format(balance)}")
	}
}

fun printMenuList() {
	val title = "*** Welcom to Taernly's Folly ***"
	println(title)
	menuList.forEach {
		val (_, menu, price) = it.split(",")
		val dotLen = title.length - menu.length - price.length
		var dot = StringBuilder()
		(1..dotLen).forEach { dot.append(".") }
		println(menu + dot + price)
	}
}

fun performPurchase(patronName: String, price: Double): Boolean {
	val totalPurse = patronGold.getValue(patronName)
	if (totalPurse < price) return false
	patronGold[patronName] = totalPurse - price
	return true
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

private fun placeOrder(patronName: String, menuData: String) {
	val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
	val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
	val (type, name, price) = menuData.split(',')

	println("$patronName 은 $tavernMaster 에게 주문한다.")
	
	val purchased = performPurchase(patronName, price.toDouble())
	if (purchased) {
		val message = "$patronName 은 금화 $price 로 $name ($type)를 구입한다."
		val phrase = if (name == "Dragon's Breath") {
			"$patronName 이 감탄한다: ${toDragonSpeak("와, $name 진짜 좋구나!")}"
		} else {
			"$patronName 이 말한다: 감사합니다. $name."
		}

		println(message)
		println(phrase)
	} else {
		uniquePatrons -= patronName
		patronGold -= patronName
		println("잔액이 부족하다.")
	}
}
