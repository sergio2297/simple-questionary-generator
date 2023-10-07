package es.sfernandez.sqg.model.validators.beans.question.answers.correction.rightornot

import es.sfernandez.sqg.beans.question.answers.correction.rightornot.PossibleValues
import es.sfernandez.sqg.model.validators.ValidatorTest
import es.sfernandez.sqg.utilities.fixtures.beans.question.answers.correction.rightornot.UsesPossibleValuesFixtures
import org.junit.jupiter.api.Test

class PossibleValuesValidatorTest : ValidatorTest<PossibleValues>, UsesPossibleValuesFixtures {

    //---- Attributes ----
    override val validator = PossibleValuesValidator()

    //---- Tests ----
    @Test
    fun validate_anEmptyPossibleValuesCorrection_returnsWarningTest() {
        assertIsWarningValidate(aPossibleValuesWith().noValues().build())
    }

    @Test
    fun validate_aPossibleValuesCorrection_returnsOkTest() {
        assertIsOkValidate(aPossibleValues())
    }

}