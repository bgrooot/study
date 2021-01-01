import java.io.File

fun main() {
	Game.play()
}

open class Room(val name: String){
	protected open val dangerLevel = 5

	open fun load() = "아무도 여기에 오지 않았습니다..."
}

open class TownSquare : Room("Town Square") {
	override val dangerLevel = super.dangerLevel - 3
	private val bellSound = "댕댕"
	
	final override fun load() = "당신의 참여를 주민들이 다 함께 환경합니다!!\r\n${ringBell()}"
	public fun ringBell() = "당신의 도착을 종탑에서 알림니다. $bellSound"
}

object Game {
	private var currentRoom: Room = TownSquare()
	private val player = Player("Madrigal")
	private var quit = false

	private val worldMap = listOf(
		listOf(currentRoom, Room("Tavern"), Room("Back Room")),
		listOf(Room("Long Corridor"), Room("Generic Room"))
	)

	fun play() {
		while (!quit) {
			println("> 명령을 입력하세요: ")
			println(GameInput(readLine()).processCommand())
		}
	}

	private fun move(directionInput: String) = 
		try {
			val direction = Direction.valueOf(directionInput.toUpperCase())
			val newPosition = direction.updateCoordinate(player.currentPosition)
			if (!newPosition.isInBounds) {
				throw IllegalStateException("$direction 쪽 방향이 범위를 벗어남.")
			}

			val newRoom = worldMap[newPosition.y][newPosition.x]
			player.currentPosition = newPosition
			currentRoom = newRoom
			"OK, $direction 방향의 ${newRoom.name}로 이동했습니다."
		} catch (e: Exception) {
			"잘못된 방향임: $directionInput."
		}

	private fun displayMap(): String {
		var map = StringBuilder()
		worldMap.forEach { that ->
			that.forEach {
				map.append(it.takeIf{ it == currentRoom }?.let{ "O " } ?: "X ")
			}

			if (worldMap.last() != that) map.append("\n")
		}

		return map.toString()
	}

	private fun ring(): String {
		if (currentRoom is TownSquare) {
			return (currentRoom as TownSquare).ringBell()
		} else {
			return "종이 없습니다."
		}
	}

	private fun quit(): String {
		quit = true
		return "게임을 종료합니다."
	}

	private class GameInput(arg: String?) {
		private val input = arg ?: ""
		val command = input.split(" ")[0]
		val argument = input.split(" ").getOrElse(1, { "" })

		fun processCommand(): String = when (command.toLowerCase()) {
			"map" -> displayMap()
			"move" -> move(argument)
			"ring" -> ring()
			"quit", "exit" -> quit() 
			else -> commandNotFound()
		}

		private fun commandNotFound(): String = "적합하지 않은 명령입니다!"
	}
}

enum class Direction(private val coordinate: Coordinate) {
	NORTH(Coordinate(0, -1)),
	EAST(Coordinate(1, 0)),
	SOUTH(Coordinate(0, 1)),
	WEST(Coordinate(-1, 0));

	fun updateCoordinate(playerCoordinate: Coordinate) = coordinate + playerCoordinate
}

data class Coordinate(val x: Int, val y: Int) {
	val isInBounds = x >= 0 && y >= 0
	operator fun plus(other: Coordinate) = Coordinate(x + other.x, y + other.y)
}

class Player(_name: String, var healthPoints: Int = 100) {

	var name = _name
		get() = "${field.capitalize()} of $hometown"
		private set(value) {
			field = value.trim()
		}

	val hometown by lazy { selectHometown() }
	var currentPosition = Coordinate(0, 0)

	fun selectHometown() = File("data/towns.txt")
			.readText()
			.split("\r\n")
			.shuffled()
			.first()
}
