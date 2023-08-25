package es.sfernandez.sqg.model.correcting.questionnaire

import es.sfernandez.sqg.beans.Questionnaire

/**
 * A QuestionnaireResult represents the result of correcting all questions present in a [Questionnaire]
 */
interface QuestionnaireResult {

    /** The Questionnaire tha has been correcting */
    val questionnaire: Questionnaire

    /** Num of questions in the questionnaire */
    val numOfQuestions: Int
        get() = questionnaire.questions.size

    /** Num of not answered questions */
    val numOfNotAnswered: Int

}