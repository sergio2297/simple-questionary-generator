package es.sfernandez.sqg.deserializer

/**
 * Deserializer is able to deserialize a given text (representing an XML, UUID, JSON...) to a Kotlin object
 *
 * @param T type of object deserialized
 */
interface Deserializer<T> {

    /**
     * Deserialize the given text to an object of type T
     *
     * @param text Text to deserialize (it may representa XML, JSON, UUID...)
     * @return the result of deserialize the given text
     */
    fun deserialize(text : String) : T

}