package es.sfernandez.sqg.deserializer.json.questionnaire.question

import es.sfernandez.sqg.beans.contents.ContentType
import es.sfernandez.sqg.beans.contents.UnknownContent
import es.sfernandez.sqg.beans.question.answers.AnswerFixtures.SOME_IMAGE
import es.sfernandez.sqg.beans.question.answers.AnswerFixtures.SOME_SOUND
import es.sfernandez.sqg.beans.question.answers.AnswerFixtures.SOME_TEXT
import es.sfernandez.sqg.beans.question.answers.AnswerFixtures.SOME_VIDEO
import es.sfernandez.sqg.deserializer.json.JsonFixtures
import es.sfernandez.sqg.deserializer.json.JsonKeys
import es.sfernandez.sqg.deserializer.json.questionnaire.contents.ContentJsonDeserializer
import es.sfernandez.sqg.deserializer.logs.DeserializationLog
import es.sfernandez.sqg.deserializer.logs.DeserializationLogUtilsForTests
import es.sfernandez.sqg.utilities.Fixtures
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito

class ChoiceJsonDeserializerTest {

    //---- Attributes ----
    private lateinit var deserializer : ChoiceJsonDeserializer
    private lateinit var contentDeserializer: ContentJsonDeserializer

    //---- Configuration ----
    @BeforeEach
    fun setup() {
        deserializer = createDefaultDeserializer()
    }

    //---- Methods ----
    private fun createDefaultDeserializer(): ChoiceJsonDeserializer {
        return ChoiceJsonDeserializer()
    }

    private fun createMockedDeserializer() : ChoiceJsonDeserializer {
        contentDeserializer = Mockito.mock(ContentJsonDeserializer::class.java)
        Mockito.lenient().`when`(contentDeserializer.logs())
            .thenReturn(arrayOf(Mockito.mock(DeserializationLog::class.java)))
        return ChoiceJsonDeserializer(contentDeserializer)
    }

    //---- Tests ----
    @Test
    fun deserialize_objectWithoutId_returnsChoiceWithEmptyIdTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        val choice = deserializer.deserialize(json)

        assertThat(choice.id).isEmpty()
    }

    @Test
    fun afterDeserialize_objectWithoutId_logsHaveWarningTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer, JsonKeys.Choice.ID)
    }

    @Test
    fun deserialize_objectWithId_returnsChoiceWithIdTest() {
        val id = Fixtures.SOME_TEXT_1
        val json = """
            {
                "${JsonKeys.Choice.ID}": "$id"
            }"""

        val choice = deserializer.deserialize(json)

        assertThat(choice.id).isEqualTo(id)
    }

    @Test
    fun afterDeserialize_objectWithNotValidId_logsHaveWarningTest() {
        val json = """
            {
                "${JsonKeys.Choice.ID}": 5
            }"""

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer, JsonKeys.Choice.ID)
    }

    @Test
    fun deserialize_objectWithoutContent_returnsChoiceWithUnknownContentTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        val choice = deserializer.deserialize(json)

        assertThat(choice.content).isInstanceOf(UnknownContent::class.java)
    }

    @Test
    fun afterDeserialize_objectWithoutContent_logsHaveWarningTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer, JsonKeys.Choice.CONTENT)
    }

    @Test
    fun deserialize_objectWithNotValidContent_returnsChoiceWithUnknownContentTest() {
        val json = """
            {
                "${JsonKeys.Choice.CONTENT}": {}
            }
        """.trimIndent()

        val choice = deserializer.deserialize(json)

        assertThat(choice.content).isInstanceOf(UnknownContent::class.java)
    }

    @Test
    fun afterDeserialize_objectWithNotValidContent_logsHaveWarningTest() {
        val json = """
            {
                "${JsonKeys.Choice.CONTENT}": {}
            }
        """.trimIndent()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer, JsonKeys.Choice.CONTENT)
    }

    @Test
    fun deserialize_objectWithTextContent_returnsChoiceWithTextTest() {
        deserializer = createMockedDeserializer()
        Mockito.`when`(contentDeserializer.deserialize(anyString())).thenReturn(SOME_TEXT)
        val json = """
            {
                "${JsonKeys.Choice.CONTENT}": {
                    "${ContentType.TEXT.jsonName}": {}
                }
            }
        """.trimIndent()

        val choice = deserializer.deserialize(json)

        assertThat(choice.content).isEqualTo(SOME_TEXT)
    }

    @Test
    fun deserialize_soundWithTextContent_returnsChoiceWithTextTest() {
        deserializer = createMockedDeserializer()
        Mockito.`when`(contentDeserializer.deserialize(anyString())).thenReturn(SOME_SOUND)
        val json = """
            {
                "${JsonKeys.Choice.CONTENT}": {
                    "${ContentType.SOUND.jsonName}": {}
                }
            }
        """.trimIndent()

        val choice = deserializer.deserialize(json)

        assertThat(choice.content).isEqualTo(SOME_SOUND)
    }

    @Test
    fun deserialize_imageWithTextContent_returnsChoiceWithTextTest() {
        deserializer = createMockedDeserializer()
        Mockito.`when`(contentDeserializer.deserialize(anyString())).thenReturn(SOME_IMAGE)
        val json = """
            {
                "${JsonKeys.Choice.CONTENT}": {
                    "${ContentType.IMAGE.jsonName}": {}
                }
            }
        """.trimIndent()

        val choice = deserializer.deserialize(json)

        assertThat(choice.content).isEqualTo(SOME_IMAGE)
    }

    @Test
    fun deserialize_videoWithTextContent_returnsChoiceWithTextTest() {
        deserializer = createMockedDeserializer()
        Mockito.`when`(contentDeserializer.deserialize(anyString())).thenReturn(SOME_VIDEO)
        val json = """
            {
                "${JsonKeys.Choice.CONTENT}": {
                    "${ContentType.VIDEO.jsonName}": {}
                }
            }
        """.trimIndent()

        val choice = deserializer.deserialize(json)

        assertThat(choice.content).isEqualTo(SOME_VIDEO)
    }

}