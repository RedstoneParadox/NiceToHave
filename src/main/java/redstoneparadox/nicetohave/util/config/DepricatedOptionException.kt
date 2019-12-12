package redstoneparadox.nicetohave.util.config

@Deprecated("use new config system.")
class DeprecatedOptionException(option: String): Exception("Attempted to access deprecated config option $option.") {
}