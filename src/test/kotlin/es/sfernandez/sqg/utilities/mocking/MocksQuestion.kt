package es.sfernandez.sqg.utilities.mocking

import es.sfernandez.sqg.beans.question.Question
import org.mockito.Mockito

internal interface MocksQuestion {

    fun mockQuestion(): Question {
        return Mockito.mock(Question::class.java)
    }

    fun mockQuestions(numOfQuestions: Int): Array<Question> {
        return generateMocks(this::mockQuestion, numOfQuestions)
    }

}