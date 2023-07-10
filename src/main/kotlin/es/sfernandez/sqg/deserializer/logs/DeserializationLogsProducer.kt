package es.sfernandez.sqg.deserializer.logs

/**
 * A DeserializationLogsProducer can generate [DeserializationLog] and store them.
 *
 * It uses a [DeserializationLogFactory] to produce logs
 *
 */
class DeserializationLogsProducer {

    //---- Attributes ----
    lateinit var logFactory: DeserializationLogFactory

    private val logs: MutableList<DeserializationLog> = mutableListOf()

    //---- Methods ----
    /**
     * @return an Array with all [DeserializationLog] generated
     */
    fun logs() : Array<DeserializationLog> {
        return logs.toTypedArray()
    }

    /**
     * Clear all logs produced
     */
    fun clear() {
        logs.clear()
    }

    /**
     * Creates and stores a debug message
     *
     * @param msg Log's message
     */
    fun debug(msg: String) {
        logs.add(logFactory.debug(msg))
    }

    /**
     * Creates and stores a warning message
     *
     * @param msg Log's message
     */
    fun warning(msg: String) {
        logs.add(logFactory.warning(msg))
    }

    /**
     * Creates and stores a missing property message with warning level
     *
     * @param key Name of the missing property
     * @param replacement Replacement used instead of missing value. By default, is empty String
     */
    fun warningMissingProperty(key: String, replacement: String = "") {
        warning("Missing property \"$key\". " +
                "Default value \"$replacement\" assigned.")
    }

    /**
     * Creates and stores an incorrect type property message with warning level
     *
     * @param key Name of the missing property
     * @param replacement Replacement used instead of incorrect type value. By default, is empty String
     */
    fun warningIncorrectType(key: String, replacement: String = "") {
        warning("Incorrect type used for property \"$key\". " +
                "Default value \"$replacement\" assigned.")
    }

    /**
     * Creates and stores an undefined enum constant message with warning level
     *
     * @param key Name of the property
     * @param replacement Replacement used instead of undefined enum constant value.
     */
    fun <T : Enum<T>> warningUndefinedEnumConstant(key: String, replacement: T) {
        warning("Undefined enum constant used for property \"$key\". " +
                "Default value \"${replacement.name}\" assigned.")
    }

    /**
     * Creates and stores an error message
     *
     * @param msg Log's message
     */
    fun error(msg: String) {
        logs.add(logFactory.error(msg))
    }

    /**
     * Creates and stores a missing property message with error level
     *
     * @param key Name of the missing property
     */
    fun errorMissingProperty(key: String) {
        error("Missing mandatory property \"$key\".")
    }

    /**
     * Creates and stores an incorrect type property message with error level
     *
     * @param key Name of the missing property
     */
    fun errorIncorrectType(key: String) {
        error("Incorrect type used for property \"$key\".")
    }

    /**
     * Creates and stores an undefined enum constant message with error level
     *
     * @param key Name of the property
     */
    fun errorUndefinedEnumConstant(key: String) {
        error("Undefined enum constant used for property \"$key\".")
    }
}