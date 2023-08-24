package es.sfernandez.sqg.model.correcting.answer.correctors

import es.sfernandez.sqg.beans.question.answers.correction.AnswerCorrection
import es.sfernandez.sqg.model.correcting.answer.replies.Reply

/**
 * An AnswerCorrector can evaluate a given reply [Reply] to an answer's correction [AnswerCorrection] and return
 * a result. For example: right or not, score, grade...
 *
 * Each AnswerCorrector can accept only some types of AnswerCorrection and Replies, so if you try to execute [correct]
 * with values that aren't accepted by the corrector, an [AnswerCorrectorException] will be thrown.
 *
 * @param RESULT type of value returned after the evaluation
 */
abstract class AnswerCorrector<out RESULT> {

    //---- Methods ----
    /**
     * @param correction AnswerCorrection to check
     * @return true if the given correction is accepted by the corrector
     */
    abstract fun accept(correction: AnswerCorrection) : Boolean

    protected inline fun <reified CORRECTION : AnswerCorrection> cast(correction: AnswerCorrection) : CORRECTION {
        return correction as CORRECTION
    }

    /**
     * @param reply Reply to check
     * @return true if the given reply is accepted by the corrector
     */
    abstract fun accept(reply: Reply<*>) : Boolean

    protected inline fun <reified REPLY: Reply<*>> cast(reply: Reply<*>) : REPLY {
        return reply as REPLY
    }

    /**
     * Evaluates the given reply with the answer's correction and returns a result.
     *
     * @param correction AnswerCorrection use to check the reply
     * @param reply Reply to evaluate
     * @return the result of evaluate the reply with the correction
     * @throws AnswerCorrectorException iff the given correction or reply isn't accepted
     * @see accept
     * @see accept
     */
    fun correct(correction: AnswerCorrection, reply: Reply<*>) : RESULT {
        if(!accept(correction)) throwNotAcceptedValueException(correction)
        if(!accept(reply)) throwNotAcceptedValueException(reply)

        return safeCorrect(correction, reply)
    }

    private fun throwNotAcceptedValueException(value: Any) {
        throw AnswerCorrectorException("Error. ${value.javaClass.canonicalName} isn't accepted by ${javaClass.canonicalName}")
    }

    protected abstract fun safeCorrect(correction: AnswerCorrection, reply: Reply<*>) : RESULT

}
