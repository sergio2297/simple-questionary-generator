package es.sfernandez.sqg.beans.question.answers

import es.sfernandez.sqg.beans.question.answers.replies.Reply

/**
 * An Answer represents all the information necessary to represent the answer of a question.
 *
 * Also, it can evaluate a given reply to the answer and return if it is right or not.
 *
 * @param REPLY type of [Reply] that accept the answer
 * @property type Type of the answer.
 * @constructor Create empty Answer
 * @see AnswerTypes
 */
abstract class Answer<in REPLY : Reply<*>>(
    val type : AnswerTypes
) {

    /**
     * Evaluates the given reply and return true if it is accepted by the answer
     *
     * @param reply Reply to evaluate
     * @return true if the given reply is right
     */
    abstract fun isRight(reply : REPLY) : Boolean

}