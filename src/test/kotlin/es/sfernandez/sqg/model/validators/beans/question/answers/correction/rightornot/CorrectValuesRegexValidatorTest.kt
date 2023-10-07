package es.sfernandez.sqg.model.validators.beans.question.answers.correction.rightornot

import es.sfernandez.sqg.beans.question.answers.correction.rightornot.CorrectValuesRegex
import es.sfernandez.sqg.model.validators.ValidatorTest
import es.sfernandez.sqg.utilities.fixtures.beans.question.answers.correction.rightornot.UsesCorrectValuesRegexFixtures
import org.junit.jupiter.api.Test

class CorrectValuesRegexValidatorTest : ValidatorTest<CorrectValuesRegex>, UsesCorrectValuesRegexFixtures {

    //---- Attributes ----
    override val validator = CorrectValuesRegexValidator()

    //---- Tests ----
    @Test
    fun validate_aCorrectValuesRegexWithEmptyRegex_returnsErrorTest() {
        assertIsErrorValidate(aCorrectValuesRegexWith().emptyPattern().build())
    }

    @Test
    fun validate_aCorrectValuesRegex_returnsOkTest() {
        assertIsOkValidate(aCorrectValuesRegex())
    }

}