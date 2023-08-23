package es.sfernandez.sqg.model.correcting.answer.replies

import es.sfernandez.sqg.beans.question.answers.Answer

/**
 * TextReply is a [Reply] that stores a String that represents the
 * reply given to an [Answer]
 *
 * @constructor Creates a TextReply with the text given as argument
 */
class TextReply(
    private val text : String
) : Reply<String> {

    //---- Methods ----
    override fun get(): String {
        return text
    }
}