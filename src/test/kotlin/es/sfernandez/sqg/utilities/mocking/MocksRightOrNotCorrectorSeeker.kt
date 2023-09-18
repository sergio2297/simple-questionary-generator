package es.sfernandez.sqg.utilities.mocking

import es.sfernandez.sqg.model.correcting.answer.correctors.rightornot.RightOrNotAnswerCorrector
import es.sfernandez.sqg.model.correcting.answer.seekers.RightOrNotCorrectorSeeker
import org.mockito.Mockito
import org.mockito.kotlin.any

interface MocksRightOrNotCorrectorSeeker {

    fun mockRightOrNotCorrectorSeeker(): RightOrNotCorrectorSeeker {
        return Mockito.mock(RightOrNotCorrectorSeeker::class.java)
    }

    fun mockRightOrNotCorrectorSeekerToFind(corrector: RightOrNotAnswerCorrector): RightOrNotCorrectorSeeker {
        val seeker = mockRightOrNotCorrectorSeeker()
        Mockito.`when`(seeker.correctorFor(any())).thenReturn(corrector)
        return seeker
    }

}