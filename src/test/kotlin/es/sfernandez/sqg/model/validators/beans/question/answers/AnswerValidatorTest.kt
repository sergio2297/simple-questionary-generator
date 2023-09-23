package es.sfernandez.sqg.model.validators.beans.question.answers

import es.sfernandez.sqg.beans.question.answers.Answer
import es.sfernandez.sqg.beans.question.answers.correction.AnswerCorrection
import es.sfernandez.sqg.beans.question.answers.correction.UnspecifiedAnswerCorrection
import es.sfernandez.sqg.beans.question.answers.input.AnswerInput
import es.sfernandez.sqg.beans.question.answers.input.UnspecifiedAnswerInput
import es.sfernandez.sqg.model.validators.ValidatorTest
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class AnswerValidatorTest : ValidatorTest<Answer> {

    //---- Attributes ----
    override val validator = AnswerValidator()

    //---- Methods ----
    private fun anAnswer() : Answer {
        val answer = Answer()

        answer.input = Mockito.mock(AnswerInput::class.java)
        Mockito.`when`(answer.input.type).thenReturn(AnswerInput.Type.TEXT)

        answer.correction = Mockito.mock(AnswerCorrection::class.java)
        Mockito.`when`(answer.correction.type).thenReturn(AnswerCorrection.Type.RIGHT_OR_NOT)

        return answer
    }

    private fun anAnswerWithUnspecifiedInput() : Answer {
        val answer = anAnswer()
        answer.input = UnspecifiedAnswerInput()
        return answer
    }

    private fun anAnswerWithUnspecifiedCorrection() : Answer {
        val answer = anAnswer()
        answer.correction = UnspecifiedAnswerCorrection()
        return answer
    }

    //---- Tests ----
    @Test
    fun validate_answerWithUnspecifiedInput_returnsErrorTest() {
        assertIsErrorValidate(anAnswerWithUnspecifiedInput())
    }

    @Test
    fun validate_answerWithUnspecifiedCorrection_returnsErrorTest() {
        assertIsErrorValidate(anAnswerWithUnspecifiedCorrection())
    }

    @Test
    fun validate_anAnswer_returnsOkTest() {
        assertIsOkValidate(anAnswer())
    }

}