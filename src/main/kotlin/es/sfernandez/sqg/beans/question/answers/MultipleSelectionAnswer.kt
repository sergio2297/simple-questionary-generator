package es.sfernandez.sqg.beans.question.answers

import es.sfernandez.sqg.beans.question.answers.choices.Choice
import es.sfernandez.sqg.beans.question.answers.replies.MultipleReply

/**
 * MultipleSelectionAnswer is a type of [SelectionAnswer] which multiple right [Choice]
 *
 * @constructor Creates a new MultipleSelectionAnswer with the specified possibleChoices and rightChoices.
 * @throws AnswerException if no possibleChoices are given or if one of the right choices isn't in the possible choices array
 *
 */
class MultipleSelectionAnswer(
    possibleChoices : Array<Choice>,
    private val rightChoices : Array<Choice>,
) : SelectionAnswer<MultipleReply>(AnswerTypes.MULTIPLE_SELECTION, possibleChoices) {

    init {
        validateRightChoicesInPossibleChoices(*rightChoices)
    }

    //---- Methods ----
    override fun isRight(reply: MultipleReply): Boolean {
        val rightChoicesSet = hashSetOf(*rightChoices)
        val replyChoicesSet = hashSetOf(*reply.get())

        return rightChoicesSet == replyChoicesSet
    }

}