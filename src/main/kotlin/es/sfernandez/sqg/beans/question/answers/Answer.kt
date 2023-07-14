package es.sfernandez.sqg.beans.question.answers

/**
 * An Answer represents all the information necessary to represent the answer of a question.
 *
 * @see AnswerTypes
 */
interface Answer {

    /** The type of the answer */
    val type : AnswerTypes
}