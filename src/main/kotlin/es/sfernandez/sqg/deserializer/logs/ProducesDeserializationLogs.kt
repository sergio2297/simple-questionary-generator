package es.sfernandez.sqg.deserializer.logs

/**
 * Something that ProducesDeserializationLogs can return its produced [DeserializationLog]
 */
interface ProducesDeserializationLogs {

    /** Deserialization logs produced */
    fun logs() : Array<DeserializationLog>

}