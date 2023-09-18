package es.sfernandez.sqg.utilities.mocking

import es.sfernandez.sqg.model.correcting.answer.correctors.rightornot.RightOrNotAnswerCorrector
import org.mockito.Mockito

interface MocksRightOrNotAnswerCorrector : MocksAnswerCorrector {

    fun mockRightOrNotAnswerCorrector(): RightOrNotAnswerCorrector {
        return Mockito.mock(RightOrNotAnswerCorrector::class.java)
    }

    fun mockRightOrNotAnswerCorrectorToReturn(corrector: RightOrNotAnswerCorrector, result: Boolean): RightOrNotAnswerCorrector {
        super.mockAnswerCorrectorToReturn(corrector, result)
        return corrector
    }

    fun mockRightOrNotAnswerCorrectorToReturn(result: Boolean): RightOrNotAnswerCorrector {
        return mockRightOrNotAnswerCorrectorToReturn(mockRightOrNotAnswerCorrector(), result)
    }

}