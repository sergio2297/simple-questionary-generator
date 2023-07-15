package es.sfernandez.sqg.beans.question.answers

import es.sfernandez.sqg.beans.question.answers.AnswerFixtures.generateSomeChoices
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MultipleSelectionAnswerTest {

    //---- Attributes ----
    private lateinit var answer : MultipleSelectionAnswer

    //---- Fixtures ----
    private val possibleChoices = generateSomeChoices(5)
    private val someIds = arrayOf("1", "2", "3")

    //---- Tests ----
    @Test
    fun multipleSelectionAnswers_areMultipleSelectionAnswerTypeTest() {
        answer = MultipleSelectionAnswer(possibleChoices, someIds)

        assertThat(answer.type).isEqualTo(AnswerTypes.MULTIPLE_SELECTION)
    }

    @Test
    fun construct_withNoPossibleChoices_worksTest() {
        answer = MultipleSelectionAnswer(arrayOf(), someIds)

        assertThat(answer.possibleChoices).isEmpty()
    }

    @Test
    fun construct_withPossibleChoices_worksTest() {
        answer = MultipleSelectionAnswer(possibleChoices, someIds)

        assertThat(answer.possibleChoices).containsExactly(*possibleChoices)
    }

    @Test
    fun construct_withNoRightChoicesId_worksTest() {
        answer = MultipleSelectionAnswer(possibleChoices, arrayOf())

        assertThat(answer.rightChoicesId).isEmpty()
    }

    @Test
    fun construct_withRightChoicesId_worksTest() {
        answer = MultipleSelectionAnswer(possibleChoices, someIds)

        assertThat(answer.rightChoicesId).containsExactly(*someIds)
    }

}