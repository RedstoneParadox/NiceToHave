package redstoneparadox.nicetohave.util

@ExperimentalUnsignedTypes
fun rgbToInt(red : UByte, green : UByte, blue : UByte): Int {

    val redInt : Int = red.toInt() shl 16
    val greenInt : Int = green.toInt() shl 8
    val blueInt : Int = blue.toInt()

    return (redInt or greenInt or blueInt)
}