package net.redstoneparadox.nicetohave.util

fun secondsToTicks(seconds : Int): Int {
    return seconds * 20
}

fun minutesToTicks(minutes : Int, seconds: Int): Int {
    return ((minutes * 60) + seconds) * 20
}