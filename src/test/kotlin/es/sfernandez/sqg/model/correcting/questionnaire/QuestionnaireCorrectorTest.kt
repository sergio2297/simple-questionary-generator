package es.sfernandez.sqg.model.correcting.questionnaire

import es.sfernandez.sqg.beans.Questionnaire
import es.sfernandez.sqg.beans.question.Question
import es.sfernandez.sqg.model.correcting.replies.Reply
import es.sfernandez.sqg.utilities.mocking.MocksQuestion
import es.sfernandez.sqg.utilities.mocking.MocksQuestionnaire
import es.sfernandez.sqg.utilities.mocking.MocksReply
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.fail

class QuestionnaireCorrectorTest {

    //---- Constants and Definitions ----
    private class FooQuestionnaireCorrector : QuestionnaireCorrector<QuestionnaireResult> {

        //---- Constructor ----
        constructor() : super()

        constructor(questionnaire: Questionnaire) : this() {
            correct(questionnaire)
        }

        override fun generateResultSafely(): QuestionnaireResult {
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
    private val questions = mockQuestions(10)

    //---- Configuration ----
    @BeforeTest
    fun setup() {
        corrector = FooQuestionnaireCorrector(mockQuestionnaireWith(questions))
    }

    //---- Methods ----
    companion object : MocksQuestionnaire, MocksQuestion, MocksReply {
        @JvmStatic
        fun streamOfQuestionnaires(): Stream<Questionnaire> {
            return Stream.of(
                mockQuestionnaireWith(arrayOf()),
                mockQuestionnaireWith(mockQuestions(1)),
                mockQuestionnaireWith(mockQuestions(20)),
            )
        }

        private fun createCorrectorWith(questionnaire: Questionnaire): FooQuestionnaireCorrector {
            return FooQuestionnaireCorrector(questionnaire)
        }

        private fun numOfQuestionsIn(questionnaire: Questionnaire): Int {
            return questionnaire.questions.size
        }

        private fun registerReplyForQuestions(corrector: QuestionnaireCorrector<*>, questions: Array<Question>) {
            questions.forEach { question -> corrector.registerReply(question, mockReply()) }
        }

    }

    //---- Tests ----
    @Test
    fun afterReset_correctorIsNotCorrectingTest() {
        corrector.reset()

        assertThat(corrector.isCorrecting()).isFalse()
    }

    @Test
    fun correct_whenCorrectorsIsAlreadyCorrecting_throwsExceptionTest() {
        corrector = FooQuestionnaireCorrector()
        corrector.correct(mockQuestionnaire())

        assertThrows<QuestionnaireCorrectingException> { corrector.correct(mockQuestionnaire()) }
    }

    @Test
    fun afterCorrect_correctorIsCorrectingTest() {
        corrector = FooQuestionnaireCorrector()

        corrector.correct(mockQuestionnaire())

        assertThat(corrector.isCorrecting()).isTrue()
    }

    @Test
    fun registerReply_whenNoQuestionnaireIsBeingCorrecting_throwsExceptionTest() {
        corrector = FooQuestionnaireCorrector()

        assertThrows<QuestionnaireCorrectingException> { corrector.registerReply(questions[0], mockReply()) }
    }

    @Test
    fun registerReply_forUnknownQuestion_throwsExceptionTest() {
        val unknownQuestion = aQuestion()

        assertThrows<QuestionnaireCorrectingException> { corrector.registerReply(unknownQuestion, mockReply()) }
    }

    @Test
    fun registerReply_decreaseNotAnsweredQuestionAmountTest() {
        val notAnsweredAtFirst = corrector.testCountNotAnswered()

        corrector.registerReply(questions[0], mockReply())

        assertThat(corrector.testCountNotAnswered()).isEqualTo(notAnsweredAtFirst - 1)
    }

    @Test
    fun registeredReply_canBeAccessedTest() {
        val question = questions[0]
        val reply = mockReply()
        corrector.registerReply(question, reply)

        val registeredReply = corrector.testReplyFor(question)

        assertThat(registeredReply).isSameAs(reply)
    }

    @Test
    fun registerReplyTwice_overwriteOldReplyTest() {
        val question = questions[0]
        val secondReply = mockReply()
        corrector.registerReply(question, mockReply())

        corrector.registerReply(question, secondReply)
        val registeredReply = corrector.testReplyFor(question)

        assertThat(registeredReply).isSameAs(secondReply)
    }

    @Test
    fun registerReplyTwice_doesNotDecreaseNotAnsweredQuestionsTest() {
        val question = questions[0]
        corrector.registerReply(question, mockReply())
        val notAnsweredAtFirst = corrector.testCountNotAnswered()

        corrector.registerReply(question, mockReply())
        val notAnswered = corrector.testCountNotAnswered()

        assertThat(notAnswered).isEqualTo(notAnsweredAtFirst)
    }

    @Test
    fun replyFor_whenNoQuestionnaireIsBeingCorrecting_throwsExceptionTest() {
        corrector = FooQuestionnaireCorrector()

        assertThrows<QuestionnaireCorrectingException> { corrector.testReplyFor(questions[0]) }
    }

    @Test
    fun replyFor_notAnsweredQuestion_throwsExceptionTest() {
        val notAnsweredQuestion = questions[0]

        assertThrows<QuestionnaireCorrectingException> { corrector.testReplyFor(notAnsweredQuestion) }
    }

    @Test
    fun replyFor_unknownQuestion_throwsExceptionTest() {
        val unknownQuestion = aQuestion()

        assertThrows<QuestionnaireCorrectingException> { corrector.testReplyFor(unknownQuestion) }
    }

    @Test
    fun countNotAnswered_whenNoQuestionnaireIsBeingCorrecting_throwsExceptionTest() {
        corrector = FooQuestionnaireCorrector()

        assertThrows<QuestionnaireCorrectingException> { corrector.testCountNotAnswered() }
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

    @Test
    fun generateResult_whenNoQuestionnaireIsBeingCorrecting_throwsExceptionTest() {
        corrector = FooQuestionnaireCorrector()

        assertThrows<QuestionnaireCorrectingException> { corrector.generateResult() }
    }

}