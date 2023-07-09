package es.sfernandez.sqg.deserializer.logs

import es.sfernandez.sqg.deserializer.Deserializer

/**
 * Context of a [DeserializationLog]
 *
 * @property input String to deserialize which causes the log
 * @property deserializerClass [Deserializer]'s class of the object which produces the log
 */
data class DeserializationContext(
    val input: String,
    val deserializerClass: Class<out Deserializer<*>>,
) {

    override fun toString(): String {
        return "Deserializer: ${deserializerClass.simpleName}\nInput: $input"
    }

}