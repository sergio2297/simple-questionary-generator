package es.sfernandez.sqg.utilities.fixtures.beans.question.answers.input

import es.sfernandez.sqg.beans.question.answers.input.AnswerInput
import es.sfernandez.sqg.beans.question.answers.input.UnspecifiedAnswerInput
import org.mockito.Mockito

interface UsesAnswerInputFixtures {

    fun anAnswerInput() : AnswerInput {
        val input = Mockito.mock(AnswerInput::class.java)
        Mockito.`when`(input.type).thenReturn(AnswerInput.Type.TEXT)
        return input
    }

    fun anUnspecifiedAnswerInput() : AnswerInput {
        return UnspecifiedAnswerInput()
    }

}