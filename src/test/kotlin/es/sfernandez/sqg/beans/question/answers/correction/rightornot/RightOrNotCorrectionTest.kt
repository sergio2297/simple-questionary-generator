package es.sfernandez.sqg.beans.question.answers.correction.rightornot

import es.sfernandez.sqg.beans.question.answers.correction.AnswerCorrection
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

interface RightOrNotCorrectionTest {

    fun createCorrection(): RightOrNotCorrection

    @Test
    fun correctionTypeIs_RightOrNotTest() {
        assertThat(createCorrection().type).isEqualTo(AnswerCorrection.Type.RIGHT_OR_NOT)
    }

}