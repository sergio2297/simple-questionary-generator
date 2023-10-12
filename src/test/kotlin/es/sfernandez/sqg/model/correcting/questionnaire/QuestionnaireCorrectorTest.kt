package es.sfernandez.sqg.model.correcting.questionnaire

import es.sfernandez.sqg.beans.Questionnaire
import es.sfernandez.sqg.beans.question.Question
import es.sfernandez.sqg.model.correcting.replies.Reply
import es.sfernandez.sqg.utilities.fixtures.beans.UsesQuestionnaireFixtures
import es.sfernandez.sqg.utilities.fixtures.beans.question.UsesQuestionFixtures
import es.sfernandez.sqg.utilities.fixtures.model.correcting.replies.UsesReplyFixtures
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
    private val questions = someQuestions(10)

    //---- Configuration ----
    @BeforeTest
    fun setup() {
        corrector = createCorrectorWith(anEmptyQuestionnaireWith().questions(*questions).build())
    }

    //---- Methods ----
    companion object : UsesQuestionnaireFixtures, UsesQuestionFixtures, UsesReplyFixtures {
        @JvmStatic
        fun streamOfQuestionnaires(): Stream<Questionnaire> {
            return Stream.of(
                anEmptyQuestionnaire(),
                anEmptyQuestionnaireWith().numOfQuestions(1).build(),
                anEmptyQuestionnaireWith().numOfQuestions(20).build(),
            )
        }

        private fun createCorrectorWith(questionnaire: Questionnaire): FooQuestionnaireCorrector {
            return FooQuestionnaireCorrector(questionnaire)
        }

        private fun numOfQuestionsIn(questionnaire: Questionnaire): Int {
            return questionnaire.questions.size
        }

        private fun registerReplyForQuestions(corrector: QuestionnaireCorrector<*>, questions: Array<Question>) {
            questions.forEach { question -> corrector.registerReply(question, aReply()) }
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
        corrector.correct(aQuestionnaire())

        assertThrows<QuestionnaireCorrectingException> { corrector.correct(aQuestionnaire()) }
    }

    @Test
    fun afterCorrect_correctorIsCorrectingTest() {
        corrector = FooQuestionnaireCorrector()

        corrector.correct(aQuestionnaire())

        assertThat(corrector.isCorrecting()).isTrue()
    }

    @Test
    fun registerReply_whenNoQuestionnaireIsBeingCorrecting_throwsExceptionTest() {
        corrector = FooQuestionnaireCorrector()

        assertThrows<QuestionnaireCorrectingException> { corrector.registerReply(questions[0], aReply()) }
    }

    @Test
    fun registerReply_forUnknownQuestion_throwsExceptionTest() {
        val unknownQuestion = aQuestion()

        assertThrows<QuestionnaireCorrectingException> { corrector.registerReply(unknownQuestion, aReply()) }
    }

    @Test
    fun registerReply_decreaseNotAnsweredQuestionAmountTest() {
        val notAnsweredAtFirst = corrector.testCountNotAnswered()

        corrector.registerReply(questions[0], aReply())

        assertThat(corrector.testCountNotAnswered()).isEqualTo(notAnsweredAtFirst - 1)
    }

    @Test
    fun registeredReply_canBeAccessedTest() {
        val question = questions[0]
        val reply = aReply()
        corrector.registerReply(question, reply)

        val registeredReply = corrector.testReplyFor(question)

        assertThat(registeredReply).isSameAs(reply)
    }

    @Test
    fun registerReplyTwice_overwriteOldReplyTest() {
        val question = questions[0]
        val secondReply = aReply()
        corrector.registerReply(question, aReply())

        corrector.registerReply(question, secondReply)
        val registeredReply = corrector.testReplyFor(question)

        assertThat(registeredReply).isSameAs(secondReply)
    }

    @Test
    fun registerReplyTwice_doesNotDecreaseNotAnsweredQuestionsTest() {
        val question = questions[0]
        corrector.registerReply(question, aReply())
        val notAnsweredAtFirst = corrector.testCountNotAnswered()

        corrector.registerReply(question, aReply())
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