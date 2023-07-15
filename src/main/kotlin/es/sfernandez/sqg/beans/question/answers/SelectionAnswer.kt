package es.sfernandez.sqg.beans.question.answers

import es.sfernandez.sqg.beans.question.answers.choices.Choice
import es.sfernandez.sqg.model.correcting.replies.Reply
import java.util.*

/**
 * SelectionAnswer is a type of [Answer] that represents answers which all possible [Choice] are displayed
 * to the user, and he has to select one or more of them
 *
 * @see SingleSelectionAnswer
 * @see MultipleSelectionAnswer
 */
sealed class SelectionAnswer(
    override val type: AnswerTypes,
    var possibleChoices: Array<Choice>
) : Answer