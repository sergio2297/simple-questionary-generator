package es.sfernandez.sqg.beans.question.answers.correction

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class UnspecifiedAnswerCorrectionTest {

    //---- Tests ----
    @Test
    fun anUnspecifiedAnswerCorrector_isUnspecifiedTypeTest() {
        val correction = UnspecifiedAnswerCorrection()

        assertThat(correction.type).isEqualTo(AnswerCorrection.Type.UNSPECIFIED)
    }

}