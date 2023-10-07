package es.sfernandez.sqg.model.validators.beans.question.answers.correction.rightornot

import es.sfernandez.sqg.beans.question.answers.correction.rightornot.RightChoiceIds
import es.sfernandez.sqg.model.validators.ValidatorTest
import es.sfernandez.sqg.utilities.fixtures.beans.question.answers.correction.rightornot.UsesRightChoiceIdsFixtures
import org.junit.jupiter.api.Test

class RightChoiceIdsValidatorTest : ValidatorTest<RightChoiceIds>, UsesRightChoiceIdsFixtures {

    //---- Attributes ----
    override val validator = RightChoiceIdsValidator()

    //---- Tests ----
    @Test
    fun validate_anEmptyRightChoiceIdsCorrection_returnsWarningTest() {
        assertIsWarningValidate(aRightChoiceIdsWith().noRightIds().build())
    }

    @Test
    fun validate_aRightChoiceIdsCorrection_returnsOkTest() {
        assertIsOkValidate(aRightChoiceIds())
    }

}