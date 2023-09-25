package es.sfernandez.sqg.model.validators.beans.question.answers

import es.sfernandez.sqg.beans.question.answers.Answer
import es.sfernandez.sqg.model.validators.ValidatorTest
import es.sfernandez.sqg.utilities.fixtures.beans.question.answers.UsesAnswerFixtures
import org.junit.jupiter.api.Test

class AnswerValidatorTest : ValidatorTest<Answer>, UsesAnswerFixtures {

    //---- Attributes ----
    override val validator = AnswerValidator()

    //---- Tests ----
    @Test
    fun validate_answerWithUnspecifiedInput_returnsErrorTest() {
        assertIsErrorValidate(anAnswerWith().unspecifiedInput().build())
    }

    @Test
    fun validate_answerWithUnspecifiedCorrection_returnsErrorTest() {
        assertIsErrorValidate(anAnswerWith().unspecifiedCorrection().build())
    }

    @Test
    fun validate_anAnswer_returnsOkTest() {
        assertIsOkValidate(anAnswer())
    }

}