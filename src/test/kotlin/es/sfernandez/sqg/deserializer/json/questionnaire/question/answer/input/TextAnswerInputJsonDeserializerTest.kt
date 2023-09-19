package es.sfernandez.sqg.deserializer.json.questionnaire.question.answer.input

import es.sfernandez.sqg.beans.question.answers.input.TextAnswerInput
import es.sfernandez.sqg.deserializer.json.JsonFixtures
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class TextAnswerInputJsonDeserializerTest {

    //---- Attributes ----
    private lateinit var deserializer: TextAnswerInputJsonDeserializer

    //---- Configuration ----
    @BeforeEach
    fun setup() {
        deserializer = TextAnswerInputJsonDeserializer()
    }

    //---- Tests ----
    @Test
    fun deserialize_emptyJsonObject_returnsTextAnswerInputTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        val input = deserializer.deserialize(json)

        assertThat(input).isInstanceOf(TextAnswerInput::class.java)
    }

}