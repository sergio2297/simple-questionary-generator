package es.sfernandez.sqg.beans.question.answers

import es.sfernandez.sqg.beans.question.answers.choices.Choice

/**
 * SingleSelectionAnswer is a type of [SelectionAnswer] which only one right [Choice]
 *
 * @constructor Creates a new SingleSelectionAnswer with the specified possibleChoices and rightChoice.
 *
 */
class SingleSelectionAnswer(
    possibleChoices: Array<Choice>,
    var rightChoiceId: String
) : SelectionAnswer(AnswerTypes.SINGLE_SELECTION, possibleChoices)