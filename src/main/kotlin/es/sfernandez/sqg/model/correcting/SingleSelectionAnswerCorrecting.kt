package es.sfernandez.sqg.model.correcting

import es.sfernandez.sqg.beans.question.answers.SingleSelectionAnswer
import es.sfernandez.sqg.model.correcting.replies.SingleReply

/**
 * A SingleSelectionAnswerCorrecting can evaluate if a given [SingleReply] to a [SingleSelectionAnswer] is right or not.
 *
 * It will be right iff the choice's id referenced by the Reply is equal to the right choice id of the answer
 */
class SingleSelectionAnswerCorrecting(
    override val answer: SingleSelectionAnswer
) : AnswerCorrecting<SingleSelectionAnswer, SingleReply> {

    //---- Methods ----
    /**
     * Checks if the given reply is right to the answer
     *
     * It will be right iff the choice's id referenced by the Reply is equal to the right choice id of the answer
     *
     * @param reply SingleReply to check
     * @return true if the reply is right
     */
    override fun isRight(reply: SingleReply): Boolean {
        return answer.rightChoiceId == reply.get().id
    }

}