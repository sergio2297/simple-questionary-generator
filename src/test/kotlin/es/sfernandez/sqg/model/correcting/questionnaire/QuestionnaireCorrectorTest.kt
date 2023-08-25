package es.sfernandez.sqg.model.correcting.questionnaire

import es.sfernandez.sqg.beans.Questionnaire
import es.sfernandez.sqg.beans.question.Question
import es.sfernandez.sqg.model.correcting.replies.Reply
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.Mockito
import java.util.stream.Stream
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.fail

class QuestionnaireCorrectorTest {

    //---- Constants and Definitions ----
    private class FooQuestionnaireCorrector(
        questionnaire: Questionnaire
    ) : QuestionnaireCorrector<QuestionnaireResult>(questionnaire) {

        override fun generateResult(): QuestionnaireResult {
            fail("Error. This method shouldn't be tested")
        }

        fun testReplyFor(quest: Question) : Reply<*> {
            return super.replyFor(quest)
        }

        fun testCountNotAnswered(): Int {
            return super.countNotAnswered()
        }

    }

    //---- Attributes ----
    private lateinit var corrector: FooQuestionnaireCorrector

    //---- Fixtures ----
    private val questions = createQuestions(10)

    //---- Configuration ----
    @BeforeTest
    fun setup() {
        corrector = FooQuestionnaireCorrector(createQuestionnaireWith(questions))
    }

    //---- Methods ----
    companion object {
        @JvmStatic
        fun streamOfQuestionnaires(): Stream<Questionnaire> {
            return Stream.of(
                createQuestionnaireWith(arrayOf()),
                createQuestionnaireWith(createQuestions(1)),
                createQuestionnaireWith(createQuestions(20)),
            )
        }

        private fun createCorrectorWith(questionnaire: Questionnaire): FooQuestionnaireCorrector {
            return FooQuestionnaireCorrector(questionnaire)
        }

        private fun createQuestionnaireWith(questions: Array<Question>): Questionnaire {
            val questionnaire = Mockito.mock(Questionnaire::class.java)
            Mockito.`when`(questionnaire.questions).thenReturn(questions)
            return questionnaire
        }

        private fun createQuestion(): Question {
            return Mockito.mock(Question::class.java)
        }

        private fun createQuestions(numOfQuestions: Int): Array<Question> {
            return generateSequence(0) { it + 1 }
                .take(numOfQuestions)
                .map { _ -> createQuestion() }
                .toList()
                .toTypedArray()
        }

        private fun createReply(): Reply<*> {
            return Mockito.mock(Reply::class.java)
        }

        private fun numOfQuestionsIn(questionnaire: Questionnaire): Int {
            return questionnaire.questions.size
        }

        private fun registerReplyForQuestions(corrector: QuestionnaireCorrector<*>, questions: Array<Question>) {
            questions.forEach { question -> corrector.registerReply(question, createReply()) }
        }

    }

    //---- Tests ----
    @Test
    fun registerReply_forUnknownQuestion_throwsExceptionTest() {
        val unknownQuestion = createQuestion()

        assertThrows<QuestionnaireCorrectingException> { corrector.registerReply(unknownQuestion, createReply()) }
    }

    @Test
    fun registerReply_decreaseNotAnsweredQuestionAmountTest() {
        val notAnsweredAtFirst = corrector.testCountNotAnswered()

        corrector.registerReply(questions[0], createReply())

        assertThat(corrector.testCountNotAnswered()).isEqualTo(notAnsweredAtFirst - 1)
    }

    @Test
    fun registeredReply_canBeAccessedTest() {
        val question = questions[0]
        val reply = createReply()
        corrector.registerReply(question, reply)

        val registeredReply = corrector.testReplyFor(question)

        assertThat(registeredReply).isSameAs(reply)
    }

    @Test
    fun registerReplyTwice_overwriteOldReplyTest() {
        val question = questions[0]
        val secondReply = createReply()
        corrector.registerReply(question, createReply())

        corrector.registerReply(question, secondReply)
        val registeredReply = corrector.testReplyFor(question)

        assertThat(registeredReply).isSameAs(secondReply)
    }

    @Test
    fun registerReplyTwice_doesNotDecreaseNotAnsweredQuestionsTest() {
        val question = questions[0]
        corrector.registerReply(question, createReply())
        val notAnsweredAtFirst = corrector.testCountNotAnswered()

        corrector.registerReply(question, createReply())
        val notAnswered = corrector.testCountNotAnswered()

        assertThat(notAnswered).isEqualTo(notAnsweredAtFirst)
    }

    @Test
    fun replyFor_notAnsweredQuestion_throwsExceptionTest() {
        val notAnsweredQuestion = questions[0]

        assertThrows<QuestionnaireCorrectingException> { corrector.testReplyFor(notAnsweredQuestion) }
    }

    @Test
    fun replyFor_unknownQuestion_throwsExceptionTest() {
        val unknownQuestion = createQuestion()

        assertThrows<QuestionnaireCorrectingException> { corrector.testReplyFor(unknownQuestion) }
    }

    @ParameterizedTest
    @MethodSource("streamOfQuestionnaires")
    fun countNotAnswered_atFirstReturns_questionnaireNumOfQuestionsTest(questionnaire: Questionnaire) {
        corrector = createCorrectorWith(questionnaire)

        val notAnswered = corrector.testCountNotAnswered()

        assertThat(notAnswered).isEqualTo(numOfQuestionsIn(questionnaire))
    }

    @ParameterizedTest
    @MethodSource("streamOfQuestionnaires")
    fun countNotAnswered_returnsZeroIf_allQuestionHasReplyTest(questionnaire: Questionnaire) {
        corrector = createCorrectorWith(questionnaire)
        registerReplyForQuestions(corrector, questionnaire.questions)

        val notAnswered = corrector.testCountNotAnswered()

        assertThat(notAnswered).isZero()
    }

}