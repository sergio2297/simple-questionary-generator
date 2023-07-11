package es.sfernandez.sqg.deserializer.json.question

import es.sfernandez.sqg.BasicFixtures
import es.sfernandez.sqg.deserializer.json.JsonFixtures
import es.sfernandez.sqg.deserializer.json.JsonKeys
import es.sfernandez.sqg.beans.contents.UnknownContent
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ChoiceJsonDeserializerTest {

    //---- Constants and Definitions ----

    //---- Attributes ----
    private lateinit var deserializer : ChoiceJsonDeserializer

    //---- Fixtures ----

    //---- Configuration ----
    @BeforeEach
    fun setup() {
        deserializer = createDefaultDeserializer()
    }

    private fun createDefaultDeserializer(): ChoiceJsonDeserializer {
        return ChoiceJsonDeserializer()
    }

    //---- Methods ----

    //---- Tests ----
    @Test
    fun deserialize_objectWithoutId_returnsChoiceWithoutIdTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        val choice = deserializer.deserialize(json)

        assertThat(choice.id).isEmpty()
    }

    @Test
    fun deserialize_objectWithId_returnsChoiceWithIdTest() {
        val id = BasicFixtures.SOME_TEXT_1
        val json = """
            {
                "${JsonKeys.Choice.ID}": "$id"
            }"""

        val choice = deserializer.deserialize(json)

        assertThat(choice.id).isEqualTo(id)
    }

    @Test
    fun deserialize_objectWithoutContent_returnsChoiceWithUnknownContentTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        val choice = deserializer.deserialize(json)

        assertThat(choice.content).isInstanceOf(UnknownContent::class.java)
    }

    @Test
    fun deserialize_objectWithTextContent_returnsChoiceWithTextTest() {

    }

    @Test
    fun deserialize_soundWithTextContent_returnsChoiceWithTextTest() {

    }

    @Test
    fun deserialize_imageWithTextContent_returnsChoiceWithTextTest() {

    }

    @Test
    fun deserialize_videoWithTextContent_returnsChoiceWithTextTest() {

    }

}