package es.sfernandez.sqg.model.validators.answers

import es.sfernandez.sqg.beans.question.answers.TextAnswer
import es.sfernandez.sqg.model.validators.ValidationResult
import es.sfernandez.sqg.model.validators.Validator

class TextAnswerValidator : Validator<TextAnswer> {

    //---- Methods ----
    override fun validate(value: TextAnswer): ValidationResult<TextAnswer> {
        return if(value.checkWithRegex)
                validateRegexOf(value)
            else
                validatePossibleRepliesOf(value)
    }

    private fun validateRegexOf(textAnswer: TextAnswer): ValidationResult<TextAnswer> {
        if(textAnswer.replyRegex.pattern.isEmpty())
            return ValidationResult.error("Error. The regex used for evaluate replies need a non empty pattern.", textAnswer)

        return ValidationResult.ok()
    }

    private fun validatePossibleRepliesOf(textAnswer: TextAnswer): ValidationResult<TextAnswer> {
        if(textAnswer.possibleReplies.isEmpty())
            return ValidationResult.error("Error. At least one possible reply is needed to create a ${TextAnswer::class.simpleName}.", textAnswer)

        return ValidationResult.ok()
    }

}