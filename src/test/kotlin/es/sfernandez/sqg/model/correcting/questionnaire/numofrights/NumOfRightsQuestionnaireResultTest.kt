package es.sfernandez.sqg.model.correcting.questionnaire.numofrights

import es.sfernandez.sqg.beans.Questionnaire
import es.sfernandez.sqg.beans.question.Question
import org.assertj.core.api.Assertions.assertThat
import org.mockito.Mockito
import kotlin.test.Test

class NumOfRightsQuestionnaireResultTest {

    //---- Methods ----
    private fun createQuestionnaire(): Questionnaire {
        return Mockito.mock(Questionnaire::class.java)
    }

    private fun createQuestionnaireWithNQuestions(numOfQuestions: Int): Questionnaire {
        val questionnaire = createQuestionnaire()
        Mockito.`when`(questionnaire.questions).thenReturn(createQuestions(numOfQuestions))
        return questionnaire
    }

    private fun createQuestion(): Question {
        return Mockito.mock(Question::class.java)
    }

    private fun createQuestions(numOfQuestions: Int): Array<Question> {
        return generateSequence(0) { it + 1 }
            .take(numOfQuestions)
            .map { _ -> createQuestion() }
            .toList()
            .toTypedArray()
    }

    //---- Tests ----
    @Test
    fun afterConstruct_questionnaireIsAssignedCorrectlyTest() {
        val questionnaire = createQuestionnaire()

        val result = NumOfRightsQuestionnaireResult(questionnaire, 0,0,0)

        assertThat(result.questionnaire).isSameAs(questionnaire)
    }

    @Test
    fun afterConstruct_numOfQuestionsIsInitializedCorrectlyTest() {
        val numOfQuestions = 10
        val questionnaire = createQuestionnaireWithNQuestions(numOfQuestions)

        val result = NumOfRightsQuestionnaireResult(questionnaire, 0,0,0)

        assertThat(result.numOfQuestions).isEqualTo(numOfQuestions)
    }

    @Test
    fun afterConstruct_numOfNotAnsweredIsAssignedCorrectlyTest() {
        val numOfNotAnswered = 3

        val result = NumOfRightsQuestionnaireResult(createQuestionnaire(), numOfNotAnswered, 0,0)

        assertThat(result.numOfNotAnswered).isEqualTo(numOfNotAnswered)
    }

    @Test
    fun afterConstruct_numOfRightsIsAssignedCorrectlyTest() {
        val numOfRights = 3

        val result = NumOfRightsQuestionnaireResult(createQuestionnaire(), 0, numOfRights,0)

        assertThat(result.numOfRights).isEqualTo(numOfRights)
    }

    @Test
    fun afterConstruct_numOfFailuresIsAssignedCorrectlyTest() {
        val numOfFailures = 3

        val result = NumOfRightsQuestionnaireResult(createQuestionnaire(), 0, 0, numOfFailures)

        assertThat(result.numOfFailures).isEqualTo(numOfFailures)
    }

}