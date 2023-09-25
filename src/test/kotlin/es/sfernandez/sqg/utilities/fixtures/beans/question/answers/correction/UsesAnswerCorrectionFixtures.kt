package es.sfernandez.sqg.utilities.fixtures.beans.question.answers.correction

import es.sfernandez.sqg.beans.question.answers.correction.AnswerCorrection
import es.sfernandez.sqg.beans.question.answers.correction.UnspecifiedAnswerCorrection
import org.mockito.Mockito

interface UsesAnswerCorrectionFixtures {

    fun anAnswerCorrection() : AnswerCorrection {
        val input = Mockito.mock(AnswerCorrection::class.java)
        Mockito.`when`(input.type).thenReturn(AnswerCorrection.Type.RIGHT_OR_NOT)
        return input
    }

    fun anUnspecifiedAnswerCorrection() : AnswerCorrection {
        return UnspecifiedAnswerCorrection()
    }
    
}