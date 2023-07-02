package es.sfernandez.sqg.model.question.answers

import es.sfernandez.sqg.model.question.answers.AnswerFixtures.generateSomeChoices
import es.sfernandez.sqg.model.question.answers.choices.Choice
import es.sfernandez.sqg.model.question.answers.replies.MultipleReply
import es.sfernandez.sqg.model.question.answers.AnswerException
import es.sfernandez.sqg.model.question.answers.AnswerTypes
import es.sfernandez.sqg.model.question.answers.MultipleSelectionAnswer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MultipleSelectionAnswerTest {

    //---- Constants and Definitions ----
    private class FooChoice : Choice

    //---- Attributes ----
    private lateinit var answer : MultipleSelectionAnswer

    //---- Fixtures ----
    private val possibleChoices = generateSomeChoices(5)

    //---- Tests ----
    @Test
    fun multipleSelectionAnswers_areMultipleSelectionAnswerTypeTest() {
        answer = MultipleSelectionAnswer(possibleChoices, possibleChoices)

        assertThat(answer.type).isEqualTo(AnswerTypes.MULTIPLE_SELECTION)
    }

    @Test
    fun constructWith_noPossibleChoices_throwsAnswerExceptionTest() {
        assertThrows<AnswerException> { MultipleSelectionAnswer(arrayOf(), possibleChoices) }
    }

    @Test
    fun constructWith_rightAnswerNotContainedByPossibleChoices_throwsAnswerExceptionTest() {
        assertThrows<AnswerException> { MultipleSelectionAnswer(possibleChoices, arrayOf(FooChoice())) }
    }

    @Test
    fun replyWithNoRightChoices_isNotRightTest() {
        val rightChoices = arrayOf(*possibleChoices)
        answer = MultipleSelectionAnswer(possibleChoices, rightChoices)
        val reply = MultipleReply(arrayOf())

        val isRight = answer.isRight(reply)

        assertThat(isRight).isFalse()
    }

    @Test
    fun replyWithSomeRightChoices_isNotRightTest() {
        val rightChoices = arrayOf(*possibleChoices)
        answer = MultipleSelectionAnswer(possibleChoices, rightChoices)
        val reply = MultipleReply(arrayOf(rightChoices[0]))

        val isRight = answer.isRight(reply)

        assertThat(isRight).isFalse()
    }

    @Test
    fun replyWithAllRightChoices_isRightTest() {
        val rightChoices = arrayOf(*possibleChoices)
        answer = MultipleSelectionAnswer(possibleChoices, rightChoices)
        val reply = MultipleReply(arrayOf(*rightChoices))

        val isRight = answer.isRight(reply)

        assertThat(isRight).isTrue()
    }

}