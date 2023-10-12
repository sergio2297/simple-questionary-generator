package es.sfernandez.sqg.model.correcting.questionnaire.numofrights

import es.sfernandez.sqg.beans.Questionnaire
import es.sfernandez.sqg.beans.question.Question
import es.sfernandez.sqg.beans.question.answers.Answer
import es.sfernandez.sqg.beans.question.answers.correction.rightornot.CorrectValuesRegex
import es.sfernandez.sqg.beans.question.answers.correction.rightornot.PossibleValues
import es.sfernandez.sqg.model.correcting.replies.TextReply
import es.sfernandez.sqg.utilities.Fixtures
import es.sfernandez.sqg.utilities.fixtures.beans.UsesQuestionnaireFixtures
import es.sfernandez.sqg.utilities.fixtures.beans.question.UsesQuestionFixtures
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.BeforeTest

class NumOfRightsQuestionnaireCorrectorTest {

    //---- Attributes ----
    private lateinit var corrector: NumOfRightsQuestionnaireCorrector

    //---- Fixtures ----
    private lateinit var questions: Array<Question>

    //---- Configuration ----
    @BeforeTest
    fun setup() {
        questions = arrayOf(
            questionWithPossibleValuesCorrection("", Fixtures.SOME_TEXT_1, Fixtures.SOME_TEXT_2),
            questionWithPossibleValuesCorrection("", Fixtures.SOME_TEXT_1),
            questionWithRegexValuesCorrection(Regex(".*"))
        )
        corrector = NumOfRightsQuestionnaireCorrector()
        corrector.correct(createQuestionnaire(questions))
    }

    //---- Methods ----
    companion object : UsesQuestionnaireFixtures, UsesQuestionFixtures {

        @JvmStatic
        fun streamOfQuestionnaires(): Stream<Questionnaire> {
            return Stream.of(
                anEmptyQuestionnaire(),
                anEmptyQuestionnaireWith().numOfQuestions(1).build(),
                anEmptyQuestionnaireWith().numOfQuestions(20).build(),
            )
        }

        private fun createQuestionnaire(questions: Array<Question>): Questionnaire {
            val questionnaire = Questionnaire()
            questionnaire.questions = questions
            return questionnaire
        }

        private fun questionWithPossibleValuesCorrection(vararg values: String): Question {
            val answer = Answer()
            answer.correction = PossibleValues(*values)

            val question = Question()
            question.answer = answer

            return question
        }

        private fun questionWithRegexValuesCorrection(regex: Regex): Question {
            val answer = Answer()
            answer.correction = CorrectValuesRegex(regex)

            val question = Question()
            question.answer = answer

            return question
        }

    }

    //---- Tests ----
    @Test
    fun generatedResultQuestionnaire_isSameAs_correctorsQuestionnaireTest() {
        val questionnaire = Questionnaire()
        corrector = NumOfRightsQuestionnaireCorrector()
        corrector.correct(questionnaire)

        val result = corrector.generateResult()

        assertThat(result.questionnaire).isSameAs(questionnaire)
    }

    @ParameterizedTest
    @MethodSource("streamOfQuestionnaires")
    fun generatedResultNumOfQuestions_isSameAs_questionnaireSizeTest(questionnaire: Questionnaire) {
        corrector = NumOfRightsQuestionnaireCorrector()
        corrector.correct(questionnaire)

        val result = corrector.generateResult()

        assertThat(result.numOfQuestions).isEqualTo(questionnaire.questions.size)
    }

    @ParameterizedTest
    @MethodSource("streamOfQuestionnaires")
    fun generatedResult_withNoReplies_hasNotAnsweredAtFullTest(questionnaire: Questionnaire) {
        corrector = NumOfRightsQuestionnaireCorrector()
        corrector.correct(questionnaire)

        val result = corrector.generateResult()

        assertThat(result.numOfNotAnswered).isEqualTo(result.numOfQuestions)
    }

    @Test
    fun generatedResult_withReplies_decreaseNotAnsweredTest() {
        corrector.registerReply(questions[0], TextReply(Fixtures.EMPTY_TEXT))
        corrector.registerReply(questions[1], TextReply(Fixtures.EMPTY_TEXT))
        corrector.registerReply(questions[2], TextReply(Fixtures.SOME_TEXT_1))

        val result = corrector.generateResult()

        assertThat(result.numOfNotAnswered).isEqualTo(0)
    }

    @Test
    fun generatedResult_withRightReplies_countRightsCorrectlyTest() {
        corrector.registerReply(questions[0], TextReply(Fixtures.EMPTY_TEXT))
        corrector.registerReply(questions[1], TextReply(Fixtures.SOME_TEXT_2))
        corrector.registerReply(questions[2], TextReply(Fixtures.SOME_TEXT_1))

        val result = corrector.generateResult()

        assertThat(result.numOfRights).isEqualTo(2)
    }

    @Test
    fun generatedResult_withNoRightReplies_hasZeroRightsCorrectlyTest() {
        corrector.registerReply(questions[0], TextReply(Fixtures.SOME_TEXT_3))
        corrector.registerReply(questions[1], TextReply(Fixtures.SOME_TEXT_3))

        val result = corrector.generateResult()

        assertThat(result.numOfRights).isEqualTo(0)
    }

    @Test
    fun generatedResult_withNotRightReplies_countFailuresCorrectlyTest() {
        corrector.registerReply(questions[0], TextReply(Fixtures.SOME_TEXT_3))
        corrector.registerReply(questions[1], TextReply(Fixtures.SOME_TEXT_3))

        val result = corrector.generateResult()

        assertThat(result.numOfFailures).isEqualTo(2)
    }

    @Test
    fun generatedResult_withNoFailedReplies_hasZeroFailuresCorrectlyTest() {
        corrector.registerReply(questions[0], TextReply(Fixtures.EMPTY_TEXT))
        corrector.registerReply(questions[1], TextReply(Fixtures.EMPTY_TEXT))
        corrector.registerReply(questions[2], TextReply(Fixtures.SOME_TEXT_1))

        val result = corrector.generateResult()

        assertThat(result.numOfFailures).isEqualTo(0)
    }

}