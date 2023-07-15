package es.sfernandez.sqg.model.correcting

import es.sfernandez.sqg.BasicFixtures
import es.sfernandez.sqg.beans.question.answers.SingleSelectionAnswer
import es.sfernandez.sqg.beans.question.answers.choices.Choice
import es.sfernandez.sqg.model.correcting.replies.SingleReply
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class SingleSelectionAnswerCorrectingTest {

    //---- Attributes ----
    private lateinit var correcting: SingleSelectionAnswerCorrecting

    //---- Methods ----
    private fun mockSingleSelectionAnswer(rightChoiceId: String) : SingleSelectionAnswer {
        val answer = Mockito.mock(SingleSelectionAnswer::class.java)
        Mockito.`when`(answer.rightChoiceId).thenReturn(rightChoiceId)
        return answer
    }

    private fun mockSingleReply(choiceId: String) : SingleReply {
        val reply = Mockito.mock(SingleReply::class.java)
        val choice = Mockito.mock(Choice::class.java)
        Mockito.`when`(choice.id).thenReturn(choiceId)
        Mockito.`when`(reply.get()).thenReturn(choice)
        return reply
    }

    //---- Tests ----
    @Test
    fun replyWithRightChoice_isRightTest() {
        val reply = mockSingleReply(BasicFixtures.SOME_TEXT_1)
        val answer = mockSingleSelectionAnswer(BasicFixtures.SOME_TEXT_1)
        correcting = SingleSelectionAnswerCorrecting(answer)

        val isRight = correcting.isRight(reply)

        assertThat(isRight).isTrue()
    }

    @Test
    fun replyWithNotRightChoice_isNotRightTest() {
        val reply = mockSingleReply(BasicFixtures.SOME_TEXT_2)
        val answer = mockSingleSelectionAnswer(BasicFixtures.SOME_TEXT_1)
        correcting = SingleSelectionAnswerCorrecting(answer)

        val isRight = correcting.isRight(reply)

        assertThat(isRight).isFalse()
    }

    @Test
    fun sameCorrecting_canCheck_differentRepliesTest() {
        val answer = mockSingleSelectionAnswer(BasicFixtures.SOME_TEXT_1)
        correcting = SingleSelectionAnswerCorrecting(answer)

        val notRightReply = mockSingleReply(BasicFixtures.SOME_TEXT_2)
        val rightReply = mockSingleReply(BasicFixtures.SOME_TEXT_1)

        assertThat(correcting.isRight(notRightReply)).isFalse()
        assertThat(correcting.isRight(rightReply)).isTrue()
    }

}