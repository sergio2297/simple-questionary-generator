package es.sfernandez.sqg.model.correcting

import es.sfernandez.sqg.BasicFixtures
import es.sfernandez.sqg.beans.question.answers.TextAnswer
import es.sfernandez.sqg.model.correcting.replies.TextReply
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class TextAnswerCorrectingTest {

    //---- Attributes ----
    private lateinit var correcting : TextAnswerCorrecting

    //---- Fixtures ----
    private val upperCaseWordPattern = "[A-Z]+"

    //---- Methods ----
    private fun mockTextAnswer() : TextAnswer {
        return Mockito.mock(TextAnswer::class.java)
    }

    private fun mockTextAnswerWithPossibleReplies(vararg possibleReplies : String) : TextAnswer {
        val answer = mockTextAnswer()
        Mockito.`when`(answer.checkWithRegex).thenReturn(false)
        Mockito.`when`(answer.possibleReplies).thenReturn(arrayOf(*possibleReplies))
        return answer
    }

    private fun mockTextAnswerWithRegex(regex: Regex) : TextAnswer {
        val answer = mockTextAnswer()
        Mockito.`when`(answer.checkWithRegex).thenReturn(true)
        Mockito.`when`(answer.replyRegex).thenReturn(regex)
        return answer
    }

    private fun mockTextReply(text: String) : TextReply {
        val reply = Mockito.mock(TextReply::class.java)
        Mockito.`when`(reply.get()).thenReturn(text)
        return reply
    }

    //---- Tests ----
    @Test
    fun replyNotContained_byPossibleReplies_isNotRightTest() {
        val reply = mockTextReply(BasicFixtures.SOME_TEXT_1)
        val answer = mockTextAnswerWithPossibleReplies(BasicFixtures.SOME_TEXT_2, BasicFixtures.SOME_TEXT_3)
        correcting = TextAnswerCorrecting(answer)

        val isRight = correcting.isRight(reply)

        assertThat(isRight).isFalse()
    }

    @Test
    fun replyContained_byPossibleReplies_isRightTest() {
        val reply = mockTextReply(BasicFixtures.SOME_TEXT_1)
        val answer = mockTextAnswerWithPossibleReplies(BasicFixtures.SOME_TEXT_1, BasicFixtures.SOME_TEXT_2)
        correcting = TextAnswerCorrecting(answer)

        val isRight = correcting.isRight(reply)

        assertThat(isRight).isTrue()
    }

    @Test
    fun replyNotMatched_byRegex_isNotRightTest() {
        val reply = mockTextReply("abcd")
        val answer = mockTextAnswerWithRegex(Regex(upperCaseWordPattern))
        correcting = TextAnswerCorrecting(answer)

        val isRight = correcting.isRight(reply)

        assertThat(isRight).isFalse()
    }

    @Test
    fun replyMatched_byRegex_isRightTest() {
        val reply = mockTextReply("ABCD")
        val answer = mockTextAnswerWithRegex(Regex(upperCaseWordPattern))
        correcting = TextAnswerCorrecting(answer)

        val isRight = correcting.isRight(reply)

        assertThat(isRight).isTrue()
    }

    @Test
    fun sameCorrecting_canCheck_differentRepliesTest() {
        val answer = mockTextAnswerWithRegex(Regex(upperCaseWordPattern))
        correcting = TextAnswerCorrecting(answer)

        val notRightReply = mockTextReply("abcd")
        val rightReply = mockTextReply("ABCD")

        assertThat(correcting.isRight(notRightReply)).isFalse()
        assertThat(correcting.isRight(rightReply)).isTrue()
    }

}