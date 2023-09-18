package es.sfernandez.sqg.deserializer.json.questionnaire

import es.sfernandez.sqg.beans.contents.Image
import es.sfernandez.sqg.beans.question.Question
import es.sfernandez.sqg.deserializer.json.JsonFixtures
import es.sfernandez.sqg.deserializer.json.JsonKeys
import es.sfernandez.sqg.deserializer.json.questionnaire.contents.ImageJsonDeserializer
import es.sfernandez.sqg.deserializer.json.questionnaire.question.QuestionJsonDeserializer
import es.sfernandez.sqg.deserializer.logs.DeserializationLog
import es.sfernandez.sqg.deserializer.logs.DeserializationLogUtilsForTests
import es.sfernandez.sqg.utilities.Fixtures
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.any
import kotlin.test.BeforeTest

class QuestionnaireJsonDeserializerTest {

    //---- Attributes ----
    private lateinit var deserializer: QuestionnaireJsonDeserializer
    private lateinit var imageDeserializer: ImageJsonDeserializer
    private lateinit var questionDeserializer: QuestionJsonDeserializer

    //---- Fixtures ----
    private val someQuestions = arrayOf(mockQuestion(), mockQuestion())

    //---- Configuration ----
    @BeforeTest
    fun setup() {
        deserializer = createDefaultDeserializer()
    }

    //---- Methods ----
    private fun createDefaultDeserializer(): QuestionnaireJsonDeserializer {
        return QuestionnaireJsonDeserializer()
    }

    private fun createMockedDeserializer() : QuestionnaireJsonDeserializer {
        imageDeserializer = Mockito.mock(ImageJsonDeserializer::class.java)
        Mockito.lenient().`when`(imageDeserializer.logs())
            .thenReturn(arrayOf(Mockito.mock(DeserializationLog::class.java)))

        questionDeserializer = Mockito.mock(QuestionJsonDeserializer::class.java)
        Mockito.lenient().`when`(questionDeserializer.logs())
            .thenReturn(arrayOf(Mockito.mock(DeserializationLog::class.java)))

        return QuestionnaireJsonDeserializer(imageDeserializer, questionDeserializer)
    }

    private fun mockImage() : Image {
        return Mockito.mock(Image::class.java)
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
    fun deserialize_objectWithoutTitle_returnsQuestionnaireWithEmptyTitleTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        val questionnaire = deserializer.deserialize(json)

        assertThat(questionnaire.title).isEmpty()
    }

