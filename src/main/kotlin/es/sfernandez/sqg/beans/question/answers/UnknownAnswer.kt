package es.sfernandez.sqg.beans.question.answers

/**
 * An Unknown type of answer
 *
 * It's used when an answer isn't well constructed
 *
 * @constructor Create an Unknown answer
 */
class UnknownAnswer
private constructor (
    override val type: AnswerTypes = AnswerTypes.UNKNOWN
) : Answer {

    constructor() : this(AnswerTypes.UNKNOWN)

}