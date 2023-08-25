package es.sfernandez.sqg.model.correcting.questionnaire.numofrights

import es.sfernandez.sqg.beans.question.Question
import es.sfernandez.sqg.model.correcting.questionnaire.QuestionResult
import es.sfernandez.sqg.model.correcting.replies.Reply

/**
 * A RightOrNotQuestionResult is a [QuestionResult] that represents the result of correcting a [Question] for
 * some [Reply] using boolean values
 *
 * @property isRight true if the result of correct the question is right
 */
class RightOrNotQuestionResult(
    override val question: Question,
    override val reply: Reply<*>,
    val isRight: Boolean,
) : QuestionResult