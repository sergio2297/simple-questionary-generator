package es.sfernandez.sqg.utilities.mocking

import es.sfernandez.sqg.model.correcting.questionnaire.numofrights.RightOrNotQuestionResult
import org.mockito.Mockito

interface MocksRightOrNotQuestionResult {

    fun mockRightOrNotQuestionResult(): RightOrNotQuestionResult {
        return Mockito.mock(RightOrNotQuestionResult::class.java)
    }

    fun mockRightOrNotQuestionResult(isRight: Boolean): RightOrNotQuestionResult {
        val result = mockRightOrNotQuestionResult()
        Mockito.`when`(result.isRight).thenReturn(isRight)
        return result
    }

}