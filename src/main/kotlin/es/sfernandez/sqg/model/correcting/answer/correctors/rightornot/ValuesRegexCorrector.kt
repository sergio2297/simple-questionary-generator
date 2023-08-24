package es.sfernandez.sqg.model.correcting.answer.correctors.rightornot

import es.sfernandez.sqg.beans.question.answers.correction.AnswerCorrection
import es.sfernandez.sqg.beans.question.answers.correction.rightornot.CorrectValuesRegex
import es.sfernandez.sqg.model.correcting.replies.Reply
import es.sfernandez.sqg.model.correcting.replies.TextReply

/**
 * A ValuesRegexCorrector can evaluate if a given [TextReply] to a [CorrectValuesRegex] answer's correction
 * is right or not.
 *
 * It will be right iff the correction's regex matches the reply's text.
 */
class ValuesRegexCorrector : RightOrNotAnswerCorrector() {

    override fun accept(correction: AnswerCorrection): Boolean {
        return super.accept(correction) && correction is CorrectValuesRegex
    }

    override fun accept(reply: Reply<*>): Boolean {
        return reply is TextReply
    }

    override fun safeCorrect(correction: AnswerCorrection, reply: Reply<*>): Boolean {
        return regexOf(correction).matches(textOf(reply))
    }

    private fun regexOf(correction: AnswerCorrection): Regex {
        return cast<CorrectValuesRegex>(correction).regex
    }

    private fun textOf(reply: Reply<*>): String {
        return cast<TextReply>(reply).get()
    }

}