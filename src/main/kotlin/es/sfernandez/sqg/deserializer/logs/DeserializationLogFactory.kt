package es.sfernandez.sqg.deserializer.logs

/**
 * A deserialization log factory it's the only way to create [DeserializationLog]
 * from outside the module
 *
 * @property context Context of all [DeserializationLog] created
 */
open class DeserializationLogFactory(
    private val context: DeserializationContext
) {

    /**
     * @param msg Message of the generated log
     * @return a new [DeserializationLog] of [DeserializationLog.Level.DEBUG]
     */
    fun debug(msg: String) : DeserializationLog {
        return DeserializationLog(DeserializationLog.Level.DEBUG, msg, context)
    }

    /**
     * @param msg Message of the generated log
     * @return a new [DeserializationLog] of [DeserializationLog.Level.WARNING]
     */
    fun warning(msg: String) : DeserializationLog {
        return DeserializationLog(DeserializationLog.Level.WARNING, msg, context)
    }

    /**
     * @param msg Message of the generated log
     * @return a new [DeserializationLog] of [DeserializationLog.Level.ERROR]
     */
    fun error(msg: String) : DeserializationLog {
        return DeserializationLog(DeserializationLog.Level.ERROR, msg, context)
    }

}