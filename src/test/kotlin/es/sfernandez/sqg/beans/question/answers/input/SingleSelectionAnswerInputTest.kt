package es.sfernandez.sqg.beans.question.answers.input

import es.sfernandez.sqg.beans.question.answers.Choice
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class SingleSelectionAnswerInputTest : SelectionAnswerInputTest() {

    //---- Methods ----
    override fun createInput(vararg choices: Choice): SelectionAnswerInput {
        return SingleSelectionAnswerInput(*choices)
    }

    //---- Tests ----
    @Test
    fun singleSelectionAnswerInput_areOfSingleSelectionTypeTest() {
        val input = SingleSelectionAnswerInput()

        assertThat(input.type).isEqualTo(AnswerInput.Type.SINGLE_SELECTION)
    }

}