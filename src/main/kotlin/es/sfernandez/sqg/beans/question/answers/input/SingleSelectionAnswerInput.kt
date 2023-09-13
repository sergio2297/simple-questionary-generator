package es.sfernandez.sqg.beans.question.answers.input

import es.sfernandez.sqg.beans.question.answers.Choice

/**
 * SingleSelectionAnswerInput is a [SelectionAnswerInput] which represents an input where the user has to mark only one
 * choice from all the possible choices
 */
class SingleSelectionAnswerInput(
    vararg possibleChoices: Choice
) : SelectionAnswerInput(*possibleChoices) {

    override val type: AnswerInput.Type
        get() = AnswerInput.Type.SINGLE_SELECTION

}