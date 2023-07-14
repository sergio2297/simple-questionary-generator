package es.sfernandez.sqg.beans.question.answers

/**
 * TextAnswer is a type of [Answer] that represents answers which the user have to type the reply to the question.
 *
 * A TextAnswer can work two ways:
 * - **Storing all possible replies**. Then, if the given reply is exactly the same to one of the possible replies stored,
 *   the answer will be right.
 * - **Storing a [Regex]**. Then, if the given reply matches the regex, the answer will be right
 */
class TextAnswer
    private constructor(
        override val type: AnswerTypes = AnswerTypes.TEXT_INPUT,
        val checkWithRegex : Boolean,
        var possibleReplies : Array<String>,
        var replyRegex : Regex,
) : Answer {

    //---- Constructors ----
    /**
     * Creates a new TextAnswer with the possible replies passed as argument
     * @param possibleReplies Strings representing the possible replies to an answer
     */
    constructor(vararg possibleReplies : String) : this(AnswerTypes.TEXT_INPUT,false, arrayOf(*possibleReplies), Regex(""))

    /**
     * Creates a new TextAnswer regex that matches the correct replies
     * @param replyRegex regex that matches all the correct replies
     */
    constructor(replyRegex : Regex) : this(AnswerTypes.TEXT_INPUT, true, emptyArray(), replyRegex)

}