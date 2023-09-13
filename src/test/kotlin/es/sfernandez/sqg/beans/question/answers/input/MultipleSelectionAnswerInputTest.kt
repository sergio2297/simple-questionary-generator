package es.sfernandez.sqg.beans.question.answers.input

import es.sfernandez.sqg.beans.question.answers.Choice
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class MultipleSelectionAnswerInputTest : SelectionAnswerInputTest() {

    //--- Methods ----
    override fun createInput(vararg choices: Choice): SelectionAnswerInput {
        return MultipleSelectionAnswerInput(*choices)
    }

    //---- Tests ----
    @Test
    fun multipleSelectionAnswerInput_areOfSingleSelectionTypeTest() {
        val input = MultipleSelectionAnswerInput()

        assertThat(input.type).isEqualTo(AnswerInput.Type.MULTIPLE_SELECTION)
    }

}