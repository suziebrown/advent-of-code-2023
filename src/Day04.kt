import kotlin.math.pow

fun main() {
    class Scratchcard(
        val cardNumber: Int,
        val winningNumbers: List<Int>,
        val yourNumbers: List<Int>,
    ) {
        val numberOfMatches = winningNumbers.map { yourNumbers.contains(it) }.count { it }
        val score = 2.0.pow(numberOfMatches.toDouble() - 1).toInt()
    }

    fun mapToScratchcard(input: String): Scratchcard {
        val (cardNumber, winningNumbersString, yourNumbersString) = input.split(":", "|")

        return Scratchcard(
            cardNumber = cardNumber.removePrefix("Card").trim().toInt(),
            winningNumbers = winningNumbersString.trim().split(Regex("\\s+")).map { it.toInt() },
            yourNumbers = yourNumbersString.trim().split(Regex("\\s+")).map { it.toInt() },
        )
    }

    fun part1(input: List<String>): Int {
        val scratchCards = input.map {
            mapToScratchcard(it)
        }

        return scratchCards.sumOf { it.score }
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    printTestResult(::part1, 13, 4, 1)
    printTestResult(::part2, 30, 4, 2)
    println()
    printResult(::part1, 4, 1)
    printResult(::part2, 4, 2)
}
