package es.sfernandez.sqg.beans.question.answers.input

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class UnspecifiedAnswerInputTest {

    //---- Tests ----
    @Test
    fun unspecifiedAnswerInput_areOfUnspecifiedTypeTest() {
        val input = UnspecifiedAnswerInput()

        assertThat(input.type).isEqualTo(AnswerInput.Type.UNSPECIFIED)
    }

}