package es.sfernandez.sqg.utilities.mocking

import es.sfernandez.sqg.beans.question.answers.correction.AnswerCorrection
import es.sfernandez.sqg.model.correcting.answer.correctors.AnswerCorrector
import es.sfernandez.sqg.model.correcting.replies.Reply
import org.mockito.Mockito
import org.mockito.kotlin.any

interface MocksAnswerCorrector {

    fun mockAnswerCorrector(): AnswerCorrector<*> {
        return Mockito.mock(AnswerCorrector::class.java)
    }

    fun <RESULT> mockAnswerCorrectorToReturn(corrector: AnswerCorrector<*>, result: RESULT): AnswerCorrector<*> {
        Mockito.`when`(corrector.correct(any(), any())).thenReturn(result)
        return corrector
    }

    fun <RESULT> mockAnswerCorrectorToReturn(result: RESULT): AnswerCorrector<*> {
        return mockAnswerCorrectorToReturn(mockAnswerCorrector(), result)
    }

    fun <RESULT> mockAnswerCorrectorFor(correction: AnswerCorrection, reply: Reply<*>, result: RESULT): AnswerCorrector<*> {
        val corrector = mockAnswerCorrector()
        Mockito.`when`(corrector.correct(correction, reply)).thenReturn(result)
        return corrector
    }

}