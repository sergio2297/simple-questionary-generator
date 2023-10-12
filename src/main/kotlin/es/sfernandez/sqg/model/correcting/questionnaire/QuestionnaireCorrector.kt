package es.sfernandez.sqg.model.correcting.questionnaire

import es.sfernandez.sqg.beans.Questionnaire
import es.sfernandez.sqg.beans.question.Question
import es.sfernandez.sqg.model.correcting.replies.Reply
import java.util.*

/**
 * A QuestionnaireCorrector can generate the result of correcting a [Questionnaire] from replies given to its questions
 *
 * @param RESULT [QuestionnaireResult] that the corrector generates
 */
abstract class QuestionnaireCorrector<RESULT: QuestionnaireResult> {

    //---- Attributes ----
    /** True if the corrector is correcting a questionnaire */
    private var correcting = false

    /** Questionnaire that will be corrected */
    protected lateinit var questionnaire: Questionnaire

    /** Map that stores the question's replies */
    protected val questionReplies = mutableMapOf<Question, Reply<*>>()

    //---- Methods ----
    fun reset() {

    }

    fun correct(questionnaire: Questionnaire) {
        this.correcting = true
        this.questionnaire = questionnaire
    }

    /**
     * Register for the given question its reply.
     *
     * If another reply was given before to the same question, old reply will be overwritten.
     *
     * Note that this method doesn't check or ensure if the given reply is accepted by a supposed answer's corrector.
     *
     * @param quest Question replied
     * @param reply Reply given
     * @throws QuestionnaireCorrectingException iff the given question doesn't belong to the corrector's questionnaire
     */
    fun registerReply(quest: Question, reply: Reply<*>) {
        throwExceptionIfNotCorrectingAnything("register a reply")

        if(!questionnaireContains(quest))
            throw QuestionnaireCorrectingException("Error. It's not possible to register a reply for the given question " +
                    "because it doesn't belong to the corrector's questionnaire.")

        storeReply(quest, reply)
    }

    private fun questionnaireContains(quest: Question): Boolean {
        return questionnaire.questions.contains(quest)
    }

    private fun storeReply(quest: Question, reply: Reply<*>) {
        questionReplies[quest] = reply
    }

    /**
     * Search inside the registered replies, the one which was given to the passed question
     *
     * @param quest Question
     * @return the reply registered for the given question, or throws an exception
     * @throws QuestionnaireCorrectingException iff the given question hasn't got a reply registered
     */
    protected fun replyFor(quest: Question) : Reply<*> {
        throwExceptionIfNotCorrectingAnything("get a question's reply")

        return Optional.ofNullable(questionReplies[quest])
            .orElseThrow { QuestionnaireCorrectingException("Error. You're trying to get the registered reply for a " +
                    "question that hasn't been replied.") }
    }

    /**
     * Generates the questionnaire's result using the registered replies
     *
     * @return the result of correcting the questionnaire
     */
    fun generateResult() : RESULT {
        throwExceptionIfNotCorrectingAnything("generate a result")

        return generateResultSafely()
    }

    /**
     * Generates the questionnaire's result using the registered replies
     *
     * When this method is called, it ensures that the corrector is correcting something
     *
     * @return the result of correcting the questionnaire
     */
    protected abstract fun generateResultSafely() : RESULT

    /**
     * @return the amount of questions that haven't got a reply registered
     */
    protected fun countNotAnswered(): Int {
        throwExceptionIfNotCorrectingAnything("count not answered questions")

        return questionnaire.questions.size - questionReplies.size
    }

    private fun throwExceptionIfNotCorrectingAnything(operation: String) {
        if(!correcting)
            throw QuestionnaireCorrectingException("Error. It's not possible to $operation because the corrector " +
                    "isn't correcting any questionnaire.")
    }

}