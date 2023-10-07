package es.sfernandez.sqg.model.validators.beans.question.answers.correction.rightornot

import es.sfernandez.sqg.beans.question.answers.correction.rightornot.PossibleValues
import es.sfernandez.sqg.model.validators.ValidationResult
import es.sfernandez.sqg.model.validators.Validator

class PossibleValuesValidator : Validator<PossibleValues> {

    override fun validate(value: PossibleValues): ValidationResult<PossibleValues> {
        return checkIfPossibleValuesIsEmpty(value)
    }

    private fun checkIfPossibleValuesIsEmpty(possibleValues: PossibleValues): ValidationResult<PossibleValues> {
        return if(possibleValues.values.isEmpty())
                ValidationResult.warning("Warning! No possible values specified for correction.")
            else
                ValidationResult.ok()
    }

}