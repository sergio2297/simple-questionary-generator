package es.sfernandez.sqg.beans.question.answers

import es.sfernandez.sqg.BasicFixtures
import es.sfernandez.sqg.beans.question.answers.AnswerFixtures.generateSomeChoices
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SingleSelectionAnswerTest {

    //---- Attributes ----
    private lateinit var answer : SingleSelectionAnswer

    //---- Fixtures ----
    private val possibleChoices = generateSomeChoices(5)
    private val choiceId = BasicFixtures.SOME_TEXT_1

    //---- Tests ----
    @Test
    fun singleSelectionAnswer_areSingleSelectionAnswerTypeTest() {
        answer = SingleSelectionAnswer(possibleChoices, choiceId)

        assertThat(answer.type).isEqualTo(AnswerTypes.SINGLE_SELECTION)
    }

    @Test
    fun construct_withNoPossibleChoices_worksTest() {
        answer = SingleSelectionAnswer(arrayOf(), choiceId)

        assertThat(answer.possibleChoices).isEmpty()
    }

    @Test
    fun construct_withPossibleChoices_worksTest() {
        answer = SingleSelectionAnswer(possibleChoices, choiceId)

        assertThat(answer.possibleChoices).containsExactly(*possibleChoices)
    }

    @Test
    fun construct_withRightChoice_worksTest() {
        answer = SingleSelectionAnswer(arrayOf(), choiceId)

        assertThat(answer.rightChoiceId).isEqualTo(choiceId)
    }

}