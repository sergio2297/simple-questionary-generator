package es.sfernandez.sqg.deserializer.json.questionary.question

import es.sfernandez.sqg.BasicFixtures
import es.sfernandez.sqg.beans.question.answers.Answer
import es.sfernandez.sqg.beans.question.answers.UnknownAnswer
import es.sfernandez.sqg.beans.question.explanations.Explanation
import es.sfernandez.sqg.beans.question.problems.Problem
import es.sfernandez.sqg.deserializer.json.JsonFixtures
import es.sfernandez.sqg.deserializer.json.JsonKeys
import es.sfernandez.sqg.deserializer.json.questionary.question.answer.AnswerJsonDeserializer
import es.sfernandez.sqg.deserializer.logs.DeserializationLog
import es.sfernandez.sqg.deserializer.logs.DeserializationLogUtilsForTests
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.any

class QuestionJsonDeserializerTest {

    //---- Attributes ----
    private lateinit var deserializer: QuestionJsonDeserializer

    private lateinit var problemJsonDeserializer: ProblemJsonDeserializer
    private lateinit var answerJsonDeserializer: AnswerJsonDeserializer
    private lateinit var explanationJsonDeserializer: ExplanationJsonDeserializer

    //---- Fixtures ----
    private val aProblem = Mockito.mock(Problem::class.java)
    private val anAnswer = Mockito.mock(Answer::class.java)
    private val anExplanation = Mockito.mock(Explanation::class.java)

    //---- Configuration ----
    @BeforeEach
    fun setup() {
        deserializer = createMockedDeserializer()
    }

    //---- Methods ----
    private fun createNormalDeserializer() : QuestionJsonDeserializer {
        return QuestionJsonDeserializer()
    }

    private fun createMockedDeserializer() : QuestionJsonDeserializer {
        problemJsonDeserializer = Mockito.mock(ProblemJsonDeserializer::class.java)
        answerJsonDeserializer = Mockito.mock(AnswerJsonDeserializer::class.java)
        explanationJsonDeserializer = Mockito.mock(ExplanationJsonDeserializer::class.java)
        return QuestionJsonDeserializer(problemJsonDeserializer, answerJsonDeserializer,
            explanationJsonDeserializer)
    }

    private fun mockDeserializersToReturnEmptyLogs() {
        Mockito.lenient().`when`(problemJsonDeserializer.logs()).thenReturn(arrayOf())
        Mockito.lenient().`when`(answerJsonDeserializer.logs()).thenReturn(arrayOf())
        Mockito.lenient().`when`(explanationJsonDeserializer.logs()).thenReturn(arrayOf())
    }

    private fun mockSomeDeserializationLog() : Array<DeserializationLog> {
        return generateSequence { Mockito.mock(DeserializationLog::class.java) }
            .take(3).toList().toTypedArray()
    }

    //---- Tests ----
    @Test
    fun deserialize_objectWithNoTitle_returnQuestionWithEmptyTitleTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT
        deserializer = createNormalDeserializer()

        val question = deserializer.deserialize(json)

