package es.sfernandez.sqg.model.correcting.questionnaire.numofrights

import es.sfernandez.sqg.beans.Questionnaire
import es.sfernandez.sqg.beans.question.Question
import es.sfernandez.sqg.beans.question.answers.Answer
import es.sfernandez.sqg.beans.question.answers.correction.AnswerCorrection
import es.sfernandez.sqg.model.correcting.answer.correctors.AnswerCorrector
import es.sfernandez.sqg.model.correcting.answer.seekers.RightOrNotCorrectorSeeker
import es.sfernandez.sqg.model.correcting.questionnaire.QuestionnaireCorrector
import es.sfernandez.sqg.model.correcting.replies.Reply

/**
 * A NumOfRightsQuestionnaireCorrector is a [QuestionnaireCorrector] that generates a [NumOfRightsQuestionnaireResult] as
 * result of correcting a [Questionnaire] from replies given to its questions.
 *
 * All questionnaire's questions must have answers with [AnswerCorrection.Type.RIGHT_OR_NOT] type. If not, the corrector
 * wouldn't be able to find an apropiate [AnswerCorrector] and an exception will be thrown
 *
 */
class NumOfRightsQuestionnaireCorrector(
    questionnaire: Questionnaire,
) : QuestionnaireCorrector<NumOfRightsQuestionnaireResult>() {

    //---- Attributes ----
    private val answerCorrectorSeeker = RightOrNotCorrectorSeeker()

    //---- Constructor ----
    init {
        correct(questionnaire)
    }

    //---- Methods ----
    override fun generateResult(): NumOfRightsQuestionnaireResult {
        val questionResults = correctQuestions()

        return NumOfRightsQuestionnaireResult(
            questionnaire,
            countNotAnswered(),
            countRights(questionResults),
            countFailures(questionResults),
        )
    }

    private fun correctQuestions(): List<RightOrNotQuestionResult> {
        return questionReplies.keys.map(this::correctQuestion).toList()
    }

    private fun correctQuestion(question: Question) : RightOrNotQuestionResult {
        val answer = question.answer
        val reply = replyFor(question)
        val isRight = correctAnswer(answer, reply)

        return RightOrNotQuestionResult(question, reply, isRight)
    }

    private fun correctAnswer(answer: Answer, reply: Reply<*>): Boolean {
        return answerCorrectorSeeker.correctorFor(answer)
            .correct(answer.correction, reply)
    }

    private fun countRights(questionResults: List<RightOrNotQuestionResult>): Int {
        return questionResults.count(RightOrNotQuestionResult::isRight)
    }

    private fun countFailures(questionResults: List<RightOrNotQuestionResult>): Int {
        return questionResults.count { result -> !result.isRight }
    }

}