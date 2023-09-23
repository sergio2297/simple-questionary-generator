package es.sfernandez.sqg.model.validators.beans

import es.sfernandez.sqg.beans.Questionnaire
import es.sfernandez.sqg.model.validators.ValidationResult
import es.sfernandez.sqg.model.validators.Validator

class QuestionnaireValidator : Validator<Questionnaire> {

    override fun validate(value: Questionnaire): ValidationResult<Questionnaire> {
        val result = checkIfHasEmptyTitle(value)

        return if(result.isOk())
                checkIfHasQuestions(value)
            else
                result
    }

    private fun checkIfHasEmptyTitle(questionnaire: Questionnaire): ValidationResult<Questionnaire> {
        return if(questionnaire.title.trim().isEmpty())
                ValidationResult.error("Questionnaire must have a title")
            else
                ValidationResult.ok()
    }

    private fun checkIfHasQuestions(questionnaire: Questionnaire): ValidationResult<Questionnaire> {
        return if(questionnaire.questions.isEmpty())
            ValidationResult.error("Questionnaire must have at least one Question")
        else
            ValidationResult.ok()
    }

}