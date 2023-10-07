package es.sfernandez.sqg.model.validators.beans.question.answers.input

import es.sfernandez.sqg.beans.question.answers.input.SelectionAnswerInput
import es.sfernandez.sqg.model.validators.ValidationResult
import es.sfernandez.sqg.model.validators.Validator

class SelectionAnswerInputValidator : Validator<SelectionAnswerInput> {

    override fun validate(value: SelectionAnswerInput): ValidationResult<SelectionAnswerInput> {
        return checkIfPossibleChoicesAreEmpty(value)
    }

    private fun checkIfPossibleChoicesAreEmpty(input: SelectionAnswerInput): ValidationResult<SelectionAnswerInput> {
        return if(input.possibleChoices.isEmpty())
                ValidationResult.warning("Warning! There is a SelectionAnswerInput without possible choices.")
            else
                ValidationResult.ok()
    }

}