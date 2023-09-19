package es.sfernandez.sqg.deserializer.json.questionnaire.question.answer.input

import es.sfernandez.sqg.beans.question.answers.input.MultipleSelectionAnswerInput
import es.sfernandez.sqg.deserializer.json.JsonFixtures
import es.sfernandez.sqg.deserializer.json.questionnaire.question.ChoiceJsonDeserializer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MultipleSelectionAnswerInputJsonDeserializerTest : SelectionAnswerInputJsonDeserializerTest() {

    //---- Methods ----
    override fun instantiateDeserializer(): SelectionAnswerInputJsonDeserializer<*> {
        return MultipleSelectionAnswerInputJsonDeserializer()
    }

    override fun instantiateDeserializer(choiceDeserializer: ChoiceJsonDeserializer): SelectionAnswerInputJsonDeserializer<*> {
        return MultipleSelectionAnswerInputJsonDeserializer(choiceDeserializer)
    }

    //---- Test ----
    @Test
    fun deserialize_returnedInputsIs_instanceOfMultipleSelectionAnswerInputTest() {
        deserializer = createNormalDeserializer()
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        val input = deserializer.deserialize(json)

        assertThat(input).isInstanceOf(MultipleSelectionAnswerInput::class.java)
    }

}