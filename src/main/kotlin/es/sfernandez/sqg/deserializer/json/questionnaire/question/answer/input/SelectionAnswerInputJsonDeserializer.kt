package es.sfernandez.sqg.deserializer.json.questionnaire.question.answer.input

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import es.sfernandez.sqg.beans.question.answers.input.SelectionAnswerInput
import es.sfernandez.sqg.deserializer.json.JsonDeserializer
import es.sfernandez.sqg.deserializer.json.JsonKeys
import es.sfernandez.sqg.deserializer.json.questionnaire.question.ChoiceJsonDeserializer

internal sealed class SelectionAnswerInputJsonDeserializer<INPUT : SelectionAnswerInput> : JsonDeserializer<INPUT> {

    //---- Attributes ----
    private val choiceDeserializer: ChoiceJsonDeserializer

    //---- Constructor ----
    constructor(clazz: Class<INPUT>) : super(clazz) {
        choiceDeserializer = ChoiceJsonDeserializer()
    }

    constructor(clazz: Class<INPUT>, choiceDeserializer: ChoiceJsonDeserializer) : super(clazz) {
        this.choiceDeserializer = choiceDeserializer
    }

    //---- Methods ----
    override fun createDeserializer(): StdDeserializer<INPUT> {
        return CustomDeserializer()
    }

    protected abstract fun instantiateInput() : INPUT

    private inner class CustomDeserializer : StdDeserializer<INPUT>(mappedClass) {

        override fun deserialize(parser: JsonParser?, ctxt: DeserializationContext?): INPUT {
            val node = extractJsonNode(parser)

            val input = instantiateInput()
            input.possibleChoices = extractArrayOfObjects(node, JsonKeys.Answer.Input.Selection.POSSIBLE_CHOICES, choiceDeserializer)
            return input
        }

    }

}