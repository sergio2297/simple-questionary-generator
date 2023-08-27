package es.sfernandez.sqg.beans.question.answers.correction.rightornot

import es.sfernandez.sqg.utilities.Fixtures
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class PossibleValuesTest: RightOrNotCorrectionTest {

    //---- Methods ----
    override fun createCorrection(): RightOrNotCorrection {
        return PossibleValues()
    }

    //---- Test ----
    @Test
    fun construct_withoutPossibleValues_worksTest() {
        val correction = PossibleValues()

        assertThat(correction.values).isEmpty()
    }

    @Test
    fun construct_withOnlyOnePossibleValue_worksTest() {
        val possibleValue = Fixtures.SOME_TEXT_1

        val correction = PossibleValues(possibleValue)

        assertThat(correction.values).containsOnly(possibleValue)
    }

    @Test
    fun construct_withMoreThanOnePossibleValues_worksTest() {
        val possibleValues = arrayOf(Fixtures.SOME_TEXT_1, Fixtures.SOME_TEXT_2)

        val correction = PossibleValues(*possibleValues)

        assertThat(correction.values).containsOnly(*possibleValues)
    }

}