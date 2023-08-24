package es.sfernandez.sqg.model.correcting.answer.correctors.rightornot

import es.sfernandez.sqg.beans.question.answers.correction.AnswerCorrection
import es.sfernandez.sqg.beans.question.answers.correction.rightornot.RightOrNotCorrection
import es.sfernandez.sqg.model.correcting.answer.correctors.AnswerCorrector
import es.sfernandez.sqg.model.correcting.replies.Reply

/**
 * A RightOrNotAnswerCorrector can evaluate if a given reply [Reply] to an answer's correction [AnswerCorrection]
 * is right or not, representing each possibility with boolean values.
 */
abstract class RightOrNotAnswerCorrector : AnswerCorrector<Boolean>() {

    /**
     * @param correction AnswerCorrection to check
     * @return true if the correction is instance of [RightOrNotCorrection]
     */
    override fun accept(correction: AnswerCorrection): Boolean {
        return correction is RightOrNotCorrection
    }

}