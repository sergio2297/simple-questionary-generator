package es.sfernandez.sqg.model.question.answers

import es.sfernandez.sqg.BasicFixtures
import es.sfernandez.sqg.model.question.answers.replies.TextReply
import es.sfernandez.sqg.model.question.answers.AnswerException
import es.sfernandez.sqg.model.question.answers.AnswerTypes
import es.sfernandez.sqg.model.question.answers.TextAnswer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class TextAnswerTest {

    //---- Attributes ----
    private lateinit var answer : TextAnswer

    //---- Fixtures ----
    private val upperCaseWordPattern = "[A-Z]+"

    //---- Tests ----
    @Test
    fun textAnswer_areTextInputAnswerTypeTest() {
        answer = TextAnswer(upperCaseWordPattern)

        assertThat(answer.type).isEqualTo(AnswerTypes.TEXT_INPUT)
    }

    @Test
    fun construct_withNoPossibleReplies_throwsAnswerExceptionTest() {
        assertThrows<AnswerException> { TextAnswer() }
    }

    @Test
    fun replyNotContained_byPossibleReplies_isNotRightTest() {
        val reply = BasicFixtures.SOME_TEXT_1
        answer = TextAnswer(BasicFixtures.SOME_TEXT_2, BasicFixtures.SOME_TEXT_3)

        val isRight = answer.isRight(TextReply(reply))

        assertThat(isRight).isFalse()
    }

    @Test
    fun replyContained_byPossibleReplies_isRightTest() {
        val reply = BasicFixtures.SOME_TEXT_1
        answer = TextAnswer(reply, BasicFixtures.SOME_TEXT_2, BasicFixtures.SOME_TEXT_3)

        val isRight = answer.isRight(TextReply(reply))

        assertThat(isRight).isTrue()
    }

    @Test
    fun construct_withEmptyRegex_throwsAnswerExceptionTest() {
        val regex = Regex(BasicFixtures.EMPTY_TEXT)

        assertThrows<AnswerException> { TextAnswer(regex) }
    }

    @Test
    fun replyNotMatched_byRegex_isNotRightTest() {
        val reply = "abcd"
        answer = TextAnswer(Regex(upperCaseWordPattern))

        val isRight = answer.isRight(TextReply(reply))

        assertThat(isRight).isFalse()
    }

    @Test
    fun replyMatched_byRegex_isRightTest() {
        val reply = "ABCD"
        answer = TextAnswer(Regex(upperCaseWordPattern))

        val isRight = answer.isRight(TextReply(reply))

        assertThat(isRight).isTrue()
    }
}