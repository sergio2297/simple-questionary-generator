package es.sfernandez.sqg.deserializer

/**
 * Deserialization exception is a specific type of Exception thrown by the deserialization module
 */
class DeserializationException : RuntimeException {

    constructor() : super()

    constructor(msg : String) : super(msg)

    constructor(msg : String, throwable : Throwable) : super(msg, throwable)

}