package es.sfernandez.sqg.deserializer.logs

/**
 * Log specific for deserialization process
 *
 * @property level Categorizes the level of significance
 * @property msg Log's message
 * @property context Info like Deserializer's name or deserialized input
 */
data class DeserializationLog
internal constructor(
    val level: Level,
    val msg: String,
    val context: DeserializationContext,
) {

    //---- Constants and Definitions ----
    /**
     * Categories of log's importance
     */
    enum class Level {
        DEBUG, WARNING, ERROR
    }

    override fun toString(): String {
        return "[${level.name}] $msg:\nContext:\n$context"
    }
}