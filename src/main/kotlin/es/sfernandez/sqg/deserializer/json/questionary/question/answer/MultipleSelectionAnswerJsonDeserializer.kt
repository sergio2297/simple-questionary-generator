package es.sfernandez.sqg.deserializer.json.questionary.question.answer

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import es.sfernandez.sqg.beans.question.answers.MultipleSelectionAnswer
import es.sfernandez.sqg.deserializer.json.JsonDeserializer
import es.sfernandez.sqg.deserializer.json.JsonKeys
import es.sfernandez.sqg.deserializer.json.questionary.question.ChoiceJsonDeserializer

class MultipleSelectionAnswerJsonDeserializer : JsonDeserializer<MultipleSelectionAnswer> {

    //---- Attributes ----
    private val choiceDeserializer: ChoiceJsonDeserializer

    //---- Constructor ----
    constructor() : super(MultipleSelectionAnswer::class.java) {
        this.choiceDeserializer = ChoiceJsonDeserializer()
    }

    internal constructor(choiceJsonDeserializer: ChoiceJsonDeserializer) : super(MultipleSelectionAnswer::class.java) {
        this.choiceDeserializer = choiceJsonDeserializer
    }

    //---- Methods ----
    override fun createDeserializer(): StdDeserializer<MultipleSelectionAnswer> {
        return CustomDeserializer()
    }

    private inner class CustomDeserializer : StdDeserializer<MultipleSelectionAnswer>(mappedClass) {

        override fun deserialize(parser: JsonParser?, ctxt: DeserializationContext?): MultipleSelectionAnswer {
            val node = extractJsonNode(parser)

            val possibleChoices = extractArrayOfObjects(node, JsonKeys.Answer.MultipleSelection.POSSIBLE_CHOICES, choiceDeserializer)
            val rightChoicesIds = extractArray(node, JsonKeys.Answer.MultipleSelection.RIGHT_CHOICES_IDS, JsonNode::asText)

            return MultipleSelectionAnswer(possibleChoices, rightChoicesIds)
        }

    }

}