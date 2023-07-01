package es.sfernandez.sqg.model.answers

import es.sfernandez.sqg.model.answers.choices.Choice
import es.sfernandez.sqg.model.answers.replies.SingleReply

/**
 * SingleSelectionAnswer is a type of [SelectionAnswer] which only one right [Choice]
 *
 * @constructor Creates a new SingleSelectionAnswer with the specified possibleChoices and rightChoice.
 * @throws AnswerException if no possibleChoices are given or if the right choice isn't in the possible choices array
 *
 */
class SingleSelectionAnswer(
    possibleChoices: Array<Choice>,
    private val rightChoice: Choice
) : SelectionAnswer<SingleReply>(AnswerTypes.SINGLE_SELECTION, possibleChoices) {

    init {
        validateRightChoicesInPossibleChoices(rightChoice)
    }

    //---- Methods ----
    override fun isRight(reply: SingleReply) : Boolean {
        return rightChoice == reply.get()
    }
}