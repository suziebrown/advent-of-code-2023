fun main() {
    class CubeSet(val red: Int, val green: Int, val blue: Int)
    class Game(val id: Int, val cubeSets: List<CubeSet>)

    fun getNumberOfBallsOfColour(cubeSetInput: String, colour: String): Int {
        return ("\\d+(?=\\s$colour)").toRegex().find(cubeSetInput)?.value?.toInt() ?: 0
    }

    fun parseCubeSet(cubeSetInput: String): CubeSet {
        val red = getNumberOfBallsOfColour(cubeSetInput, "red")
        val green = getNumberOfBallsOfColour(cubeSetInput, "green")
        val blue = getNumberOfBallsOfColour(cubeSetInput, "blue")

        return CubeSet(red, green, blue)
    }

    fun parseGame(gameInput: String): Game {
        val (title, data) = gameInput.split(":")
        val id = title.split(" ")[1].toInt()
        val sets = data.split(";").map { parseCubeSet(it) }

        return Game(id, sets)
    }

    fun isPossible(totalCubes: CubeSet, game: Game): Boolean {
        return !game.cubeSets.any { it.red > totalCubes.red || it.green > totalCubes.green || it.blue > totalCubes.blue }
    }

    fun getMaxCubeSet(game: Game): CubeSet {
        val maxRed = game.cubeSets.maxOfOrNull { it.red } ?: 0
        val maxGreen = game.cubeSets.maxOfOrNull { it.green } ?: 0
        val maxBlue = game.cubeSets.maxOfOrNull { it.blue } ?: 0

        return CubeSet(maxRed, maxGreen, maxBlue)
    }

    fun calculatePower(cubeSet: CubeSet): Int {
        return cubeSet.red * cubeSet.green * cubeSet.blue
    }

    fun part1(input: List<String>): Int {
        val games = input.map { parseGame(it) }
        val totalCubes = CubeSet(12, 13, 14)
        return games.filter { isPossible(totalCubes, it) }.sumOf { it.id }
    }

    fun part2(input: List<String>): Int {
        val games = input.map { parseGame(it) }
        return games.map { getMaxCubeSet(it) }.map { calculatePower(it) }.sum()
    }

    printTestResult(::part1, 8, 2, 1)
    printTestResult(::part2, 2286, 2, 2)
    println()
    printResult(::part1, 2, 1)
    printResult(::part2, 2, 2)
}