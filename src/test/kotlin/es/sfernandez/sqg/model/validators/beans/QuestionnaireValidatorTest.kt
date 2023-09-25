package es.sfernandez.sqg.model.validators.beans

import es.sfernandez.sqg.beans.Questionnaire
import es.sfernandez.sqg.model.validators.ValidatorTest
import es.sfernandez.sqg.utilities.fixtures.beans.UsesQuestionnaireFixtures
import org.junit.jupiter.api.Test

class QuestionnaireValidatorTest : ValidatorTest<Questionnaire>, UsesQuestionnaireFixtures {

    //---- Attributes ----
    override val validator = QuestionnaireValidator()

    //---- Tests ----
    @Test
    fun validate_questionnaireWithEmptyTitle_returnsErrorTest() {
        assertIsErrorValidate(aQuestionnaireWith().emptyTitle().build())
    }

    @Test
    fun validate_questionnaireWithNoQuestions_returnsErrorTest() {
        assertIsErrorValidate(aQuestionnaireWith().numOfQuestions(0).build())
    }

    @Test
    fun validate_questionnaireWithTitleAndQuestions_returnsOkTest() {
        assertIsOkValidate(aQuestionnaire())
    }

}