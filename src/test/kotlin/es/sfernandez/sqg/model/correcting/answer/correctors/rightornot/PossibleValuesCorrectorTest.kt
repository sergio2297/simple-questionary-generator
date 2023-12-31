package es.sfernandez.sqg.model.correcting.answer.correctors.rightornot

import es.sfernandez.sqg.beans.question.answers.correction.rightornot.PossibleValues
import es.sfernandez.sqg.model.correcting.answer.correctors.AnswerCorrectorTest
import es.sfernandez.sqg.model.correcting.replies.TextReply
import es.sfernandez.sqg.utilities.Fixtures
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PossibleValuesCorrectorTest {

    //---- Attributes ----
    private val corrector = PossibleValuesCorrector()

    //---- Methods ----
    private fun checkCorrectFunctionWith(possibleValues: Array<String>, replyText: String, expected: Boolean) {
        val correction = PossibleValues(*possibleValues)
        val reply = TextReply(replyText)

        val isRight = corrector.correct(correction, reply)

        assertThat(isRight).isEqualTo(expected)
    }

    //---- Tests ----
    @Test
    fun acceptCorrection_returnsTrueId_correctionsInstanceOfPossibleValuesTest() {
        AnswerCorrectorTest.checkIfCorrectorAcceptCorrection(corrector, PossibleValues::class.java)
    }

    @Test
    fun acceptReply_returnsTrueIf_replyInstanceOfTextReplyTest() {
        AnswerCorrectorTest.checkIfCorrectorAcceptReply(corrector, TextReply::class.java)
    }

    @Test
    fun correct_returnsTrueIf_replyTextIsInCorrectionPossibleValuesTest() {
        checkCorrectFunctionWith(
            arrayOf(Fixtures.SOME_TEXT_1, Fixtures.SOME_TEXT_2),
            Fixtures.SOME_TEXT_2,
            true)
    }

    @Test
    fun correct_returnsFalseIf_replyTextIsNotInCorrectionPossibleValuesTest() {
        checkCorrectFunctionWith(
            arrayOf(Fixtures.SOME_TEXT_1, Fixtures.SOME_TEXT_2),
            Fixtures.SOME_TEXT_3,
            false)
    }

    @Test
    fun correct_returnsFalseIf_correctionPossibleValuesIsEmptyTest() {
        checkCorrectFunctionWith(
            arrayOf(),
            Fixtures.EMPTY_TEXT,
            false
        )
    }

}