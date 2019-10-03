package redstoneparadox.nicetohave.util.config

class DeprecatedOptionException(option: String): Exception("Attempted to access deprecated config option $option.") {
}