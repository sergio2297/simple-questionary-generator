package es.sfernandez.sqg.deserializer.json.question.contents

import es.sfernandez.sqg.BasicFixtures
import es.sfernandez.sqg.deserializer.json.JsonDeserializer
import es.sfernandez.sqg.deserializer.json.JsonFixtures
import es.sfernandez.sqg.deserializer.json.JsonKeys
import es.sfernandez.sqg.deserializer.logs.DeserializationLog
import es.sfernandez.sqg.model.contents.Text
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class TextJsonDeserializerTest {

    //---- Attributes ----
    private val deserializer = TextJsonDeserializer()

    //---- Methods ----
    private fun checkDeserializerLogsContainsWarningWithKey(key: String) {
        assertThat(deserializer.logs.asSequence()
            .any { log -> log.level == DeserializationLog.Level.WARNING
                    && log.msg.contains(key)})
            .isTrue()
    }
    
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
        val value = BasicFixtures.SOME_TEXT_1
        val json = """
            { "${JsonKeys.Text.VALUE}": "$value"}
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
            { "${JsonKeys.Text.MARKUP}": "${markup.name}"}
        """.trimIndent()

        val text = deserializer.deserialize(json)

        assertThat(text.markup).isEqualTo(markup)
    }

    @Test
    fun logs_afterDeserializeJson_withoutValue_containsWarningMsgTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        deserializer.deserialize(json)

        checkDeserializerLogsContainsWarningWithKey(JsonKeys.Text.VALUE)
    }

    @Test
    fun logs_afterDeserializeJson_withIncorrectTypeValue_containsWarningMsgTest() {
        val json = """
            { "${JsonKeys.Text.VALUE}": {}}
        """.trimIndent()

        deserializer.deserialize(json)

        checkDeserializerLogsContainsWarningWithKey(JsonKeys.Text.VALUE)
    }

    @Test
    fun logs_afterDeserializeJson_withoutMarkup_containsWarningMsgTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        deserializer.deserialize(json)

        checkDeserializerLogsContainsWarningWithKey(JsonKeys.Text.MARKUP)
    }

    @Test
    fun logs_afterDeserializeJson_withIncorrectTypeMarkup_containsWarningMsgTest() {
        val json = """
            { "${JsonKeys.Text.MARKUP}": {}}
        """.trimIndent()

        deserializer.deserialize(json)

        checkDeserializerLogsContainsWarningWithKey(JsonKeys.Text.MARKUP)
    }

    @Test
    fun logs_afterDeserializeJson_withUndefinedMarkupConstant_containsWarningMsgTest() {
        val undefinedConstant = BasicFixtures.SOME_TEXT_1
        val json = """
            { "${JsonKeys.Text.MARKUP}": "$undefinedConstant"}
        """.trimIndent()

        deserializer.deserialize(json)

        checkDeserializerLogsContainsWarningWithKey(JsonKeys.Text.MARKUP)
    }
    
}