package es.sfernandez.sqg.beans.question.answers

import es.sfernandez.sqg.beans.question.answers.AnswerFixtures.generateSomeChoices
import es.sfernandez.sqg.beans.question.answers.choices.Choice
import es.sfernandez.sqg.beans.question.answers.replies.SingleReply
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class SingleSelectionAnswerTest {

    //---- Attributes ----
    private lateinit var answer : SingleSelectionAnswer

    //---- Fixtures ----
    private val possibleChoices = generateSomeChoices(5)

    //---- Tests ----
    @Test
    fun singleSelectionAnswer_areSingleSelectionAnswerTypeTest() {
        answer = SingleSelectionAnswer(possibleChoices, possibleChoices[0])

        assertThat(answer.type).isEqualTo(AnswerTypes.SINGLE_SELECTION)
    }

    @Test
    fun constructWith_noPossibleChoices_throwsAnswerExceptionTest() {
        assertThrows<AnswerException> { SingleSelectionAnswer(arrayOf(), Choice()) }
    }

    @Test
    fun constructWith_rightAnswerNotContainedByPossibleChoices_throwsAnswerExceptionTest() {
        assertThrows<AnswerException> { SingleSelectionAnswer(possibleChoices, Choice()) }
    }

    @Test
    fun replyWithRightChoice_isRightTest() {
        val rightChoice = possibleChoices[0]
        answer = SingleSelectionAnswer(possibleChoices, rightChoice)

        val isRight = answer.isRight(SingleReply(rightChoice))

        assertThat(isRight).isTrue()
    }

    @Test
    fun replyWithNoRightChoice_isNotRightTest() {
        val rightChoice = possibleChoices[0]
        val notRightChoice = possibleChoices[1]
        answer = SingleSelectionAnswer(possibleChoices, rightChoice)

        val isRight = answer.isRight(SingleReply(notRightChoice))

        assertThat(isRight).isFalse()
    }

}