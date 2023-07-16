package es.sfernandez.sqg.deserializer.json.questionary.contents

import es.sfernandez.sqg.beans.contents.*
import es.sfernandez.sqg.beans.question.answers.AnswerFixtures.SOME_IMAGE
import es.sfernandez.sqg.beans.question.answers.AnswerFixtures.SOME_SOUND
import es.sfernandez.sqg.beans.question.answers.AnswerFixtures.SOME_TEXT
import es.sfernandez.sqg.beans.question.answers.AnswerFixtures.SOME_VIDEO
import es.sfernandez.sqg.deserializer.json.JsonFixtures
import es.sfernandez.sqg.deserializer.logs.DeserializationLog
import es.sfernandez.sqg.deserializer.logs.DeserializationLogUtilsForTests
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class ContentJsonDeserializerTest {

    //---- Attributes ----
    private var deserializer = ContentJsonDeserializer()

    private lateinit var textJsonDeserializer: TextJsonDeserializer
    private lateinit var soundJsonDeserializer: SoundJsonDeserializer
    private lateinit var videoJsonDeserializer: VideoJsonDeserializer
    private lateinit var imageJsonDeserializer: ImageJsonDeserializer

    //---- Configuration ----
    @BeforeEach
    fun setup() {
        deserializer = createMockedDeserializer()
    }

    //---- Methods ----
    private fun createNormalDeserializer() : ContentJsonDeserializer {
        return ContentJsonDeserializer()
    }

    private fun createMockedDeserializer() : ContentJsonDeserializer {
        textJsonDeserializer = Mockito.mock(TextJsonDeserializer::class.java)
        soundJsonDeserializer = Mockito.mock(SoundJsonDeserializer::class.java)
        videoJsonDeserializer = Mockito.mock(VideoJsonDeserializer::class.java)
        imageJsonDeserializer = Mockito.mock(ImageJsonDeserializer::class.java)
        return ContentJsonDeserializer(textJsonDeserializer, soundJsonDeserializer,
            videoJsonDeserializer, imageJsonDeserializer)
    }

    private fun mockDeserializersToReturnEmptyLogs() {
        Mockito.lenient().`when`(textJsonDeserializer.logs()).thenReturn(arrayOf())
        Mockito.lenient().`when`(soundJsonDeserializer.logs()).thenReturn(arrayOf())
        Mockito.lenient().`when`(videoJsonDeserializer.logs()).thenReturn(arrayOf())
        Mockito.lenient().`when`(imageJsonDeserializer.logs()).thenReturn(arrayOf())
    }

    //---- Tests ----
    @Test
    fun deserialize_notJsonObject_returnUnknownContentTest() {
        val json = JsonFixtures.EMPTY_JSON_ARRAY
        deserializer = createNormalDeserializer()

        val content = deserializer.deserialize(json)

        assertThat(content).isInstanceOf(UnknownContent::class.java)
    }

    @Test
    fun afterDeserialize_notJsonObject_logsHaveWarningTest() {
        val json = JsonFixtures.EMPTY_JSON_ARRAY
        deserializer = createNormalDeserializer()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(
            deserializer, ContentJsonDeserializer::class.java.simpleName)
    }

    @Test
    fun deserialize_objectWithNotValidName_returnUnknownContentTest() {
        val json = """
             {
                 "not_content_type": {}
             }"""
        deserializer = createNormalDeserializer()

        val content = deserializer.deserialize(json)

        assertThat(content).isInstanceOf(UnknownContent::class.java)
    }

    @Test
    fun afterDeserialize_objectWithNotValidName_logsHaveWarningTest() {
        val json = """
             {
                 "not_content_type": {}
             }"""
        deserializer = createNormalDeserializer()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(
            deserializer, ContentJsonDeserializer::class.java.simpleName)
    }

    @Test
    fun deserialize_objectWithUnknownContentType_returnUnknownContentTest() {
        val json = """
             {
                "${ContentType.UNKNOWN.jsonName}": {}
             }
             """
        deserializer = createNormalDeserializer()

        val content = deserializer.deserialize(json)

        assertThat(content).isInstanceOf(UnknownContent::class.java)
    }

    @Test
    fun afterDeserialize_objectWithUnknownContentType_logsContainWarningTest() {
        val json = """
             {
                "${ContentType.UNKNOWN.jsonName}": {}
             }
             """
        deserializer = createNormalDeserializer()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(
            deserializer, ContentJsonDeserializer::class.java.simpleName)
    }

    @Test
    fun deserialize_objectWithMoreThanOneField_returnUnknownContentTest() {
        val json = """
             {
                "${ContentType.TEXT.jsonName}": {},
                "${ContentType.IMAGE.jsonName}": {}
             }
             """
        deserializer = createNormalDeserializer()

        val content = deserializer.deserialize(json)

        assertThat(content).isInstanceOf(UnknownContent::class.java)
    }

    @Test
    fun afterDeserialize_objectWithMoreThanOneField_logsContainWarningTest() {
        val json = """
             {
                "${ContentType.TEXT.jsonName}": {},
                "${ContentType.IMAGE.jsonName}": {}
             }
             """
        deserializer = createNormalDeserializer()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(
            deserializer, ContentJsonDeserializer::class.java.simpleName)
    }

    @Test
    fun deserialize_objectWithTextContent_returnTextTest() {
        mockDeserializersToReturnEmptyLogs()
        Mockito.`when`(textJsonDeserializer.deserialize(Mockito.anyString())).thenReturn(SOME_TEXT)
        val json = """
            {
                "${ContentType.TEXT.jsonName}": {}
            }
            """

        val content = deserializer.deserialize(json)

        assertThat(content).isEqualTo(SOME_TEXT)
    }

    @Test
    fun deserialize_objectWithSoundContent_returnSoundTest() {
        mockDeserializersToReturnEmptyLogs()
        Mockito.`when`(soundJsonDeserializer.deserialize(Mockito.anyString())).thenReturn(SOME_SOUND)
        val json = """
            {
                "${ContentType.SOUND.jsonName}": {}
            }
            """

        val content = deserializer.deserialize(json)

        assertThat(content).isEqualTo(SOME_SOUND)
    }

    @Test
    fun deserialize_objectWithVideoContent_returnVideoTest() {
        mockDeserializersToReturnEmptyLogs()
        Mockito.`when`(videoJsonDeserializer.deserialize(Mockito.anyString())).thenReturn(SOME_VIDEO)
        val json = """
            {
                "${ContentType.VIDEO.jsonName}": {}
            }
            """

        val content = deserializer.deserialize(json)

        assertThat(content).isEqualTo(SOME_VIDEO)
    }

    @Test
    fun deserialize_objectWithImageContent_returnImageTest() {
        mockDeserializersToReturnEmptyLogs()
        Mockito.`when`(imageJsonDeserializer.deserialize(Mockito.anyString())).thenReturn(SOME_IMAGE)
        val json = """
            {
                "${ContentType.IMAGE.jsonName}": {}
            }
            """

        val content = deserializer.deserialize(json)

        assertThat(content).isEqualTo(SOME_IMAGE)
    }

    private fun mockSomeDeserializationLog() : Array<DeserializationLog> {
        return generateSequence { Mockito.mock(DeserializationLog::class.java) }
            .take(3).toList().toTypedArray()
    }

    @Test
    fun afterDeserialize_deserializerContainsLogsProducedBy_usedDeserializerTest() {
        val textLogs = mockSomeDeserializationLog()
        Mockito.`when`(textJsonDeserializer.logs()).thenReturn(textLogs)

        val json = """
            {
                "${ContentType.TEXT.jsonName}": {}
            }"""

        deserializer.deserialize(json)

        assertThat(deserializer.logs()).contains(*textLogs)
    }

}