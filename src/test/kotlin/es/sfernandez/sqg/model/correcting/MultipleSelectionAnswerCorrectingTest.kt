package es.sfernandez.sqg.model.correcting

import es.sfernandez.sqg.BasicFixtures
import es.sfernandez.sqg.beans.question.answers.MultipleSelectionAnswer
import es.sfernandez.sqg.beans.question.answers.choices.Choice
import es.sfernandez.sqg.model.correcting.replies.MultipleReply
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class MultipleSelectionAnswerCorrectingTest {

    //---- Attributes ----
    private lateinit var correcting: MultipleSelectionAnswerCorrecting

    //---- Fixtures ----
    private val someIds = arrayOf("1", "2", "3", "4")

    //---- Methods ----
    private fun mockMultipleSelectionAnswer(vararg rightChoicesIds: String) : MultipleSelectionAnswer {
        val answer = Mockito.mock(MultipleSelectionAnswer::class.java)
        Mockito.`when`(answer.rightChoicesIds).thenReturn(arrayOf(*rightChoicesIds))
        return answer
    }

    private fun mockChoice(choiceId: String): Choice {
        val choice = Mockito.mock(Choice::class.java)
        Mockito.`when`(choice.id).thenReturn(choiceId)
        return choice
    }

    private fun mockMultipleReply(vararg choicesIds: String) : MultipleReply {
        val reply = Mockito.mock(MultipleReply::class.java)
        val choices = choicesIds.asSequence()
            .map(this::mockChoice)
            .toList().toTypedArray()
        Mockito.`when`(reply.get()).thenReturn(choices)
        return reply
    }

    //---- Test ----
    @Test
    fun reply_withNotAllRightChoicesIds_isNotRightTest() {
        val reply = mockMultipleReply(*someIds)
        val answer = mockMultipleSelectionAnswer(*someIds, "8")
        correcting = MultipleSelectionAnswerCorrecting(answer)

        val isRight = correcting.isRight(reply)

        assertThat(isRight).isFalse()
    }

    @Test
    fun reply_withAllRightChoicesIds_isRightTest() {
        val reply = mockMultipleReply(*someIds)
        val answer = mockMultipleSelectionAnswer(*someIds)
        correcting = MultipleSelectionAnswerCorrecting(answer)

        val isRight = correcting.isRight(reply)

        assertThat(isRight).isTrue()
    }

    @Test
    fun reply_withMoreThanAllRightChoicesIds_isNotRightTest() {
        val reply = mockMultipleReply(*someIds, "8")
        val answer = mockMultipleSelectionAnswer(*someIds)
        correcting = MultipleSelectionAnswerCorrecting(answer)

        val isRight = correcting.isRight(reply)

        assertThat(isRight).isFalse()
    }

    @Test
    fun sameCorrecting_canCheck_differentRepliesTest() {
        val answer = mockMultipleSelectionAnswer(BasicFixtures.SOME_TEXT_1)
        correcting = MultipleSelectionAnswerCorrecting(answer)

        val notRightReply = mockMultipleReply()
        val anotherNotRightReply = mockMultipleReply(BasicFixtures.SOME_TEXT_2)
        val rightReply = mockMultipleReply(BasicFixtures.SOME_TEXT_1)

        assertThat(correcting.isRight(notRightReply)).isFalse()
        assertThat(correcting.isRight(anotherNotRightReply)).isFalse()
        assertThat(correcting.isRight(rightReply)).isTrue()
    }

}