    @Test
    fun afterDeserialize_objectWithoutTitle_logsHaveWarningTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer, JsonKeys.Questionnaire.TITLE)
    }

    @Test
    fun deserialize_objectWithNotValidTitle_returnsQuestionnaireWithEmptyTitleTest() {
        val json = """
            {
                "${JsonKeys.Questionnaire.TITLE}": 10
            }
        """.trimIndent()

        val questionnaire = deserializer.deserialize(json)

        assertThat(questionnaire.title).isEmpty()
    }

    @Test
    fun afterDeserialize_objectWithNotValidTitle_logsHaveWarningTest() {
        val json = """
            {
                "${JsonKeys.Questionnaire.TITLE}": 10
            }
        """.trimIndent()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer, JsonKeys.Questionnaire.TITLE)
    }

    @Test
    fun deserialize_objectWithTitle_returnsQuestionnaireWithCorrectTitleTest() {
        val title = Fixtures.SOME_TEXT_1
        val json = """
            {
                "${JsonKeys.Questionnaire.TITLE}": "$title"
            }
        """.trimIndent()

        val questionnaire = deserializer.deserialize(json)

        assertThat(questionnaire.title).isEqualTo(title)
    }

    @Test
    fun deserialize_objectWithoutPortrait_returnsQuestionnaireWithEmptyPortraitTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        val questionnaire = deserializer.deserialize(json)

        assertThat(questionnaire.portrait.path).isEmpty()
    }

    @Test
    fun afterDeserialize_objectWithoutPortrait_logsHaveWarningTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer, JsonKeys.Questionnaire.PORTRAIT)
    }

    @Test
    fun deserialize_objectWithNotValidPortrait_returnsQuestionnaireWithEmptyPortraitTest() {
        val json = """
            {
                "${JsonKeys.Questionnaire.PORTRAIT}": []
            }
        """.trimIndent()

        val questionnaire = deserializer.deserialize(json)

        assertThat(questionnaire.portrait.path).isEmpty()
    }

    @Test
    fun afterDeserialize_objectWithNotValidPortrait_logsHaveWarningTest() {
        val json = """
            {
                "${JsonKeys.Questionnaire.PORTRAIT}": []
            }
        """.trimIndent()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer, JsonKeys.Questionnaire.PORTRAIT)
    }

    @Test
    fun deserialize_objectWithPortrait_returnsQuestionnaireWithCorrectPortraitTest() {
        deserializer = createMockedDeserializer()
        val expectedPortrait = mockImage()
        Mockito.`when`(imageDeserializer.deserialize(any())).thenReturn(expectedPortrait)
        val json = """
            {
                "${JsonKeys.Questionnaire.PORTRAIT}": {}
            }
        """.trimIndent()

        val questionnaire = deserializer.deserialize(json)

        assertThat(questionnaire.portrait).isEqualTo(expectedPortrait)
    }

    @Test
    fun deserialize_objectWithPortrait_dumpLogsTest() {
        deserializer = createMockedDeserializer()
        val expectedLogs = mockSomeDeserializationLog()
        Mockito.`when`(imageDeserializer.deserialize(any())).thenReturn(mockImage())
        Mockito.`when`(imageDeserializer.logs()).thenReturn(expectedLogs)
        val json = """
            {
                "${JsonKeys.Questionnaire.PORTRAIT}": {}
            }
        """.trimIndent()

        deserializer.deserialize(json)

        assertThat(deserializer.logs()).contains(*expectedLogs)
    }

    @Test
    fun deserialize_objectWithoutQuestions_returnsQuestionnaireWithEmptyQuestionsTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        val questionnaire = deserializer.deserialize(json)

        assertThat(questionnaire.questions).isEmpty()
    }

    @Test
    fun afterDeserialize_objectWithoutQuestions_logsHaveWarningTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer, JsonKeys.Questionnaire.QUESTIONS)
    }

    @Test
    fun deserialize_objectWithNotValidQuestions_returnsQuestionnaireWithEmptyQuestionsTest() {
        val json = """
            {
                "${JsonKeys.Questionnaire.QUESTIONS}": {}
            }
        """.trimIndent()

        val questionnaire = deserializer.deserialize(json)

        assertThat(questionnaire.questions).isEmpty()
    }

    @Test
    fun afterDeserialize_objectWithNotValidQuestions_logsHaveWarningTest() {
        val json = """
            {
                "${JsonKeys.Questionnaire.QUESTIONS}": {}
            }
        """.trimIndent()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer, JsonKeys.Questionnaire.QUESTIONS)
    }

    @Test
    fun deserialize_objectWithQuestions_returnsAnswerWithQuestionsTest() {
        deserializer = createMockedDeserializer()
        val expectedArray = someQuestions
        Mockito.`when`(questionDeserializer.deserialize(any())).thenReturn(expectedArray[0], expectedArray[1])
        val json = """
            {
                "${JsonKeys.Questionnaire.QUESTIONS}": ["", ""]
            }
        """.trimIndent()

        val questionnaire = deserializer.deserialize(json)

        assertThat(questionnaire.questions).containsExactly(*someQuestions)
    }

    @Test
    fun deserialize_objectWithQuestions_dumpLogsTest() {
        deserializer = createMockedDeserializer()
        val expectedLogs = mockSomeDeserializationLog()
        Mockito.`when`(questionDeserializer.logs()).thenReturn(expectedLogs)
        val json = """
            {
                "${JsonKeys.Questionnaire.QUESTIONS}": []
            }
        """.trimIndent()

        deserializer.deserialize(json)

        assertThat(deserializer.logs()).contains(*expectedLogs)
    }

}
