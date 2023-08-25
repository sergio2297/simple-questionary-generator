package es.sfernandez.sqg.model.correcting.questionnaire

import es.sfernandez.sqg.beans.question.Question
import es.sfernandez.sqg.model.correcting.replies.Reply

/**
 * A QuestionResult represent the result of correcting a [Question] for some [Reply]
 */
interface QuestionResult {

    /** The Question that has been correcting */
    val question: Question

    /** The reply given to the question */
    val reply: Reply<*>

}