package es.sfernandez.sqg.beans.question.answers.input

import es.sfernandez.sqg.beans.question.answers.Choice

/**
 * SelectionAnswerInput is an [AnswerInput] which represents an input where the user has to mark one or more
 * possible choices.
 *
 * @param possibleChoices Possible choices to select
 * @see SingleSelectionAnswerInput
 * @see MultipleSelectionAnswerInput
 */
sealed class SelectionAnswerInput(
    vararg possibleChoices: Choice
) : AnswerInput {

    //---- Attributes ----
    var possibleChoices: Array<Choice>

    //---- Constructor ----
    init {
        this.possibleChoices = arrayOf(*possibleChoices)
    }

}