package es.sfernandez.sqg.beans.question.answers.correction.rightornot

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CorrectValuesRegexTest: RightOrNotCorrectionTest {

    //---- Fixtures ----
    private val regex = Regex(".*")

    //---- Methods ----
    override fun createCorrection(): RightOrNotCorrection {
        return CorrectValuesRegex(regex)
    }

    //---- Tests ----
    @Test
    fun afterConstruct_regexIsAssignedCorrectlyTest() {
        val correction = CorrectValuesRegex(regex)

        assertThat(correction.regex).isSameAs(regex)
    }

    @Test
    fun assignRegex_worksProperlyTest() {
        val anotherRegex = Regex(".*")
        val correction = CorrectValuesRegex(regex)

        correction.regex = anotherRegex

        assertThat(correction.regex).isSameAs(anotherRegex)
    }

}