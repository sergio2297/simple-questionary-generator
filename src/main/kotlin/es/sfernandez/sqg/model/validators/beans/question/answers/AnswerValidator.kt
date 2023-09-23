package es.sfernandez.sqg.model.validators.beans.question.answers

import es.sfernandez.sqg.beans.question.answers.Answer
import es.sfernandez.sqg.beans.question.answers.correction.AnswerCorrection
import es.sfernandez.sqg.beans.question.answers.input.AnswerInput
import es.sfernandez.sqg.model.validators.ValidationResult
import es.sfernandez.sqg.model.validators.Validator

class AnswerValidator : Validator<Answer> {

    override fun validate(value: Answer): ValidationResult<Answer> {
        return Validator.validateIteratively(value, *validations())
    }

    private fun validations() : Array<(Answer) -> ValidationResult<Answer>> {
        return arrayOf(
            ::checkIfInputIsSpecified,
            ::checkIfCorrectionIsSpecified,
        )
    }

    private fun checkIfInputIsSpecified(answer: Answer) : ValidationResult<Answer> {
        return if(answer.input.type != AnswerInput.Type.UNSPECIFIED)
            ValidationResult.ok()
        else
            ValidationResult.error("Error. Answers must have a specified input type.")
    }

    private fun checkIfCorrectionIsSpecified(answer: Answer) : ValidationResult<Answer> {
        return if(answer.correction.type != AnswerCorrection.Type.UNSPECIFIED)
                ValidationResult.ok()
            else
                ValidationResult.error("Error. Answers must have a specified correction type.")
    }

}