package es.sfernandez.sqg.deserializer.json.questionnaire.contents

import es.sfernandez.sqg.deserializer.json.JsonDeserializer
import es.sfernandez.sqg.deserializer.json.JsonFixtures
import es.sfernandez.sqg.deserializer.json.JsonKeys
import es.sfernandez.sqg.deserializer.logs.DeserializationLogUtilsForTests
import es.sfernandez.sqg.utilities.Fixtures
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class VideoJsonDeserializerTest {

    //---- Attributes ----
    private val deserializer = VideoJsonDeserializer()

    //---- Tests ----
    @Test
    fun videoJsonDeserializer_isInstanceOf_JsonDeserializerTest() {
        Assertions.assertThat(VideoJsonDeserializer()).isInstanceOf(JsonDeserializer::class.java)
    }

    @Test
    fun deserialize_jsonWithoutPath_assignEmptyPathTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        val video = deserializer.deserialize(json)

        Assertions.assertThat(video.path).isEmpty()
    }

    @Test
    fun deserialize_jsonWithPath_returnPathTest() {
        val path = Fixtures.SOME_TEXT_1
        val json = """
            { "${JsonKeys.Contents.Video.PATH}": "$path"}
        """.trimIndent()

        val video = deserializer.deserialize(json)

        Assertions.assertThat(video.path).isEqualTo(path)
    }

    @Test
    fun deserialize_jsonWithoutAutoplay_assignTrueTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        val video = deserializer.deserialize(json)

        Assertions.assertThat(video.autoplay).isTrue()
    }

    @ParameterizedTest
    @ValueSource(booleans = [true, false])
    fun deserialize_jsonWithAutoplay_worksTest(autoplay : Boolean) {
        val json = """
            { "${JsonKeys.Contents.Video.AUTOPLAY}": ${autoplay}}
        """.trimIndent()

        val video = deserializer.deserialize(json)

        Assertions.assertThat(video.autoplay).isEqualTo(autoplay)
    }

    @Test
    fun logs_afterDeserializeJson_withoutPath_containsWarningMsgTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer, JsonKeys.Contents.Video.PATH)
    }

    @Test
    fun logs_afterDeserializeJson_withIncorrectTypePath_containsWarningMsgTest() {
        val json = """
            { "${JsonKeys.Contents.Video.PATH}": {}}
        """.trimIndent()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer, JsonKeys.Contents.Video.PATH)
    }

    @Test
    fun logs_afterDeserializeJson_withoutAutoplay_containsWarningMsgTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer, JsonKeys.Contents.Video.AUTOPLAY)
    }

    @Test
    fun logs_afterDeserializeJson_withIncorrectTypeAutoplay_containsWarningMsgTest() {
        val json = """
            { "${JsonKeys.Contents.Video.AUTOPLAY}": "true"}
        """.trimIndent()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer, JsonKeys.Contents.Video.AUTOPLAY)
    }
    
}