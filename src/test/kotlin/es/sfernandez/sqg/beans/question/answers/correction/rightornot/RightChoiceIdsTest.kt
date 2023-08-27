package es.sfernandez.sqg.beans.question.answers.correction.rightornot

import es.sfernandez.sqg.utilities.Fixtures
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class RightChoiceIdsTest: RightOrNotCorrectionTest {

    //---- Methods ----
    override fun createCorrection(): RightOrNotCorrection {
        return RightChoiceIds()
    }
    
    //---- Test ----
    @Test
    fun construct_withoutRightChoiceIds_worksTest() {
        val correction = RightChoiceIds()
    
        Assertions.assertThat(correction.ids).isEmpty()
    }
    
    @Test
    fun construct_withOnlyOnePossibleValue_worksTest() {
        val id = Fixtures.SOME_TEXT_1
    
        val correction = RightChoiceIds(id)
    
        Assertions.assertThat(correction.ids).containsOnly(id)
    }
    
    @Test
    fun construct_withMoreThanOneRightChoiceIds_worksTest() {
        val ids = arrayOf(Fixtures.SOME_TEXT_1, Fixtures.SOME_TEXT_2)
    
        val correction = RightChoiceIds(*ids)
    
        Assertions.assertThat(correction.ids).containsOnly(*ids)
    }

}