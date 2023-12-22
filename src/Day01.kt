fun main() {
    val digitsSpelledOut = mapOf(
        "one" to '1',
        "two" to '2',
        "three" to '3',
        "four" to '4',
        "five" to '5',
        "six" to '6',
        "seven" to '7',
        "eight" to '8',
        "nine" to '9'
    )

    fun convertWrittenNumberToChar(digit: String): Char {
        if (digit in digitsSpelledOut.keys) {
            return digitsSpelledOut[digit]!!
        }

        if (digit.length == 1 && digit[0] in '0'..'9') {
            return digit[0]
        }

        throw Exception("Invalid digit $digit")
    }

    fun surroundWithParentheses(s: String) = "($s)"

    fun getTwoDigitNumberFromLine(line: String, regex: Regex): Int {
        val matchResult = regex.findAll(line)
        if (matchResult.none()) {
            throw Exception("No digit found in $line")
        }

        val firstMatch = matchResult.first().value
        val lastMatch = matchResult.last().value

        return "${convertWrittenNumberToChar(firstMatch)}${convertWrittenNumberToChar(lastMatch)}".toInt()
    }

    fun part1(input: List<String>): Int {
        val regex = "([0-9])".toRegex()
        val calibrationValues = input.map { getTwoDigitNumberFromLine(it, regex) }
        return calibrationValues.sum()
    }

    fun part2(input: List<String>): Int {
        val regex = "([0-9])|${digitsSpelledOut.keys.map { surroundWithParentheses(it) }.joinToString("|")}".toRegex()
        val calibrationValues = input.map { getTwoDigitNumberFromLine(it, regex) }
        return calibrationValues.sum()
    }

    printTestResult(::part1, 142, 1, 1)
    printTestResult(::part2, 281, 1, 2)
    println()
    printResult(::part1, 1, 1)
    printResult(::part2, 1, 2)
}
