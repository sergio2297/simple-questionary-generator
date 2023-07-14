package es.sfernandez.sqg.model.correcting

import es.sfernandez.sqg.beans.question.answers.Answer
import es.sfernandez.sqg.model.correcting.replies.Reply

/**
 * An AnswerCorrecting can evaluate if a given reply to an answer is right or not.
 *
 * @param REPLY type of [Reply] that accept the answer
 */
interface AnswerCorrecting<out ANSWER : Answer, in REPLY : Reply<*>> {

    /** Answer to correct */
    val answer: ANSWER

    /**
     * Evaluates the given reply and return true if it is accepted by the answer
     *
     * @param reply Reply to evaluate
     * @return true if the given reply is right
     */
    fun isRight(reply : REPLY) : Boolean

}