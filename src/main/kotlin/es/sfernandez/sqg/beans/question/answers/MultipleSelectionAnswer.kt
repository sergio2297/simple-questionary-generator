package es.sfernandez.sqg.beans.question.answers

import es.sfernandez.sqg.beans.question.answers.choices.Choice

/**
 * MultipleSelectionAnswer is a type of [SelectionAnswer] which multiple right [Choice]
 *
 * @constructor Creates a new MultipleSelectionAnswer with the specified possibleChoices and rightChoices.
 *
 */
class MultipleSelectionAnswer(
    possibleChoices : Array<Choice>,
    var rightChoicesIds : Array<String>,
) : SelectionAnswer(AnswerTypes.MULTIPLE_SELECTION, possibleChoices) {}