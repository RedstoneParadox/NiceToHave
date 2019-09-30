package redstoneparadox.nicetohave.util

/**
 * Useful for situations in which you need to cast to a type with generic
 * arguments; this func gets around type-erasure.
 */
inline fun <reified T> Any.tryAs(): T? = this as? T