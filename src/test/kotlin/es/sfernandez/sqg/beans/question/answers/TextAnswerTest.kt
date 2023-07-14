package es.sfernandez.sqg.beans.question.answers

import es.sfernandez.sqg.BasicFixtures
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TextAnswerTest {

    //---- Attributes ----
    private lateinit var answer : TextAnswer

    //---- Fixtures ----
    private val aRegex = Regex("[A-Z]+")

    //---- Tests ----
    @Test
    fun textAnswer_areTextInputAnswerTypeTest() {
        answer = TextAnswer()

        assertThat(answer.type).isEqualTo(AnswerTypes.TEXT_INPUT)
    }

    @Test
    fun construct_withNoPossibleReplies_worksTest() {
        answer = TextAnswer()

        assertThat(answer.possibleReplies).isEmpty()
    }

    @Test
    fun construct_withSomePossibleReplies_worksTest() {
        val somePossibleReplies = arrayOf(
            BasicFixtures.SOME_TEXT_1,
            BasicFixtures.SOME_TEXT_2,
            BasicFixtures.SOME_TEXT_3
        )

        answer = TextAnswer(*somePossibleReplies)

        assertThat(answer.possibleReplies).contains(*somePossibleReplies)
    }

    @Test
    fun construct_withPossibleReplies_createsRegexWithEmptyPatternTest() {
        answer = TextAnswer()

        assertThat(answer.replyRegex.pattern).isEmpty()
    }

    @Test
    fun construct_withPossibleReplies_doNotCheckWithRegexTest() {
        answer = TextAnswer()

        assertThat(answer.checkWithRegex).isFalse()
    }

    @Test
    fun construct_withRegex_worksTest() {
        answer = TextAnswer(aRegex)

        assertThat(answer.replyRegex).isSameAs(aRegex)
    }

    @Test
    fun construct_withRegex_createsEmptyPossibleRepliesTest() {
        answer = TextAnswer(aRegex)

        assertThat(answer.possibleReplies).isEmpty()
    }

    @Test
    fun construct_withRegex_doCheckWithRegexTest() {
        answer = TextAnswer(aRegex)

        assertThat(answer.checkWithRegex).isTrue()
    }

}