package es.sfernandez.sqg.utilities.mocking

import es.sfernandez.sqg.beans.question.answers.Answer
import es.sfernandez.sqg.beans.question.answers.correction.AnswerCorrection
import org.mockito.Mockito

interface MocksAnswer {

    fun mockAnswer(): Answer {
        return Mockito.mock(Answer::class.java)
    }

    fun mockAnswerWith(correction: AnswerCorrection): Answer {
        val answer = mockAnswer()
        Mockito.`when`(answer.correction).thenReturn(correction)
        return answer
    }

}