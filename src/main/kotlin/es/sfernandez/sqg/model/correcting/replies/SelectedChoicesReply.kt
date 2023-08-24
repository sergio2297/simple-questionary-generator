package es.sfernandez.sqg.model.correcting.replies

import es.sfernandez.sqg.beans.question.answers.Answer
import es.sfernandez.sqg.beans.question.answers.Choice

/**
 * SelectedChoicesReply is a [Reply] that can store an array of [Choice] that represents
 * the selection given as reply to an [Answer]
 */
class SelectedChoicesReply : Reply<Array<Choice>> {

    //---- Attributes ----
    private val selection: Array<Choice>

    //---- Constructor ----
    constructor(vararg selection: Choice) {
        this.selection = arrayOf(*selection)
    }

    //---- Methods ----
    override fun get(): Array<Choice> {
        return selection
    }

}