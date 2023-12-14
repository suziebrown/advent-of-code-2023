import kotlin.io.path.Path
import kotlin.io.path.readLines

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/input/$name.txt").readLines()

fun changeConsoleToRed() = print("\u001b[31m")
fun changeConsoleToGreen() = print("\u001b[32m")
fun resetConsoleColour() = print("\u001b[0m")

fun printTestResult(checkFunction: (input: List<String>) -> Int, expectedResult: Int, day: Int, part: Int? = null) {
    val paddedDay = day.toString().padStart(2, '0')

    val inputFileName = if (part != null) "Day${paddedDay}_Part${part}_test" else "Day${paddedDay}_test"
    val input = readInput(inputFileName)

    val result = checkFunction(input)
    print("Part $part test: $result ")

    if (result == expectedResult) {
        changeConsoleToGreen()
        println("PASS")
    } else {
        changeConsoleToRed()
        println("FAIL")
    }

    resetConsoleColour()
}
