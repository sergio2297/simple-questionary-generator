package es.sfernandez.sqg.model.correcting.replies

import es.sfernandez.sqg.beans.question.answers.Answer
import es.sfernandez.sqg.beans.question.answers.choices.Choice

/**
 * MultipleReply is a [Reply] that stores an array of [Choice] that represents
 * the selection given as reply to an [Answer]
 *
 * @constructor Creates a MultipleReply with the Choices given as argument
 */
class MultipleReply(
    private val selection : Array<Choice>
) : Reply<Array<Choice>> {

    //---- Methods ----
    override fun get(): Array<Choice> {
        return selection
    }

}