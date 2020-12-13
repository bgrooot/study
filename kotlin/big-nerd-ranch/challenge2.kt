const val MAX_EXPERIENCE: Int = 5000

fun main() {
	val playerName: String = "Estragon"
    var playerCoin: Int = 50
	var experiencePoints: Int = 5
	var hasSteed: Boolean = false

    val pubName: String = "Unicorn's Horn"
    val pubMasterName: String = "Unknown"
	val pubMenu: Set<String> = hashSetOf<String>("Hydromel Liqueurs" , "Wine", "Lacroix")

	experiencePoints += 5

	println(experiencePoints)
	println(playerName.reversed())
}
