package es.sfernandez.sqg.deserializer.json.questionary.contents

import es.sfernandez.sqg.beans.contents.*
import es.sfernandez.sqg.deserializer.DeserializationException
import es.sfernandez.sqg.deserializer.json.JsonFixtures
import es.sfernandez.sqg.deserializer.logs.DeserializationLog
import es.sfernandez.sqg.deserializer.logs.DeserializationLogUtilsForTests
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import org.mockito.kotlin.any

class GroupOfContentsJsonDeserializerTest {

    //---- Attributes ----
    private var deserializer = GroupOfContentsJsonDeserializer()

    private lateinit var contentJsonDeserializer: ContentJsonDeserializer

    //---- Fixtures ----
    private val deserializedText = Text()
    private val deserializedSound = Sound()
    private val deserializedVideo = Video()
    private val deserializedImage = Image()

    //---- Configuration ----
    @BeforeEach
    fun setup() {
        deserializer = createMockedDeserializer()
    }

    //---- Methods ----
    private fun createNormalDeserializer() : GroupOfContentsJsonDeserializer {
        return GroupOfContentsJsonDeserializer()
    }

    private fun createMockedDeserializer() : GroupOfContentsJsonDeserializer {
        contentJsonDeserializer = Mockito.mock(ContentJsonDeserializer::class.java)
        Mockito.lenient().`when`(contentJsonDeserializer.nodeIsValidContentType(any())).thenReturn(true)
        return GroupOfContentsJsonDeserializer(contentJsonDeserializer)
    }

    private fun mockDeserializerToReturnEmptyLogs() {
        Mockito.lenient().`when`(contentJsonDeserializer.logs()).thenReturn(arrayOf())
    }

    //---- Tests ----
    @Test
    fun deserialize_jsonObject_throwsExceptionTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT
        deserializer = createNormalDeserializer()

        assertThrows<DeserializationException> { deserializer.deserialize(json) }
    }

    @Test
    fun deserialize_emptyJsonArray_returnEmptyContentManagerTest() {
        val json = JsonFixtures.EMPTY_JSON_ARRAY
        deserializer = createNormalDeserializer()

        val content = deserializer.deserialize(json)

        assertThat(content.size()).isZero()
    }

    @Test
    fun deserialize_objectWithNotValidName_isIgnoredTest() {
        val json = """
             [
                 {"not_content_type": {}}
             ]"""
        deserializer = createNormalDeserializer()

        val content = deserializer.deserialize(json)

        assertThat(content.size()).isZero()
    }

    @Test
    fun afterDeserialize_objectWithNotValidName_logsContainWarningTest() {
        val ignoredElements = 1
        val json = """
             [
                 {"not_content_type": {}}
             ]"""
        deserializer = createNormalDeserializer()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer, ignoredElements.toString())
    }

    @Test
    fun deserialize_objectWithUnknownContentType_isIgnoredTest() {
        val json = """
             [
                 {"${ContentType.UNKNOWN.jsonName}": {}}
             ]"""
        deserializer = createNormalDeserializer()

        val content = deserializer.deserialize(json)

        assertThat(content.size()).isZero()
    }

    @Test
    fun afterDeserialize_objectWithUnknownContentType_logsContainWarningTest() {
        val ignoredElements = 1
        val json = """
             [
                 {"${ContentType.UNKNOWN.jsonName}": {}}
             ]"""
        deserializer = createNormalDeserializer()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer, ignoredElements.toString())
    }

    @Test
    fun deserialize_objectWithElementsWithMoreThanOneField_isIgnoredTest() {
        val json = """
             [
                 {
                    "${ContentType.TEXT.jsonName}": {},
                    "${ContentType.IMAGE.jsonName}": {}
                 }
             ]"""
        deserializer = createNormalDeserializer()

        val content = deserializer.deserialize(json)

        assertThat(content.size()).isZero()
    }

    @Test
    fun afterDeserialize_objectWithElementsWithMoreThanOneField_logsContainWarningTest() {
        val ignoredElements = 2
        val json = """
             [
                 {
                    "${ContentType.TEXT.jsonName}": {},
                    "${ContentType.IMAGE.jsonName}": {}
                 },
                 {
                    "${ContentType.IMAGE.jsonName}": {},
                    "${ContentType.TEXT.jsonName}": {}
                 }
             ]"""
        deserializer = createNormalDeserializer()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer, ignoredElements.toString())
    }

    @Test
    fun deserialize_objectWithValidContent_contentManagerContainsContentTest() {
        mockDeserializerToReturnEmptyLogs()
        Mockito.`when`(contentJsonDeserializer.deserialize(Mockito.anyString())).thenReturn(deserializedText)
        val json = """
            [
                {"${ContentType.TEXT.jsonName}": {}}
            ]"""

        val content = deserializer.deserialize(json)

        assertThat(content.size()).isOne()
        assertThat(content.get(0)).isSameAs(deserializedText)
    }

    @Test
    fun deserialize_objectWithMoreThanOneContent_worksTest() {
        mockDeserializerToReturnEmptyLogs()
        Mockito.`when`(contentJsonDeserializer.deserialize(Mockito.anyString()))
            .thenReturn(deserializedText, deserializedSound, deserializedVideo, deserializedImage)
        val json = """
            [
                {"${ContentType.TEXT.jsonName}": {}},
                {"${ContentType.SOUND.jsonName}": {}},
                {"${ContentType.VIDEO.jsonName}": {}},
                {"${ContentType.IMAGE.jsonName}": {}}
            ]"""

        val content = deserializer.deserialize(json)

        assertThat(content.size()).isEqualTo(4)
        assertThat(content.get(0)).isSameAs(deserializedText)
        assertThat(content.get(1)).isSameAs(deserializedSound)
        assertThat(content.get(2)).isSameAs(deserializedVideo)
        assertThat(content.get(3)).isSameAs(deserializedImage)
    }

    @Test
    fun deserialize_objectWithMoreThanOneContentOfTheSameType_worksTest() {
        mockDeserializerToReturnEmptyLogs()
        val deserializedText1 = Text()
        val deserializedText2 = Text()
        Mockito.`when`(contentJsonDeserializer.deserialize(Mockito.anyString()))
            .thenReturn(deserializedText1, deserializedText2)
        val json = """
            [
                {"${ContentType.TEXT.jsonName}": {}},
                {"${ContentType.TEXT.jsonName}": {}}
            ]"""

        val content = deserializer.deserialize(json)

        assertThat(content.size()).isEqualTo(2)
        assertThat(content.get(0)).isSameAs(deserializedText1)
        assertThat(content.get(1)).isSameAs(deserializedText2)
    }

    private fun mockSomeDeserializationLog() : Array<DeserializationLog> {
        return generateSequence { Mockito.mock(DeserializationLog::class.java) }
            .take(3).toList().toTypedArray()
    }

    @Test
    fun afterDeserialize_deserializerContainsLogsProducedBy_usedDeserializerTest() {
        val logs = mockSomeDeserializationLog()
        Mockito.`when`(contentJsonDeserializer.logs()).thenReturn(logs)
        val json = """
            [
                {"${ContentType.TEXT.jsonName}": {}},
                {"${ContentType.IMAGE.jsonName}": {}}
            ]"""

        deserializer.deserialize(json)

        assertThat(deserializer.logs()).contains(*logs)
    }

}