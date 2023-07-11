package es.sfernandez.sqg.beans.question.answers.replies

import es.sfernandez.sqg.beans.question.answers.choices.Choice
import es.sfernandez.sqg.beans.question.answers.Answer

/**
 * SingleReply is a [Reply] that stores a single [Choice] that represents
 * the selection given as reply to an [Answer]
 *
 * @constructor Creates a SingleReply with the Choice given as argument
 */
class SingleReply (
    private val selection : Choice
) : Reply<Choice> {

    //---- Methods ----
    override fun get(): Choice {
        return selection
    }
}