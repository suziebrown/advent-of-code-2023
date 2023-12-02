import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/input/$name.txt").readLines()

fun changeConsoleToRed() = print("\u001b[31m");
fun changeConsoleToGreen() = print("\u001b[32m");
fun resetConsoleColour() = print("\u001b[0m");

fun printTestResult(day: Int, part: Int, checkFunction: (input: List<String>) -> Int, expectedResult: Int){
    val inputFileName = "Day${day.toString().padStart(2, '0')}_Part${part}_test"
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
