package es.sfernandez.sqg.model.correcting.answer.correctors.rightornot

import es.sfernandez.sqg.beans.question.answers.Choice
import es.sfernandez.sqg.beans.question.answers.correction.AnswerCorrection
import es.sfernandez.sqg.beans.question.answers.correction.rightornot.RightChoiceIds
import es.sfernandez.sqg.model.correcting.answer.replies.Reply
import es.sfernandez.sqg.model.correcting.answer.replies.SelectedChoicesReply

/**
 * A RightOrNotAnswerCorrector can evaluate if a given [SelectedChoicesReply] to a [RightChoiceIds] answer's correction
 * is right or not.
 *
 * It will be right iff the reply's ids are equals to correction's in number and value.
 */
class RightChoiceIdsCorrector : RightOrNotAnswerCorrector() {

    override fun accept(correction: AnswerCorrection): Boolean {
        return super.accept(correction) && correction is RightChoiceIds
    }

    override fun accept(reply: Reply<*>): Boolean {
        return reply is SelectedChoicesReply
    }

    override fun safeCorrect(correction: AnswerCorrection, reply: Reply<*>): Boolean {
        return choiceIdsOf(correction) == choiceIdsOf(reply)
    }

    private fun choiceIdsOf(correction: AnswerCorrection): HashSet<String> {
        return hashSetOf(*cast<RightChoiceIds>(correction).ids)
    }

    private fun choiceIdsOf(reply: Reply<*>): HashSet<String> {
        return cast<SelectedChoicesReply>(reply).get().asSequence()
            .map(Choice::id)
            .toHashSet()
    }

}