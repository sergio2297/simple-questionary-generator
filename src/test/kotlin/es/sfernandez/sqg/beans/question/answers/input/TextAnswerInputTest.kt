package es.sfernandez.sqg.beans.question.answers.input

import org.assertj.core.api.Assertions
import kotlin.test.Test

class TextAnswerInputTest {

    //--- Test ----
    @Test
    fun textAnswerInput_areOfTextTypeTest() {
        val input = TextAnswerInput()

        Assertions.assertThat(input.type).isEqualTo(AnswerInput.Type.TEXT)
    }

}