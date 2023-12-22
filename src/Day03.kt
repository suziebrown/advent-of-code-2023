import kotlin.math.max
import kotlin.math.min

fun main() {
    class SchematicNumber(
        val value: Int,
        val row: Int,
        val startColumn: Int,
        val endColumn: Int,
        val isAdjacentToSymbol: Boolean? = null
    )

    val numberRegex = "\\d+".toRegex()
    val symbolRegex = "[^\\d.]".toRegex()

    fun checkIsAdjacentToSymbol(row: Int, startColumn: Int, endColumn: Int, input: List<String>): Boolean {
        val inputWidth = input[0].length
        val inputHeight = input.size

        if (row > 0) {
            val neighboursAbove =
                input[row - 1].subSequence(max(startColumn - 1, 0), min(endColumn + 2, inputWidth))
            if (symbolRegex.containsMatchIn(neighboursAbove)) return true
        }
        if (row < inputHeight - 1) {
            val neighboursBelow =
                input[row + 1].subSequence(max(startColumn - 1, 0), min(endColumn + 2, inputWidth))
            if (symbolRegex.containsMatchIn(neighboursBelow)) return true
        }
        if (startColumn > 0) {
            val neighbourLeft = input[row].subSequence(startColumn - 1, startColumn)
            if (symbolRegex.containsMatchIn(neighbourLeft)) return true
        }
        if (endColumn < inputWidth - 1) {
            val neighbourRight = input[row].subSequence(endColumn + 1, endColumn + 2)
            if (symbolRegex.containsMatchIn(neighbourRight)) return true
        }

        return false
    }

    fun mapMatchToSchematicNumber(match: MatchResult, row: Int): SchematicNumber {
        val value = match.value.toInt()
        val startColumn = match.range.first
        val endColumn = match.range.last

        return SchematicNumber(value, row, startColumn, endColumn)
    }

    fun part1(input: List<String>): Int {
        val partNumbers =
            input.map {
                numberRegex.findAll(it).toList()
            }.mapIndexed { index, matches ->
                matches.map { mapMatchToSchematicNumber(it, index) }
            }.flatten()

        val foo = partNumbers
            .filter { checkIsAdjacentToSymbol(it.row, it.startColumn, it.endColumn, input) }

        return foo.sumOf { it.value }
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    printTestResult(::part1, 4361, 3, 1)
    printTestResult(::part2, 467835, 3, 2)
    println()
    printResult(::part1, 3, 1)
    printResult(::part2, 3, 2)
}
