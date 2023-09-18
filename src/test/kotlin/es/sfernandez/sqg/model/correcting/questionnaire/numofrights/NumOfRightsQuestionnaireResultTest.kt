package es.sfernandez.sqg.model.correcting.questionnaire.numofrights

import es.sfernandez.sqg.beans.Questionnaire
import es.sfernandez.sqg.utilities.mocking.MocksQuestion
import es.sfernandez.sqg.utilities.mocking.MocksQuestionnaire
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class NumOfRightsQuestionnaireResultTest : MocksQuestionnaire, MocksQuestion {

    //---- Methods ----
    private fun mockQuestionnaireWithNQuestions(numOfQuestions: Int): Questionnaire {
        return mockQuestionnaireWith(mockQuestions(numOfQuestions))
    }

    //---- Tests ----
    @Test
    fun afterConstruct_questionnaireIsAssignedCorrectlyTest() {
        val questionnaire = mockQuestionnaire()

        val result = NumOfRightsQuestionnaireResult(questionnaire, 0,0,0)

        assertThat(result.questionnaire).isSameAs(questionnaire)
    }

    @Test
    fun afterConstruct_numOfQuestionsIsInitializedCorrectlyTest() {
        val numOfQuestions = 10
        val questionnaire = mockQuestionnaireWithNQuestions(numOfQuestions)

        val result = NumOfRightsQuestionnaireResult(questionnaire, 0,0,0)

        assertThat(result.numOfQuestions).isEqualTo(numOfQuestions)
    }

    @Test
    fun afterConstruct_numOfNotAnsweredIsAssignedCorrectlyTest() {
        val numOfNotAnswered = 3

        val result = NumOfRightsQuestionnaireResult(mockQuestionnaire(), numOfNotAnswered, 0,0)

        assertThat(result.numOfNotAnswered).isEqualTo(numOfNotAnswered)
    }

    @Test
    fun afterConstruct_numOfRightsIsAssignedCorrectlyTest() {
        val numOfRights = 3

        val result = NumOfRightsQuestionnaireResult(mockQuestionnaire(), 0, numOfRights,0)

        assertThat(result.numOfRights).isEqualTo(numOfRights)
    }

    @Test
    fun afterConstruct_numOfFailuresIsAssignedCorrectlyTest() {
        val numOfFailures = 3

        val result = NumOfRightsQuestionnaireResult(mockQuestionnaire(), 0, 0, numOfFailures)

        assertThat(result.numOfFailures).isEqualTo(numOfFailures)
    }

}