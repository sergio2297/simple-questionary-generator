package es.sfernandez.sqg.model.validators.beans.question.answers.correction.rightornot

import es.sfernandez.sqg.beans.question.answers.correction.rightornot.RightChoiceIds
import es.sfernandez.sqg.model.validators.ValidationResult
import es.sfernandez.sqg.model.validators.Validator

class RightChoiceIdsValidator : Validator<RightChoiceIds> {

    override fun validate(value: RightChoiceIds): ValidationResult<RightChoiceIds> {
        return checkIfRightChoiceIdsIsEmpty(value)
    }

    private fun checkIfRightChoiceIdsIsEmpty(rightChoiceIds: RightChoiceIds): ValidationResult<RightChoiceIds> {
        return if(rightChoiceIds.ids.isEmpty())
            ValidationResult.warning("Warning! No right choice ids specified for correction.")
        else
            ValidationResult.ok()
    }

}