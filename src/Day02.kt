fun main() {
    class CubeSet(red: Int, green: Int, blue: Int) {
        val red = red
        val green = green
        val blue = blue
    }

    class Game(id: Int, cubeSets: List<CubeSet>) {
        val id = id
        val cubeSets = cubeSets
    }

    fun parseCubeSet(cubeSetInput: String): CubeSet {
        val red = ("\\d+(?=\\sred)").toRegex().find(cubeSetInput)?.value?.toInt() ?: 0
        val green = ("\\d+(?=\\sgreen)").toRegex().find(cubeSetInput)?.value?.toInt() ?: 0
        val blue = ("\\d+(?=\\sblue)").toRegex().find(cubeSetInput)?.value?.toInt() ?: 0

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

    printTestResult(::part1, 8, 2)
    printTestResult(::part2, 2286, 2)
    println()

    val input = readInput("Day02")
    println("Part 1: " + part1(input))
    println("Part 2: " + part2(input))
}