package es.sfernandez.sqg.beans.question.answers

import es.sfernandez.sqg.beans.question.answers.correction.AnswerCorrection
import es.sfernandez.sqg.beans.question.answers.correction.UnspecifiedAnswerCorrection
import es.sfernandez.sqg.beans.question.answers.input.AnswerInput
import es.sfernandez.sqg.beans.question.answers.input.UnspecifiedAnswerInput
import org.assertj.core.api.Assertions.assertThat
import org.mockito.Mockito
import kotlin.test.BeforeTest
import kotlin.test.Test

class AnswerTest {

    //---- Attributes ----
    private lateinit var answer: Answer

    //---- Configuration ----
    @BeforeTest
    fun setup() {
        answer = Answer()
    }

    //---- Methods ----
    private fun mockAnswerInput(): AnswerInput {
        return Mockito.mock(AnswerInput::class.java)
    }

    private fun mockAnswerCorrection(): AnswerCorrection {
        return Mockito.mock(AnswerCorrection::class.java)
    }

    //---- Tests ----
    @Test
    fun defaultAnswer_hasUnspecifiedAnswerInputTest() {
        val answer = Answer()

        assertThat(answer.input).isInstanceOf(UnspecifiedAnswerInput::class.java)
    }

    @Test
    fun setAnswerInput_worksProperlyTest() {
        val input = mockAnswerInput()

        answer.input = input

        assertThat(answer.input).isEqualTo(input)
    }

    @Test
    fun defaultAnswer_hasUnspecifiedAnswerCorrectionTest() {
        val answer = Answer()

        assertThat(answer.correction).isInstanceOf(UnspecifiedAnswerCorrection::class.java)
    }

    @Test
    fun setAnswerCorrection_worksProperlyTest() {
        val correction = mockAnswerCorrection()

        answer.correction = correction

        assertThat(answer.correction).isEqualTo(correction)
    }

}