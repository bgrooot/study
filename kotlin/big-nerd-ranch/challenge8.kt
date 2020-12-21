import kotlin.math.roundToInt

const val TAVERN_NAME = "Taernly's Folly"

var playerGold = 10
var playerSilver = 10

fun main() {
	placeOrder("shandy,Dragon's Breath,5.91")
	placeOrder("elixir,Shirley's Temple,4.12")
	placeOrder("elixir,Shirley's Temple,4.12")
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

private fun placeOrder(menuData: String) {
	val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
	val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
	val (type, name, price) = menuData.split(',')

	println("마드라갈은 $tavernMaster 에게 주문한다.")
	
	val purchased = performPurchase(price.toDouble())
	if (purchased) {
		val message = "마드리갈은 금화 $price 로 $name ($type)를 구입한다."
		val phrase = if (name == "Dragon's Breath") {
			"마드라갈이 감탄한다: ${toDragonSpeak("와, $name 진짜 좋구나!")}"
		} else {
			"마드라갈이 말한다: 감사합니다. $name."
		}

		println(message)
		println(phrase)
	} else {
		println("잔액이 부족하다.")
	}
}
