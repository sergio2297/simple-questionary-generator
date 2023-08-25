package es.sfernandez.sqg.model.correcting.questionnaire.numofrights

import es.sfernandez.sqg.beans.Questionnaire
import es.sfernandez.sqg.beans.question.answers.correction.AnswerCorrection
import es.sfernandez.sqg.model.correcting.questionnaire.QuestionnaireResult

/**
 * A NumOfRightsQuestionnaireResult represents the result of correcting all questions present in a [Questionnaire] which
 * its questions are of type [AnswerCorrection.Type.RIGHT_OR_NOT]
 *
 * @property numOfRights Num of answers replied correctly
 * @property numOfFailures Num of answers replied incorrectly
 */
class NumOfRightsQuestionnaireResult(
    override val questionnaire: Questionnaire,
    override val numOfNotAnswered: Int,
    val numOfRights: Int,
    val numOfFailures: Int,
) : QuestionnaireResult