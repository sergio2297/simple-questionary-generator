package es.sfernandez.sqg.beans.question.answers.input

import es.sfernandez.sqg.beans.question.answers.Choice

/**
 * MultipleSelectionAnswerInput is a [SelectionAnswerInput] which represents an input where the user has to mark one or
 * more choices from all the possible choices.
 */
class MultipleSelectionAnswerInput(
    vararg possibleChoices: Choice
) : SelectionAnswerInput(*possibleChoices) {

    override val type: AnswerInput.Type
        get() = AnswerInput.Type.MULTIPLE_SELECTION

}