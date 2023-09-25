package es.sfernandez.sqg.model.validators.beans.question

import es.sfernandez.sqg.beans.question.Question
import es.sfernandez.sqg.beans.question.answers.Answer
import es.sfernandez.sqg.beans.question.problems.Problem
import es.sfernandez.sqg.model.validators.ValidationResult
import es.sfernandez.sqg.model.validators.Validator
import es.sfernandez.sqg.model.validators.beans.contents.HasContentsValidator
import es.sfernandez.sqg.model.validators.beans.question.answers.AnswerValidator

class QuestionValidator : Validator<Question> {

    //---- Methods ----
    override fun validate(value: Question): ValidationResult<Question> {
        return Validator.validateIteratively(value, *validations())
    }

    private fun validations() : Array<(Question) -> ValidationResult<Question>> {
        return arrayOf(
            ::checkIfTitleIsEmpty,
            ::checkIfProblemIsEmpty,
            ::checkIfAnswerIsUnspecified,
        )
    }

    private fun checkIfTitleIsEmpty(question: Question) : ValidationResult<Question> {
        return if(question.title.trim().isEmpty())
                ValidationResult.error("Error. Questions must have a not empty title.")
            else
                ValidationResult.ok()
    }

    private fun checkIfProblemIsEmpty(question: Question) : ValidationResult<Question> {
        return if(hasContents(question.problem))
                ValidationResult.ok()
            else
                ValidationResult.warning("Warning! Question's problem is empty.")
    }

    private fun hasContents(problem: Problem) : Boolean {
        return HasContentsValidator().validate(problem).isOk()
    }

    private fun checkIfAnswerIsUnspecified(question: Question) : ValidationResult<Question> {
        return if(isUnspecified(question.answer))
            ValidationResult.error("Error. Questions must have an answer.")
        else
            ValidationResult.ok()
    }

    private fun isUnspecified(answer: Answer) : Boolean {
        return !AnswerValidator().validate(answer).isOk()
    }

}