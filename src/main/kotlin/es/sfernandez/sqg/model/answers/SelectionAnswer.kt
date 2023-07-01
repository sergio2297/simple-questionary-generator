package es.sfernandez.sqg.model.answers

import es.sfernandez.sqg.model.answers.choices.Choice
import es.sfernandez.sqg.model.answers.replies.Reply
import java.util.*

/**
 * SelectionAnswer is a type of [Answer] that represents answers which all possible [Choice] are displayed
 * to the user, and he has to select one or more of them
 *
 * @see SingleSelectionAnswer
 * @see MultipleSelectionAnswer
 */
sealed class SelectionAnswer<REPLY : Reply<*>>(
    type: AnswerTypes,
    private val possibleChoices: Array<Choice>
) : Answer<REPLY>(type) {

    init {
        if(possibleChoices.isEmpty())
            throw AnswerException("Error. Possible choices of a ${SelectionAnswer::class.simpleName} can't be empty")
    }

    //---- Methods ----
    protected fun validateRightChoicesInPossibleChoices(vararg choices : Choice) {
        if(!Arrays.stream(choices).allMatch(possibleChoices::contains)) {
            throw AnswerException("Error. One of the given choices aren't contained in possible choices.")
        }
    }
}