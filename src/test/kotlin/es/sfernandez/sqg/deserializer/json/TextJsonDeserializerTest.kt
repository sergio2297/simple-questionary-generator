package es.sfernandez.sqg.deserializer.json

import es.sfernandez.sqg.BasicFixtures
import es.sfernandez.sqg.model.contents.Text
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
    fun deserialize_emptyJson_returnEmptyTextTest() {
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
    fun deserialize_jsonWithoutMarkup_assignSimpleMarkupTest() {
        val defaultMarkup = Text.Markup.SIMPLE
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        val text = deserializer.deserialize(json)

        assertThat(text.markup).isEqualTo(defaultMarkup)
    }

}