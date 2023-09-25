package es.sfernandez.sqg.model.validators.beans.contents

import es.sfernandez.sqg.beans.contents.IsResource
import es.sfernandez.sqg.model.validators.ValidatorTest
import es.sfernandez.sqg.utilities.fixtures.beans.contents.UsesIsResourceFixtures
import org.junit.jupiter.api.Test

class IsResourceValidatorTest : ValidatorTest<IsResource>, UsesIsResourceFixtures {

    //---- Attributes ----
    override val validator = IsResourceValidator()

    //---- Tests ----
    @Test
    fun validate_resourceWithEmptyPath_returnErrorTest() {
        assertIsErrorValidate(anEmptyResource())
    }

    @Test
    fun validate_resourceWithPathWithoutCharacters_returnErrorTest() {
        val blankPath = "   \t "

        assertIsErrorValidate(aResourceWith(blankPath))
    }

    @Test
    fun validate_resourceWithNotEmptyPath_returnOkTest() {
        assertIsOkValidate(aResource())
    }

}