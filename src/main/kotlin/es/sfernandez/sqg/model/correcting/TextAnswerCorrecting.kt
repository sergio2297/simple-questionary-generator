package es.sfernandez.sqg.model.correcting

import es.sfernandez.sqg.beans.question.answers.TextAnswer
import es.sfernandez.sqg.model.correcting.replies.TextReply

/**
 * A TextAnswerCorrecting can evaluate if a given [TextReply] to a [TextAnswer] is right or not.
 *
 * It will depend on the answer's definition:
 * - If it is created with possible replies, then a given reply will be right iff its text is exactly the same as
 *  one of the possible replies.
 * - If it is created with a reply regex, then a given reply will be right iff its text is matched by the regex.
 */
class TextAnswerCorrecting(
    override val answer: TextAnswer
) : AnswerCorrecting<TextAnswer, TextReply> {

    //---- Methods ----
    /**
     * Checks if the given reply is right to the answer.
     *
     * It will depend on the answer's definition:
     * - If it is created with possible replies, then a given reply will be right iff its text is exactly the same as
     *  one of the possible replies.
     * - If it is created with a reply regex, then a given reply will be right iff its text is matched by the regex.
     *
     * @param reply TextReply to check
     * @return true if the reply is right
     */
    override fun isRight(reply: TextReply): Boolean {
        return if(shouldCheckWithRegex())
            checkWithRegex(reply)
        else
            checkWithPossibilities(reply)
    }

    private fun shouldCheckWithRegex() : Boolean {
        return answer.checkWithRegex
    }

    private fun checkWithRegex(reply : TextReply) : Boolean {
        return answer.replyRegex.matches(reply.get())
    }

    private fun checkWithPossibilities(reply : TextReply) : Boolean {
        return answer.possibleReplies.contains(reply.get())
    }

}