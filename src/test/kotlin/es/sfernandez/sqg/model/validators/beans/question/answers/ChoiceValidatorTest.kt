package es.sfernandez.sqg.model.validators.beans.question.answers

import es.sfernandez.sqg.beans.question.answers.Choice
import es.sfernandez.sqg.model.validators.ValidatorTest
import es.sfernandez.sqg.utilities.fixtures.beans.question.answers.UsesChoiceFixtures
import org.junit.jupiter.api.Test

class ChoiceValidatorTest : ValidatorTest<Choice>, UsesChoiceFixtures {

    //---- Attributes ----
    override val validator = ChoiceValidator()

    //---- Tests ----
    @Test
    fun validate_aChoiceWithBlankId_returnsErrorTest() {
        assertIsErrorValidate(aChoiceWith().emptyId().build())
    }

    @Test
    fun validate_aChoiceWithUnknownContent_returnsErrorTest() {
        assertIsErrorValidate(aChoiceWith().unknownContent().build())
    }

    @Test
    fun validate_aChoice_returnsOkTest() {
        assertIsOkValidate(aChoice())
    }

}