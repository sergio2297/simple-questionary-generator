package es.sfernandez.sqg.deserializer.json.questionnaire.contents

import es.sfernandez.sqg.beans.contents.Text
import es.sfernandez.sqg.deserializer.json.JsonDeserializer
import es.sfernandez.sqg.deserializer.json.JsonFixtures
import es.sfernandez.sqg.deserializer.json.JsonKeys
import es.sfernandez.sqg.deserializer.logs.DeserializationLogUtilsForTests
import es.sfernandez.sqg.utilities.Fixtures
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class TextJsonDeserializerTest {

    //---- Attributes ----
    private val deserializer = TextJsonDeserializer()

    //---- Tests ----
    @Test
    fun textJsonDeserializer_isInstanceOf_JsonDeserializerTest() {
        assertThat(TextJsonDeserializer()).isInstanceOf(JsonDeserializer::class.java)
    }

    @Test
    fun deserialize_jsonWithoutValue_assignEmptyValueTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        val text = deserializer.deserialize(json)

        assertThat(text.value).isEmpty()
    }

    @Test
    fun deserialize_jsonWithValue_returnValueTest() {
        val value = Fixtures.SOME_TEXT_1
        val json = """
            { "${JsonKeys.Contents.Text.VALUE}": "$value"}
        """.trimIndent()

        val text = deserializer.deserialize(json)

        assertThat(text.value).isEqualTo(value)
    }

    @Test
    fun deserialize_jsonWithoutMarkup_assignSimpleTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT
        
        val text = deserializer.deserialize(json)
        
        assertThat(text.markup).isEqualTo(Text.Markup.SIMPLE)
    }
    
    @ParameterizedTest
    @EnumSource(Text.Markup::class)
    fun deserialize_jsonWithMarkup_workTest(markup : Text.Markup) {
        val json = """
            { "${JsonKeys.Contents.Text.MARKUP}": "${markup.name}"}
        """.trimIndent()

        val text = deserializer.deserialize(json)

        assertThat(text.markup).isEqualTo(markup)
    }

    @Test
    fun logs_afterDeserializeJson_withoutValue_containsWarningMsgTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer, JsonKeys.Contents.Text.VALUE)
    }

    @Test
    fun logs_afterDeserializeJson_withIncorrectTypeValue_containsWarningMsgTest() {
        val json = """
            { "${JsonKeys.Contents.Text.VALUE}": {}}
        """.trimIndent()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer, JsonKeys.Contents.Text.VALUE)
    }

    @Test
    fun logs_afterDeserializeJson_withoutMarkup_containsWarningMsgTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer, JsonKeys.Contents.Text.MARKUP)
    }

    @Test
    fun logs_afterDeserializeJson_withIncorrectTypeMarkup_containsWarningMsgTest() {
        val json = """
            { "${JsonKeys.Contents.Text.MARKUP}": {}}
        """.trimIndent()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer, JsonKeys.Contents.Text.MARKUP)
    }

    @Test
    fun logs_afterDeserializeJson_withUndefinedMarkupConstant_containsWarningMsgTest() {
        val undefinedConstant = Fixtures.SOME_TEXT_1
        val json = """
            { "${JsonKeys.Contents.Text.MARKUP}": "$undefinedConstant"}
        """.trimIndent()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer, JsonKeys.Contents.Text.MARKUP)
    }
    
}