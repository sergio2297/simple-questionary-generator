package es.sfernandez.sqg.beans.question.answers

import es.sfernandez.sqg.beans.question.answers.replies.TextReply

/**
 * TextAnswer is a type of [Answer] that represents answers which the user have to type the reply to the question.
 *
 * A TextAnswer can work two ways:
 * - **Storing all possible replies**. Then, if the given reply is exactly the same to one of the possible replies stored,
 *   the answer will be right.
 * - **Checking replies by a given [Regex]**. Then, if the given reply matches the regex, the answer will be right
 */
class TextAnswer
    private constructor(
        private val checkWithRegex : Boolean,
        private val possibleReplies : Array<String>,
        private val replyRegex : Regex,
) : Answer<TextReply>(AnswerTypes.TEXT_INPUT) {

    //---- Constructors ----
    /**
     * Creates a new TextAnswer with the possible replies passed as argument
     * @param possibleReplies Strings representing the possible replies to an answer
     * @throws AnswerException if no possible replies indicated
     */
    constructor(vararg possibleReplies : String) : this(false, arrayOf(*possibleReplies), Regex("")) {
        if(possibleReplies.isEmpty()) {
            val className = TextAnswer::class.simpleName
            throw AnswerException("Error. At least one possible reply is needed to create a $className.")
        }
    }

    /**
     * Creates a new TextAnswer regex that matches the correct replies
     * @param replyRegex regex that matches all the correct replies
     * @throws AnswerException if the regex contains an empty pattern
     */
    constructor(replyRegex : Regex) : this(true, emptyArray(), replyRegex) {
        if(replyRegex.pattern.isEmpty())
            throw AnswerException("Error. The regex used for evaluate replies need a non empty pattern.")
    }

    //---- Methods ----
    override fun isRight(reply: TextReply): Boolean {
        return if(shouldCheckWithRegex())
            checkWithRegex(reply)
        else
            checkWithPossibilities(reply)
    }

    private fun shouldCheckWithRegex() : Boolean {
        return checkWithRegex
    }

    private fun checkWithRegex(reply : TextReply) : Boolean {
        return replyRegex.matches(reply.get())
    }

    private fun checkWithPossibilities(reply : TextReply) : Boolean {
        return possibleReplies.contains(reply.get())
    }

}