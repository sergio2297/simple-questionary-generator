package es.sfernandez.sqg.beans.question.answers

/**
 * Answer exception is a specific type of Exception thrown by the answers module
 */
class AnswerException : RuntimeException {

    constructor() : super()

    constructor(msg : String) : super(msg)

    constructor(msg : String, throwable : Throwable) : super(msg, throwable)

}