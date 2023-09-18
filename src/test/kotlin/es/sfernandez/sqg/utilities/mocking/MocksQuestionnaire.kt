package es.sfernandez.sqg.utilities.mocking

import es.sfernandez.sqg.beans.Questionnaire
import es.sfernandez.sqg.beans.question.Question
import org.mockito.Mockito

internal interface MocksQuestionnaire {

    fun mockQuestionnaire(): Questionnaire {
        return Mockito.mock(Questionnaire::class.java)
    }

    fun mockQuestionnaireWith(questions: Array<Question>): Questionnaire {
        val questionnaire = mockQuestionnaire()
        Mockito.`when`(questionnaire.questions).thenReturn(questions)
        return questionnaire
    }

}