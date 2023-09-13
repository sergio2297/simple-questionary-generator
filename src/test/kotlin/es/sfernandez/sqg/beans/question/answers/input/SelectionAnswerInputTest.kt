package es.sfernandez.sqg.beans.question.answers.input

import es.sfernandez.sqg.beans.question.answers.Choice
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

abstract class SelectionAnswerInputTest {

    //---- Fixtures ----
    private val choices = generateSequence { Choice() }.take(5).toList().toTypedArray()

    //---- Methods ----
    abstract fun createInput(vararg choices: Choice): SelectionAnswerInput

    //---- Tests ----
    @Test
    fun createSelectionAnswerInput_withoutPossibleChoices_worksTest() {
        val input = createInput()

        assertThat(input.possibleChoices).isEmpty()
    }

    @Test
    fun createSelectionAnswerInput_withOnePossibleChoice_worksTest() {
        val aChoice = choices[0]

        val input = createInput(aChoice)

        assertThat(input.possibleChoices.size).isEqualTo(1)
        assertThat(input.possibleChoices[0]).isSameAs(aChoice)
    }

    @Test
    fun createSelectionAnswerInput_withMoreThanOnePossibleChoices_worksTest() {
        val input = createInput(*choices)

        assertThat(input.possibleChoices).isEqualTo(choices)
    }

}