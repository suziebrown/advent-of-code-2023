fun main() {
    class CubeSet(red: Int, green: Int, blue: Int){
        val red = red
        val green = green
        val blue = blue
    }

    class Game(id: Int, cubeSets: List<CubeSet>){
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
        val sets = data.split(";").map{parseCubeSet(it)}

        return Game(id, sets)
    }

    fun part1(input: List<String>): Int {
        val games = input.map{parseGame(it)}

        return 0;
    }

    fun part2(input: List<String>): Int {
        return 0;
    }

    printTestResult(2, 1, ::part1, 8)
//    printTestResult(2, 2, ::part2, 8)
    println()

    val input = readInput("Day01")
    println("Part 1: " + part1(input))
//    println("Part 2: " + part2(input))
}