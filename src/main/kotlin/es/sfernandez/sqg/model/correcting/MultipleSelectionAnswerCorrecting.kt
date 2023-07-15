package es.sfernandez.sqg.model.correcting

import es.sfernandez.sqg.beans.question.answers.MultipleSelectionAnswer
import es.sfernandez.sqg.beans.question.answers.choices.Choice
import es.sfernandez.sqg.model.correcting.replies.MultipleReply

/**
 * A MultipleSelectionAnswerCorrecting can evaluate if a given [MultipleReply] to a [MultipleSelectionAnswer] is right
 * or not.
 *
 * It will be right iff all the choices ids referenced by the Reply are contained by the right choices ids of the
 * answer, and vice versa
 */
class MultipleSelectionAnswerCorrecting(
    override val answer: MultipleSelectionAnswer
) : AnswerCorrecting<MultipleSelectionAnswer, MultipleReply> {

    //---- Methods ----
    /**
     * Checks if the given reply is right to the answer
     *
     * It will be right iff all the choices ids referenced by the Reply are contained by the right choices ids of the
     * answer, and vice versa
     *
     * @param reply MultipleReply to check
     * @return true if the reply is right
     */
    override fun isRight(reply: MultipleReply): Boolean {
        val rightChoicesSet = hashSetOf(*answer.rightChoicesIds)
        val replyChoicesSet = hashSetWithIdsOf(reply)

        return rightChoicesSet == replyChoicesSet
    }

    private fun hashSetWithIdsOf(reply: MultipleReply) : HashSet<String> {
        return reply.get().asSequence()
            .map(Choice::id)
            .toHashSet()
    }

}