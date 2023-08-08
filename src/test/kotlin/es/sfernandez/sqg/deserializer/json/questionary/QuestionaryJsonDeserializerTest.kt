package es.sfernandez.sqg.deserializer.json.questionary

import es.sfernandez.sqg.BasicFixtures
import es.sfernandez.sqg.beans.contents.Image
import es.sfernandez.sqg.beans.question.Question
import es.sfernandez.sqg.deserializer.json.JsonFixtures
import es.sfernandez.sqg.deserializer.json.JsonKeys
import es.sfernandez.sqg.deserializer.json.questionary.contents.ImageJsonDeserializer
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
    private fun createDefaultDeserializer(): QuestionaryJsonDeserializer {
        return QuestionaryJsonDeserializer()
    }

    private fun createMockedDeserializer() : QuestionaryJsonDeserializer {
        imageDeserializer = Mockito.mock(ImageJsonDeserializer::class.java)
        Mockito.lenient().`when`(imageDeserializer.logs())
            .thenReturn(arrayOf(Mockito.mock(DeserializationLog::class.java)))

        questionDeserializer = Mockito.mock(QuestionJsonDeserializer::class.java)
        Mockito.lenient().`when`(questionDeserializer.logs())
            .thenReturn(arrayOf(Mockito.mock(DeserializationLog::class.java)))

        return QuestionaryJsonDeserializer(imageDeserializer, questionDeserializer)
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
    fun deserialize_objectWithoutTitle_returnsQuestionaryWithEmptyTitleTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        val questionary = deserializer.deserialize(json)

        assertThat(questionary.title).isEmpty()
    }

    @Test
    fun afterDeserialize_objectWithoutTitle_logsHaveWarningTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer, JsonKeys.Questionary.TITLE)
    }

    @Test
    fun deserialize_objectWithNotValidTitle_returnsQuestionaryWithEmptyTitleTest() {
        val json = """
            {
                "${JsonKeys.Questionary.TITLE}": 10
            }
        """.trimIndent()

        val questionary = deserializer.deserialize(json)

        assertThat(questionary.title).isEmpty()
    }

    @Test
    fun afterDeserialize_objectWithNotValidTitle_logsHaveWarningTest() {
        val json = """
            {
                "${JsonKeys.Questionary.TITLE}": 10
            }
        """.trimIndent()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer, JsonKeys.Questionary.TITLE)
    }

    @Test
    fun deserialize_objectWithTitle_returnsQuestionaryWithCorrectTitleTest() {
        val title = BasicFixtures.SOME_TEXT_1
        val json = """
            {
                "${JsonKeys.Questionary.TITLE}": "$title"
            }
        """.trimIndent()

        val questionary = deserializer.deserialize(json)

        assertThat(questionary.title).isEqualTo(title)
    }

    @Test
    fun deserialize_objectWithoutPortrait_returnsQuestionaryWithEmptyPortraitTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        val questionary = deserializer.deserialize(json)

        assertThat(questionary.portrait.path).isEmpty()
    }

    @Test
    fun afterDeserialize_objectWithoutPortrait_logsHaveWarningTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer, JsonKeys.Questionary.PORTRAIT)
    }

    @Test
    fun deserialize_objectWithNotValidPortrait_returnsQuestionaryWithEmptyPortraitTest() {
        val json = """
            {
                "${JsonKeys.Questionary.PORTRAIT}": []
            }
        """.trimIndent()

        val questionary = deserializer.deserialize(json)

        assertThat(questionary.portrait.path).isEmpty()
    }

    @Test
    fun afterDeserialize_objectWithNotValidPortrait_logsHaveWarningTest() {
        val json = """
            {
                "${JsonKeys.Questionary.PORTRAIT}": []
            }
        """.trimIndent()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer, JsonKeys.Questionary.PORTRAIT)
    }

    @Test
    fun deserialize_objectWithPortrait_returnsQuestionaryWithCorrectPortraitTest() {
        deserializer = createMockedDeserializer()
        val expectedPortrait = mockImage()
        Mockito.`when`(imageDeserializer.deserialize(any())).thenReturn(expectedPortrait)
        val json = """
            {
                "${JsonKeys.Questionary.PORTRAIT}": {}
            }
        """.trimIndent()

        val questionary = deserializer.deserialize(json)

        assertThat(questionary.portrait).isEqualTo(expectedPortrait)
    }

    @Test
    fun deserialize_objectWithPortrait_dumpLogsTest() {
        deserializer = createMockedDeserializer()
        val expectedLogs = mockSomeDeserializationLog()
        Mockito.`when`(imageDeserializer.deserialize(any())).thenReturn(mockImage())
        Mockito.`when`(imageDeserializer.logs()).thenReturn(expectedLogs)
        val json = """
            {
                "${JsonKeys.Questionary.PORTRAIT}": {}
            }
        """.trimIndent()

        deserializer.deserialize(json)

        assertThat(deserializer.logs()).contains(*expectedLogs)
    }

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

        assertThat(deserializer.logs()).contains(*expectedLogs)
    }

}
