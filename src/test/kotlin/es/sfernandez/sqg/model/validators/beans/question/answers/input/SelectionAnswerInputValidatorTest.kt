package es.sfernandez.sqg.model.validators.beans.question.answers.input

import es.sfernandez.sqg.beans.question.answers.input.SelectionAnswerInput
import es.sfernandez.sqg.model.validators.ValidatorTest
import es.sfernandez.sqg.utilities.fixtures.beans.question.answers.input.UsesSelectionAnswerInputFixtures
import org.junit.jupiter.api.Test

class SelectionAnswerInputValidatorTest : ValidatorTest<SelectionAnswerInput>, UsesSelectionAnswerInputFixtures {

    //---- Attributes ----
    override val validator = SelectionAnswerInputValidator()

    //---- Tests ----
    @Test
    fun validate_aInputWithoutPossibleChoices_returnsWarningTest() {
        assertIsWarningValidate(aSelectionAnswerInputWith().noPossibleChoices().build())
    }

    @Test
    fun validate_aInput_returnsOkTest() {
        assertIsOkValidate(aSelectionAnswerInput())
    }

}