import kotlin.math.roundToInt
import java.io.File

const val TAVERN_NAME = "Taernly's Folly"

var playerGold = 10
var playerSilver = 10
val patronList = mutableListOf("Eli", "Mordoc", "Sophie")
val lastName = listOf("Ironfoot", "Fernsworth", "Baggins")
val uniquePatrons = mutableSetOf<String>()
val menuList = File("data/tavern-menu-items.txt")
				.readText()
				.split("\n")

fun main() {
	printMenuList()

	(0..9).forEach {
		val first = patronList.shuffled().first()
		val last = lastName.shuffled().first()
		val name = "$first $last"
		uniquePatrons += name
	}

	var orderCount = 0
	while (orderCount <= 9) {
		placeOrder(uniquePatrons.shuffled().first(),
			menuList.shuffled().first())
		orderCount++
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

fun performPurchase(price: Double): Boolean {
	displayBalance()
	val totalPurse = playerGold + (playerSilver / 100.0)
	println("지갑 전체 금액 : 금화 $totalPurse")

	if (totalPurse < price) return false

	println("금화 $price 로 술을 구입함")

	val remainingBalance = totalPurse - price
	println("남은 잔액: ${"%.2f".format(remainingBalance)}")

	val remainingGold = remainingBalance.toInt()
	val remainingSilver = (remainingBalance % 1 * 100).roundToInt()
	playerGold = remainingGold
	playerSilver = remainingSilver
	displayBalance()

	return true
}

private fun displayBalance() {
	println("플레이어의 지갑 잔액: 금화: $playerGold 개, 은화: $playerSilver 개")
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
	
	val purchased = performPurchase(price.toDouble())
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
		println("잔액이 부족하다.")
	}
}
