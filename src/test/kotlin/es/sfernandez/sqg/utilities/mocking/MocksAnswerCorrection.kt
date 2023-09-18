package es.sfernandez.sqg.utilities.mocking

import es.sfernandez.sqg.beans.question.answers.correction.AnswerCorrection
import org.mockito.Mockito

interface MocksAnswerCorrection {

    fun mockAnswerCorrection(): AnswerCorrection {
        return Mockito.mock(AnswerCorrection::class.java)
    }

}