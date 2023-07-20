package es.sfernandez.sqg.model.validators.answers

import es.sfernandez.sqg.beans.question.answers.MultipleSelectionAnswer
import es.sfernandez.sqg.beans.question.answers.SelectionAnswer
import es.sfernandez.sqg.beans.question.answers.SingleSelectionAnswer
import es.sfernandez.sqg.beans.question.answers.choices.Choice
import es.sfernandez.sqg.model.validators.ValidationResult
import es.sfernandez.sqg.model.validators.Validator

class SelectionAnswerValidator : Validator<SelectionAnswer>{

    override fun validate(value: SelectionAnswer): ValidationResult<SelectionAnswer> {
        val result = validatePossibleChoicesOf(value)

        return if(result.isOk())
            validateRightChoiceIds(value)
        else
            result
    }

    private fun validatePossibleChoicesOf(value: SelectionAnswer) : ValidationResult<SelectionAnswer> {
        if(value.possibleChoices.isEmpty())
            return ValidationResult.error("Error. Possible choices of a ${SelectionAnswer::class.simpleName} can't be empty", value)

        return ValidationResult.ok()
    }

    private fun validateRightChoiceIds(answer: SelectionAnswer) : ValidationResult<SelectionAnswer> {
        val rightChoicesIds = rightChoicesIdsOf(answer)
            ?: return ValidationResult.warning("No validation routine defined for ${answer.javaClass.simpleName}", answer)

        if(rightChoicesIds.isEmpty())
            return ValidationResult.error("Error. It's not possible to create a ${SelectionAnswer::class.simpleName}" +
                    " with no right choices ids.", answer)

        if(!rightChoicesIds.all(possibleChoicesIdsOf(answer)::contains))
            return ValidationResult.error("Error. One of the given choices aren't contained in possible choices.", answer)

        return ValidationResult.ok()
    }

    private fun rightChoicesIdsOf(answer: SelectionAnswer) : Array<String>? {
        return when (answer) {
            is SingleSelectionAnswer -> arrayOf(answer.rightChoiceId)
            is MultipleSelectionAnswer -> answer.rightChoicesIds
            // No other is possible cause SelectionAnswer is sealed
        }
    }

    private fun possibleChoicesIdsOf(answer: SelectionAnswer) : Array<String> {
        return answer.possibleChoices.map(Choice::id).toTypedArray()
    }

}