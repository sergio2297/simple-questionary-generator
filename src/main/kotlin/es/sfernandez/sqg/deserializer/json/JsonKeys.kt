package es.sfernandez.sqg.deserializer.json

/**
 * Object which stores all json keys for values of every entity that can be deserialized by [JsonDeserializer]
 *
 * This can be used to protected ourselves from further refactor
 *
 */
object JsonKeys {

    object Text {
        const val VALUE = "value"
        const val MARKUP = "markup"
    }

}