import java.io.File
import kotlin.io.path.Path
import kotlin.io.path.readLines

fun changeConsoleToRed() = print("\u001b[31m")
fun changeConsoleToGreen() = print("\u001b[32m")
fun resetConsoleColour() = print("\u001b[0m")

fun getInputFilePath(fileName: String) = "src/input/$fileName.txt"

fun readInput(fileName: String) = Path(getInputFilePath(fileName)).readLines()

fun printTestResult(checkFunction: (input: List<String>) -> Int, expectedResult: Int, day: Int, part: Int) {
    val paddedDay = day.toString().padStart(2, '0')
    val dayFileName = "Day${paddedDay}_test"
    val partFileName = "Day${paddedDay}_Part${part}_test"

    val inputFileName = if (File(getInputFilePath(dayFileName)).exists()) dayFileName else partFileName

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

fun printResult(partFunction: (input: List<String>) -> Int, day: Int, part: Int) {
    val paddedDay = day.toString().padStart(2, '0')
    val input = readInput("Day${paddedDay}")

    val result = partFunction(input)

    println("Part $part: $result ")
}
