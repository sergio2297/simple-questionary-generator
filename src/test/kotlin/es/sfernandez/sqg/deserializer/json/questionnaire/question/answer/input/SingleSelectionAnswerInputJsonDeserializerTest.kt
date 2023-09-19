package es.sfernandez.sqg.deserializer.json.questionnaire.question.answer.input

import es.sfernandez.sqg.beans.question.answers.input.SingleSelectionAnswerInput
import es.sfernandez.sqg.deserializer.json.JsonFixtures
import es.sfernandez.sqg.deserializer.json.questionnaire.question.ChoiceJsonDeserializer
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class SingleSelectionAnswerInputJsonDeserializerTest : SelectionAnswerInputJsonDeserializerTest() {

    //---- Methods ----
    override fun instantiateDeserializer(): SelectionAnswerInputJsonDeserializer<*> {
        return SingleSelectionAnswerInputJsonDeserializer()
    }

    override fun instantiateDeserializer(choiceDeserializer: ChoiceJsonDeserializer): SelectionAnswerInputJsonDeserializer<*> {
        return SingleSelectionAnswerInputJsonDeserializer(choiceDeserializer)
    }

    //---- Test ----
    @Test
    fun deserialize_returnedInputsIs_instanceOfSingleSelectionAnswerInputTest() {
        deserializer = createNormalDeserializer()
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        val input = deserializer.deserialize(json)

        Assertions.assertThat(input).isInstanceOf(SingleSelectionAnswerInput::class.java)
    }

}