        assertThat(question.title).isEmpty()
    }

    @Test
    fun afterDeserialize_objectWithNoTitle_logsHaveWarningTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT
        deserializer = createNormalDeserializer()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer,
            JsonKeys.Question.TITLE)
    }

    @Test
    fun deserialize_objectWithNoValidTypeTitle_returnQuestionWithEmptyTitleTest() {
        val json = """
            {
                "${JsonKeys.Question.TITLE}": 123
            }
        """
        deserializer = createNormalDeserializer()

        val question = deserializer.deserialize(json)

        assertThat(question.title).isEmpty()
    }

    @Test
    fun afterDeserialize_objectWithNoValidTypeTitle_logsHaveWarningTest() {
        val json = """
            {
                "${JsonKeys.Question.TITLE}": 123
            }
        """
        deserializer = createNormalDeserializer()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer,
            JsonKeys.Question.TITLE)
    }

    @Test
    fun deserialize_objectWithTitle_worksTest() {
        val title = BasicFixtures.SOME_TEXT_1
        val json = """
            {
                "${JsonKeys.Question.TITLE}": "$title"
            }
        """
        deserializer = createNormalDeserializer()

        val question = deserializer.deserialize(json)

        assertThat(question.title).isEqualTo(title)
    }

    @Test
    fun deserialize_objectWithNoProblem_returnQuestionWithEmptyProblemTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT
        deserializer = createNormalDeserializer()

        val question = deserializer.deserialize(json)

        assertThat(question.problem.groupOfContents.size()).isZero()
    }

    @Test
    fun afterDeserialize_objectWithNoProblem_logsHaveWarningTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT
        deserializer = createNormalDeserializer()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer,
            JsonKeys.Question.PROBLEM)
    }

    @Test
    fun deserialize_objectWithNoValidTypeProblem_returnQuestionWithEmptyProblemTest() {
        val json = """
            {
                "${JsonKeys.Question.PROBLEM}": "123"
            }
        """
        deserializer = createNormalDeserializer()

        val question = deserializer.deserialize(json)

        assertThat(question.problem.groupOfContents.size()).isZero()
    }

    @Test
    fun afterDeserialize_objectWithNoValidTypeProblem_logsHaveWarningTest() {
        val json = """
            {
                "${JsonKeys.Question.PROBLEM}": "123"
            }
        """
        deserializer = createNormalDeserializer()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer,
            JsonKeys.Question.PROBLEM)
    }

    @Test
    fun deserialize_objectWithProblem_worksTest() {
        val json = """
            {
                "${JsonKeys.Question.PROBLEM}": {}
            }
        """
        mockDeserializersToReturnEmptyLogs()
        Mockito.`when`(problemJsonDeserializer.deserialize(any())).thenReturn(aProblem)

        val question = deserializer.deserialize(json)

        assertThat(question.problem).isEqualTo(aProblem)
    }

    @Test
    fun afterDeserialize_objectWithProblem_logsAreDumpTest() {
        val json = """
            {
                "${JsonKeys.Question.PROBLEM}": {}
            }
        """
        mockDeserializersToReturnEmptyLogs()
        val logs = mockSomeDeserializationLog()
        Mockito.`when`(problemJsonDeserializer.deserialize(any())).thenReturn(aProblem)
        Mockito.`when`(problemJsonDeserializer.logs()).thenReturn(logs)

        deserializer.deserialize(json)

        assertThat(deserializer.logs()).contains(*logs)
    }

    @Test
    fun deserialize_objectWithNoAnswer_returnQuestionWithUnknownAnswerTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT
        deserializer = createNormalDeserializer()

        val question = deserializer.deserialize(json)

        assertThat(question.answer).isInstanceOf(UnknownAnswer::class.java)
    }

    @Test
    fun afterDeserialize_objectWithNoAnswer_logsHaveWarningTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT
        deserializer = createNormalDeserializer()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer,
            JsonKeys.Question.ANSWER)
    }

    @Test
    fun deserialize_objectWithNoValidTypeAnswer_returnQuestionWithUnknownAnswerTest() {
        val json = """
            {
                "${JsonKeys.Question.ANSWER}": []
            }
        """
        deserializer = createNormalDeserializer()

        val question = deserializer.deserialize(json)

        assertThat(question.answer).isInstanceOf(UnknownAnswer::class.java)
    }

    @Test
    fun afterDeserialize_objectWithNoValidTypeAnswer_logsHaveWarningTest() {
        val json = """
            {
                "${JsonKeys.Question.ANSWER}": []
            }
        """
        deserializer = createNormalDeserializer()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer,
            JsonKeys.Question.ANSWER)
    }

    @Test
    fun deserialize_objectWithAnswer_worksTest() {
        val json = """
            {
                "${JsonKeys.Question.ANSWER}": {}
            }
        """
        mockDeserializersToReturnEmptyLogs()
        Mockito.`when`(answerJsonDeserializer.deserialize(any())).thenReturn(anAnswer)

        val question = deserializer.deserialize(json)

        assertThat(question.answer).isEqualTo(anAnswer)
    }

    @Test
    fun afterDeserialize_objectWithAnswer_logsAreDumpTest() {
        val json = """
            {
                "${JsonKeys.Question.ANSWER}": {}
            }
        """
        mockDeserializersToReturnEmptyLogs()
        val logs = mockSomeDeserializationLog()
        Mockito.`when`(answerJsonDeserializer.deserialize(any())).thenReturn(anAnswer)
        Mockito.`when`(answerJsonDeserializer.logs()).thenReturn(logs)

        deserializer.deserialize(json)

        assertThat(deserializer.logs()).contains(*logs)
    }

    @Test
    fun deserialize_objectWithNoExplanation_returnQuestionWithEmptyExplanationTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT
        deserializer = createNormalDeserializer()

        val question = deserializer.deserialize(json)

        assertThat(question.explanation.groupOfContents.size()).isZero()
    }

    @Test
    fun afterDeserialize_objectWithNoExplanation_logsHaveWarningTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT
        deserializer = createNormalDeserializer()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer,
            JsonKeys.Question.EXPLANATION)
    }

    @Test
    fun deserialize_objectWithNoValidTypeExplanation_returnQuestionWithEmptyExplanationTest() {
        val json = """
            {
                "${JsonKeys.Question.EXPLANATION}": "123"
            }
        """
        deserializer = createNormalDeserializer()

        val question = deserializer.deserialize(json)

        assertThat(question.explanation.groupOfContents.size()).isZero()
    }

    @Test
    fun afterDeserialize_objectWithNoValidTypeExplanation_logsHaveWarningTest() {
        val json = """
            {
                "${JsonKeys.Question.EXPLANATION}": "123"
            }
        """
        deserializer = createNormalDeserializer()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer,
            JsonKeys.Question.EXPLANATION)
    }

    @Test
    fun deserialize_objectWithExplanation_worksTest() {
        val json = """
            {
                "${JsonKeys.Question.EXPLANATION}": {}
            }
        """
        mockDeserializersToReturnEmptyLogs()
        Mockito.`when`(explanationJsonDeserializer.deserialize(any())).thenReturn(anExplanation)

        val question = deserializer.deserialize(json)

        assertThat(question.explanation).isEqualTo(anExplanation)
    }

    @Test
    fun afterDeserialize_objectWithExplanation_logsAreDumpTest() {
        val json = """
            {
                "${JsonKeys.Question.EXPLANATION}": {}
            }
        """
        mockDeserializersToReturnEmptyLogs()
        val logs = mockSomeDeserializationLog()
        Mockito.`when`(explanationJsonDeserializer.deserialize(any())).thenReturn(anExplanation)
        Mockito.`when`(explanationJsonDeserializer.logs()).thenReturn(logs)

        deserializer.deserialize(json)

        assertThat(deserializer.logs()).contains(*logs)
    }
}