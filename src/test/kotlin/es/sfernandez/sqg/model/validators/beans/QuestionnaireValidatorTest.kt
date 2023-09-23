package es.sfernandez.sqg.model.validators.beans

import es.sfernandez.sqg.beans.Questionnaire
import es.sfernandez.sqg.model.validators.ValidatorTest
import es.sfernandez.sqg.utilities.mocking.MocksQuestion
import org.junit.jupiter.api.Test

class QuestionnaireValidatorTest : ValidatorTest<Questionnaire>, MocksQuestion {

    //---- Attributes ----
    override val validator = QuestionnaireValidator()

    //---- Methods ----
    private fun aQuestionnaire() : Questionnaire {
        val questionnaire = Questionnaire()
        questionnaire.title = "Title"
        questionnaire.questions = mockQuestions(2)
        return questionnaire
    }

    private fun aQuestionnaireWithoutTitle() : Questionnaire {
        val questionnaire = aQuestionnaire()
        questionnaire.title = ""
        return questionnaire
    }

    private fun aQuestionnaireWithoutQuestions() : Questionnaire {
        val questionnaire = aQuestionnaire()
        questionnaire.questions = arrayOf()
        return questionnaire
    }

    //---- Tests ----
    @Test
    fun validate_questionnaireWithEmptyTitle_returnsErrorTest() {
        assertIsErrorValidate(aQuestionnaireWithoutTitle())
    }

    @Test
    fun validate_questionnaireWithNoQuestions_returnsErrorTest() {
        assertIsErrorValidate(aQuestionnaireWithoutQuestions())
    }

    @Test
    fun validate_questionnaireWithTitleAndQuestions_returnsOkTest() {
        assertIsOkValidate(aQuestionnaire())
    }

}