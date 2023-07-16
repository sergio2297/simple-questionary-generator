package es.sfernandez.sqg.deserializer.json.questionary.question.answer

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import es.sfernandez.sqg.beans.question.answers.SingleSelectionAnswer
import es.sfernandez.sqg.deserializer.json.JsonDeserializer
import es.sfernandez.sqg.deserializer.json.JsonKeys
import es.sfernandez.sqg.deserializer.json.questionary.question.ChoiceJsonDeserializer

class SingleSelectionAnswerJsonDeserializer : JsonDeserializer<SingleSelectionAnswer> {

    //---- Attributes ----
    private val choiceDeserializer: ChoiceJsonDeserializer

    //---- Constructor ----
    constructor() : super(SingleSelectionAnswer::class.java) {
        this.choiceDeserializer = ChoiceJsonDeserializer()
    }

    internal constructor(choiceJsonDeserializer: ChoiceJsonDeserializer) : super(SingleSelectionAnswer::class.java) {
        this.choiceDeserializer = choiceJsonDeserializer
    }

    //---- Methods ----
    override fun createDeserializer(): StdDeserializer<SingleSelectionAnswer> {
        return CustomDeserializer()
    }

    private inner class CustomDeserializer : StdDeserializer<SingleSelectionAnswer>(mappedClass) {

        override fun deserialize(parser: JsonParser?, ctxt: DeserializationContext?): SingleSelectionAnswer {
            val node = extractJsonNode(parser)

            val possibleChoices = extractArrayOfObjects(node, JsonKeys.Answer.SingleSelection.POSSIBLE_CHOICES, choiceDeserializer)
            val rightChoiceId = extractText(node, JsonKeys.Answer.SingleSelection.RIGHT_CHOICE_ID)

            return SingleSelectionAnswer(possibleChoices, rightChoiceId)
        }

    }

}