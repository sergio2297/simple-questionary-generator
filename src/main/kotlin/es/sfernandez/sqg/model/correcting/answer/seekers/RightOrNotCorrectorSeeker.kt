package es.sfernandez.sqg.model.correcting.answer.seekers

import es.sfernandez.sqg.beans.question.answers.Answer
import es.sfernandez.sqg.beans.question.answers.correction.AnswerCorrection
import es.sfernandez.sqg.beans.question.answers.correction.rightornot.CorrectValuesRegex
import es.sfernandez.sqg.beans.question.answers.correction.rightornot.PossibleValues
import es.sfernandez.sqg.beans.question.answers.correction.rightornot.RightChoiceIds
import es.sfernandez.sqg.model.correcting.answer.AnswerCorrectingException
import es.sfernandez.sqg.model.correcting.answer.correctors.AnswerCorrector
import es.sfernandez.sqg.model.correcting.answer.correctors.rightornot.PossibleValuesCorrector
import es.sfernandez.sqg.model.correcting.answer.correctors.rightornot.RightChoiceIdsCorrector
import es.sfernandez.sqg.model.correcting.answer.correctors.rightornot.RightOrNotAnswerCorrector
import es.sfernandez.sqg.model.correcting.answer.correctors.rightornot.ValuesRegexCorrector

/**
 * RightOrNotCorrectorSeeker is an [AnswerCorrectorSeeker] that search for AnswerCorrector that
 * implements [RightOrNotAnswerCorrector]
 */
class RightOrNotCorrectorSeeker : AnswerCorrectorSeeker<Boolean> {

    //---- Attributes ----
    private val valuesRegexCorrector = ValuesRegexCorrector()
    private val possibleValuesCorrector = PossibleValuesCorrector()
    private val rightChoiceIdsCorrector = RightChoiceIdsCorrector()

    //---- Methods ----
    /**
     * Seek the apropiate AnswerCorrector for the given answer based on its [AnswerCorrection]. It will return an
     * implementation of [RightOrNotAnswerCorrector]
     *
     * @param answer Answer used to search the AnswerCorrector
     * @return the apropiate AnswerCorrector for the given answer
     * @throws AnswerCorrectingException iff there is no apropiate AnswerCorrector for the given answer
     */
    override fun correctorFor(answer: Answer): AnswerCorrector<Boolean> {
        if(answer.correction is CorrectValuesRegex)
            return valuesRegexCorrector

        if(answer.correction is PossibleValues)
            return possibleValuesCorrector

        if(answer.correction is RightChoiceIds)
            return rightChoiceIdsCorrector

        throw AnswerCorrectingException("Error. There isn't any AnswerCorrector for the given answer in " +
                this.javaClass.canonicalName
        )
    }

}