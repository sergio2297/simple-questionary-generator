package es.sfernandez.sqg.model.correcting.answer.seekers

import es.sfernandez.sqg.beans.question.answers.correction.AnswerCorrection
import es.sfernandez.sqg.beans.question.answers.correction.rightornot.CorrectValuesRegex
import es.sfernandez.sqg.beans.question.answers.correction.rightornot.PossibleValues
import es.sfernandez.sqg.beans.question.answers.correction.rightornot.RightChoiceIds
import es.sfernandez.sqg.model.correcting.answer.AnswerCorrectingException
import es.sfernandez.sqg.model.correcting.answer.correctors.AnswerCorrector
import es.sfernandez.sqg.model.correcting.answer.correctors.rightornot.PossibleValuesCorrector
import es.sfernandez.sqg.model.correcting.answer.correctors.rightornot.RightChoiceIdsCorrector
import es.sfernandez.sqg.model.correcting.answer.correctors.rightornot.ValuesRegexCorrector
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import kotlin.test.DefaultAsserter.fail

class RightOrNotCorrectorSeekerTest {

    //---- Attributes ----
    private val correctorSeeker = RightOrNotCorrectorSeeker()

    //---- Methods ----
    private fun <CORRECTION: AnswerCorrection, CORRECTOR: AnswerCorrector<*>>
            checkCorrectorForIs(correctionType: Class<CORRECTION>, correctorType: Class<CORRECTOR>) {
        val answer = Mockito.mock(es.sfernandez.sqg.beans.question.answers.Answer::class.java)
        Mockito.`when`(answer.correction).thenReturn(Mockito.mock(correctionType))

        val corrector = correctorSeeker.correctorFor(answer)

        assertThat(corrector).isInstanceOf(correctorType)
    }

    //---- Tests ----
    @Test
    fun correctorFor_correctValuesRegexCorrectionIs_valuesRegexCorrectorTest() {
        checkCorrectorForIs(CorrectValuesRegex::class.java, ValuesRegexCorrector::class.java)
    }

    @Test
    fun correctorFor_possibleValuesCorrectionIs_possibleValuesCorrectorTest() {
        checkCorrectorForIs(PossibleValues::class.java, PossibleValuesCorrector::class.java)
    }

    @Test
    fun correctorFor_rightChoiceIdsCorrectionIs_rightChoiceIdsCorrectorTest() {
        checkCorrectorForIs(RightChoiceIds::class.java, RightChoiceIdsCorrector::class.java)
    }

    @Test
    fun correctorFor_anyOtherCorrection_throwsExceptionTest() {
        assertThrows<AnswerCorrectingException> { checkCorrectorForIs(AnswerCorrection::class.java, AnswerCorrector::class.java) }
    }

    @Test
    fun exceptionThrownBy_correctForWhenCorrectorIsNotFound_containsSeekersNameTest() {
        try {
            checkCorrectorForIs(AnswerCorrection::class.java, AnswerCorrector::class.java)
            fail("Error. An exception should be thrown")
        } catch (ex: AnswerCorrectingException) {
            assertThat(ex.message).contains(correctorSeeker.javaClass.canonicalName)
        }
    }

}