package es.sfernandez.sqg.model.correcting.answer.correctors.rightornot

import es.sfernandez.sqg.beans.question.answers.Choice
import es.sfernandez.sqg.beans.question.answers.correction.rightornot.RightChoiceIds
import es.sfernandez.sqg.model.correcting.answer.correctors.AnswerCorrectorTest
import es.sfernandez.sqg.model.correcting.answer.replies.SelectedChoicesReply
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class RightChoiceIdsCorrectorTest {

    //---- Attributes ----
    private val corrector = RightChoiceIdsCorrector()

    //---- Fixtures ----
    private val choices = mockSomeChoices()

    //---- Methods ----
    private fun mockSomeChoices() : Array<Choice> {
        return generateSequence(0) {it + 1}
            .take(4)
            .map(this::mockChoice)
            .toList()
            .toTypedArray()
    }

    private fun mockChoice(id: Int): Choice {
        val choice = Mockito.mock(Choice::class.java)
        Mockito.`when`(choice.id).thenReturn(id.toString())
        return choice
    }

    private fun checkCorrectFunctionWith(correctionIds: Array<Int>, replyIds: Array<Int>, expected: Boolean) {
        val correction = RightChoiceIds(*correctionIds.map(Int::toString).toTypedArray())
        val reply = SelectedChoicesReply(*replyIds.map(choices::get).toTypedArray())

        val isRight = corrector.correct(correction, reply)

        assertThat(isRight).isEqualTo(expected)
    }

    //---- Tests ----
    @Test
    fun acceptCorrection_returnsTrueId_correctionsInstanceOfRightChoiceIdsTest() {
        AnswerCorrectorTest.checkIfCorrectorAcceptCorrection(corrector, RightChoiceIds::class.java)
    }

    @Test
    fun acceptReply_returnsTrueIf_replyInstanceOfSelectedChoicesIdTest() {
        AnswerCorrectorTest.checkIfCorrectorAcceptReply(corrector, SelectedChoicesReply::class.java)
    }

    @Test
    fun correct_returnsTrueIf_correctionAndReplyHasNoChoiceIdsTest() {
        checkCorrectFunctionWith(arrayOf(), arrayOf(), true)
    }

    @Test
    fun correct_returnsTrueIf_correctionAndReplyChoiceIdAreEqualTest() {
        checkCorrectFunctionWith(arrayOf(1), arrayOf(1), true)
    }

    @Test
    fun correct_returnsFalseIf_correctionAndReplyChoiceIdAreNotEqualTest() {
        checkCorrectFunctionWith(arrayOf(1), arrayOf(2), false)
    }

    @Test
    fun correct_returnsFalseIf_atLeastOneReplyIdIsMissingFromCorrectionTest() {
        checkCorrectFunctionWith(arrayOf(1, 2, 3), arrayOf(1, 2), false)
    }

    @Test
    fun correct_returnsFalseIf_atLeastOneReplyIdIsNotInCorrectionTest() {
        checkCorrectFunctionWith(arrayOf(1, 2), arrayOf(1, 2, 3), false)
    }

    @Test
    fun correct_returnsTrueIf_correctionAndReplyChoiceIdsAreEqualTest() {
        checkCorrectFunctionWith(arrayOf(1, 2, 3), arrayOf(1, 2, 3), true)
    }

}