package es.sfernandez.sqg.model.validators.beans.question.answers

import es.sfernandez.sqg.beans.contents.ContentType
import es.sfernandez.sqg.beans.question.answers.Choice
import es.sfernandez.sqg.model.validators.ValidationResult
import es.sfernandez.sqg.model.validators.Validator

class ChoiceValidator : Validator<Choice> {

    override fun validate(value: Choice): ValidationResult<Choice> {
        return Validator.validateIteratively(value,
            ::checkIfIdIsBlank,
            ::checkIfContentIsUnknown,
        )
    }

    private fun checkIfIdIsBlank(choice: Choice): ValidationResult<Choice> {
        return if(choice.id.isBlank())
                ValidationResult.error("Error. All choices must have a not blank id.")
            else
                ValidationResult.ok()
    }

    private fun checkIfContentIsUnknown(choice: Choice): ValidationResult<Choice> {
        return if(choice.content.type == ContentType.UNKNOWN)
                ValidationResult.error("Error. All choices must have a well defined content.")
            else
                ValidationResult.ok()
    }

}