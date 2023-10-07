package es.sfernandez.sqg.model.validators.beans.question.answers.correction.rightornot

import es.sfernandez.sqg.beans.question.answers.correction.rightornot.CorrectValuesRegex
import es.sfernandez.sqg.model.validators.ValidationResult
import es.sfernandez.sqg.model.validators.Validator

class CorrectValuesRegexValidator : Validator<CorrectValuesRegex> {

    override fun validate(value: CorrectValuesRegex): ValidationResult<CorrectValuesRegex> {
        return checkIfRegexIsEmpty(value)
    }

    private fun checkIfRegexIsEmpty(valuesRegex: CorrectValuesRegex): ValidationResult<CorrectValuesRegex> {
        return if(valuesRegex.regex.pattern.isBlank())
                ValidationResult.error("Error. You're using a CorrectValuesRegex without regex's pattern.")
            else
                ValidationResult.ok()
    }

}