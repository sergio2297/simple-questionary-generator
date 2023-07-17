package es.sfernandez.sqg.deserializer.json.questionary

import es.sfernandez.sqg.beans.question.Question
import es.sfernandez.sqg.deserializer.json.JsonFixtures
import es.sfernandez.sqg.deserializer.json.JsonKeys
import es.sfernandez.sqg.deserializer.json.questionary.question.QuestionJsonDeserializer
import es.sfernandez.sqg.deserializer.logs.DeserializationLog
import es.sfernandez.sqg.deserializer.logs.DeserializationLogUtilsForTests
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.any
import kotlin.test.BeforeTest

class QuestionaryJsonDeserializerTest {

    //---- Attributes ----
    private lateinit var deserializer: QuestionaryJsonDeserializer
    private lateinit var questionDeserializer: QuestionJsonDeserializer

    //---- Fixtures ----
    private val someQuestions = arrayOf(mockQuestion(), mockQuestion())

    //---- Configuration ----
    @BeforeTest
    fun setup() {
        deserializer = createDefaultDeserializer()
    }

    //---- Methods ----
    private fun createDefaultDeserializer(): QuestionaryJsonDeserializer {
        return QuestionaryJsonDeserializer()
    }

    private fun createMockedDeserializer() : QuestionaryJsonDeserializer {
        questionDeserializer = Mockito.mock(QuestionJsonDeserializer::class.java)
        Mockito.lenient().`when`(questionDeserializer.logs())
            .thenReturn(arrayOf(Mockito.mock(DeserializationLog::class.java)))
        return QuestionaryJsonDeserializer(questionDeserializer)
    }

    private fun mockQuestion() : Question {
        return Mockito.mock(Question::class.java)
    }

    private fun mockSomeDeserializationLog() : Array<DeserializationLog> {
        return generateSequence { Mockito.mock(DeserializationLog::class.java) }
            .take(3).toList().toTypedArray()
    }

    //---- Tests ----
    @Test
    fun deserialize_objectWithoutQuestions_returnsQuestionaryWithEmptyQuestionsTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        val questionary = deserializer.deserialize(json)

        assertThat(questionary.questions).isEmpty()
    }

    @Test
    fun afterDeserialize_objectWithoutQuestions_logsHaveWarningTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer, JsonKeys.Questionary.QUESTIONS)
    }

    @Test
    fun deserialize_objectWithNotValidQuestions_returnsQuestionaryWithEmptyQuestionsTest() {
        val json = """
            {
                "${JsonKeys.Questionary.QUESTIONS}": {}
            }
        """.trimIndent()

        val questionary = deserializer.deserialize(json)

        assertThat(questionary.questions).isEmpty()
    }

    @Test
    fun afterDeserialize_objectWithNotValidQuestions_logsHaveWarningTest() {
        val json = """
            {
                "${JsonKeys.Questionary.QUESTIONS}": {}
            }
        """.trimIndent()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer, JsonKeys.Questionary.QUESTIONS)
    }

    @Test
    fun deserialize_objectWithQuestions_returnsAnswerWithQuestionsTest() {
        deserializer = createMockedDeserializer()
        val expectedArray = someQuestions
        Mockito.`when`(questionDeserializer.deserialize(any())).thenReturn(expectedArray[0], expectedArray[1])
        val json = """
            {
                "${JsonKeys.Questionary.QUESTIONS}": ["", ""]
            }
        """.trimIndent()

        val questionary = deserializer.deserialize(json)

        assertThat(questionary.questions).containsExactly(*someQuestions)
    }

    @Test
    fun deserialize_objectWithQuestions_dumpLogsTest() {
        deserializer = createMockedDeserializer()
        val expectedLogs = mockSomeDeserializationLog()
        Mockito.`when`(questionDeserializer.logs()).thenReturn(expectedLogs)
        val json = """
            {
                "${JsonKeys.Questionary.QUESTIONS}": []
            }
        """.trimIndent()

        deserializer.deserialize(json)

        assertThat(deserializer.logs()).containsExactly(*expectedLogs)
    }

}
