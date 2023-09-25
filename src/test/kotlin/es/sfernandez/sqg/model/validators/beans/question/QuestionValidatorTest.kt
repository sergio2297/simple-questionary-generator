package es.sfernandez.sqg.model.validators.beans.question

import es.sfernandez.sqg.beans.question.Question
import es.sfernandez.sqg.model.validators.ValidatorTest
import es.sfernandez.sqg.utilities.fixtures.beans.question.UsesQuestionFixtures
import org.junit.jupiter.api.Test

class QuestionValidatorTest : ValidatorTest<Question>, UsesQuestionFixtures {

    //---- Attributes ----
    override val validator = QuestionValidator()

    //---- Tests ----
    @Test
    fun validate_questionWithEmptyTitle_returnsErrorTest() {
        assertIsErrorValidate(aQuestionWith().emptyTitle().build())
    }

    @Test
    fun validate_questionNoProblem_returnsWarningTest() {
        assertIsWarningValidate(aQuestionWith().emptyProblem().build())
    }

    @Test
    fun validate_questionWithUnspecifiedAnswer_returnsErrorTest() {
        assertIsErrorValidate(aQuestionWith().unspecifiedAnswer().build())
    }

    @Test
    fun validate_question_returnsOkTest() {
        assertIsOkValidate(aQuestion())
    }

}