package es.sfernandez.sqg.model.correcting.answer.correctors.rightornot

import es.sfernandez.sqg.beans.question.answers.correction.AnswerCorrection
import es.sfernandez.sqg.beans.question.answers.correction.rightornot.PossibleValues
import es.sfernandez.sqg.model.correcting.replies.Reply
import es.sfernandez.sqg.model.correcting.replies.TextReply

/**
 * A PossibleValuesCorrector can evaluate if a given [TextReply] to a [PossibleValues] answer's correction
 * is right or not.
 *
 * It will be right iff the reply's text is in correction's possible values set.
 */
class PossibleValuesCorrector : RightOrNotAnswerCorrector() {

    override fun accept(correction: AnswerCorrection): Boolean {
        return super.accept(correction) && correction is PossibleValues
    }

    override fun accept(reply: Reply<*>): Boolean {
        return reply is TextReply
    }

    override fun safeCorrect(correction: AnswerCorrection, reply: Reply<*>): Boolean {
        return possibleValuesOf(correction).contains(textOf(reply))
    }

    private fun possibleValuesOf(answerCorrection: AnswerCorrection): Array<String> {
        return cast<PossibleValues>(answerCorrection).values
    }

    private fun textOf(reply: Reply<*>): String {
        return cast<TextReply>(reply).get()
    }

}