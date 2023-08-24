package es.sfernandez.sqg.model.correcting.answer.seekers

import es.sfernandez.sqg.beans.question.answers.Answer
import es.sfernandez.sqg.beans.question.answers.correction.AnswerCorrection
import es.sfernandez.sqg.model.correcting.answer.correctors.AnswerCorrector

/**
 * An AnswerCorrectorSeeker provides one method that for a given [Answer] return its apropiate [AnswerCorrector]
 *
 * @param RESULT type of result of the corrector searched
 */
interface AnswerCorrectorSeeker<out RESULT> {

    /**
     * Seek the apropiate AnswerCorrector for the given answer based on its [AnswerCorrection]
     *
     * It may throw an [AnswerCorrectorException]
     *
     * @param answer Answer used to search the AnswerCorrector
     * @return the apropiate AnswerCorrector for the given answer
     */
    fun correctorFor(answer: Answer) : AnswerCorrector<RESULT

}