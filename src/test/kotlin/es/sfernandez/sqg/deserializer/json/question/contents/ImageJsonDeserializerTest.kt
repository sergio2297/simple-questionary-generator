package es.sfernandez.sqg.deserializer.json.question.contents

import es.sfernandez.sqg.BasicFixtures
import es.sfernandez.sqg.deserializer.json.JsonDeserializer
import es.sfernandez.sqg.deserializer.json.JsonFixtures
import es.sfernandez.sqg.deserializer.json.JsonKeys
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ImageJsonDeserializerTest {

    //---- Attributes ----
    private val deserializer = ImageJsonDeserializer()

    //---- Tests ----
    @Test
    fun imageJsonDeserializer_isInstanceOf_JsonDeserializerTest() {
        assertThat(ImageJsonDeserializer()).isInstanceOf(JsonDeserializer::class.java)
    }

    @Test
    fun deserialize_jsonWithoutPath_assignEmptyPathTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        val image = deserializer.deserialize(json)

        assertThat(image.path).isEmpty()
    }

    @Test
    fun deserialize_jsonWithPath_returnPathTest() {
        val path = BasicFixtures.SOME_TEXT_1
        val json = """
            { "${JsonKeys.Image.PATH}": "$path"}
        """.trimIndent()

        val image = deserializer.deserialize(json)

        assertThat(image.path).isEqualTo(path)
    }

    @Test
    fun deserialize_jsonWithoutClickToSee_assignFalseTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        val image = deserializer.deserialize(json)

        assertThat(image.clickToSee).isFalse()
    }

    @ParameterizedTest
    @ValueSource(booleans = [true, false])
    fun deserialize_jsonWithClickToSee_worksTest(clickToSee : Boolean) {
        val json = """
            { "${JsonKeys.Image.CLICK_TO_SEE}": ${clickToSee}}
        """.trimIndent()

        val image = deserializer.deserialize(json)

        assertThat(image.clickToSee).isEqualTo(clickToSee)
    }

}