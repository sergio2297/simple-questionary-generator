package es.sfernandez.sqg.model.correcting.answer.correctors.rightornot

import es.sfernandez.sqg.beans.question.answers.correction.rightornot.CorrectValuesRegex
import es.sfernandez.sqg.model.correcting.answer.correctors.AnswerCorrectorTest
import es.sfernandez.sqg.model.correcting.replies.TextReply
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ValuesRegexCorrectorTest {

    //---- Attributes ----
    private val corrector = ValuesRegexCorrector()

    //---- Fixtures ----
    private val upperCaseWordPattern = "[A-Z]+"

    //---- Methods ----
    private fun checkCorrectFunctionWith(regex: String, replyText: String, expected: Boolean) {
        val correction = CorrectValuesRegex(Regex(regex))
        val reply = TextReply(replyText)

        val isRight = corrector.correct(correction, reply)

        assertThat(isRight).isEqualTo(expected)
    }

    //---- Tests ----
    @Test
    fun acceptCorrection_returnsTrueId_correctionsInstanceOfCorrectValuesRegexTest() {
        AnswerCorrectorTest.checkIfCorrectorAcceptCorrection(corrector, CorrectValuesRegex::class.java)
    }

    @Test
    fun acceptReply_returnsTrueIf_replyInstanceOfTextReplyTest() {
        AnswerCorrectorTest.checkIfCorrectorAcceptReply(corrector, TextReply::class.java)
    }

    @Test
    fun correct_returnsTrueIf_correctionRegexMatchesReplyTextTest() {
        checkCorrectFunctionWith(
            upperCaseWordPattern,
            "ABCD",
            true)
    }

    @Test
    fun correct_returnsFalseIf_correctionRegexDoesNotMatchReplyTextTest() {
        checkCorrectFunctionWith(
            upperCaseWordPattern,
            "abcd",
            false)
    }

}