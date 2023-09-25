package es.sfernandez.sqg.model.validators.beans.contents

import es.sfernandez.sqg.beans.contents.HasContents
import es.sfernandez.sqg.model.validators.ValidatorTest
import es.sfernandez.sqg.utilities.fixtures.beans.contents.UsesHasContentsFixtures
import org.junit.jupiter.api.Test

class HasContentsValidatorTest : ValidatorTest<HasContents>, UsesHasContentsFixtures {

    //---- Attributes ----
    override val validator = HasContentsValidator()

    //---- Test ----
    @Test
    fun validate_emptyHasContents_returnsWarningTest() {
        assertIsWarningValidate(anEmptyHasContents())
    }

    @Test
    fun validate_notEmptyHasContents_returnsOkTest() {
        assertIsOkValidate(anArbitraryHasContents())
    }

}