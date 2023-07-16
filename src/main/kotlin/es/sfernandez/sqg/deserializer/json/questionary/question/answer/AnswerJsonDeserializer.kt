package es.sfernandez.sqg.deserializer.json.questionary.question.answer

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import es.sfernandez.sqg.beans.question.answers.Answer
import es.sfernandez.sqg.beans.question.answers.AnswerTypes
import es.sfernandez.sqg.beans.question.answers.UnknownAnswer
import es.sfernandez.sqg.deserializer.json.JsonDeserializer
import es.sfernandez.sqg.deserializer.json.JsonKeys

class AnswerJsonDeserializer : JsonDeserializer<Answer> {

    //---- Attributes ----
    private val textAnswerDeserializer: TextAnswerJsonDeserializer
    private val singleSelectionAnswerDeserializer: SingleSelectionAnswerJsonDeserializer
    private val multipleSelectionAnswerDeserializer: MultipleSelectionAnswerJsonDeserializer

    //---- Constructor ----
    constructor() : super(Answer::class.java) {
        textAnswerDeserializer = TextAnswerJsonDeserializer()
        singleSelectionAnswerDeserializer = SingleSelectionAnswerJsonDeserializer()
        multipleSelectionAnswerDeserializer = MultipleSelectionAnswerJsonDeserializer()
    }

    internal constructor(textAnswerDeserializer: TextAnswerJsonDeserializer,
                         singleSelectionAnswerDeserializer: SingleSelectionAnswerJsonDeserializer,
                         multipleSelectionAnswerDeserializer: MultipleSelectionAnswerJsonDeserializer) : super(Answer::class.java) {
        this.textAnswerDeserializer = textAnswerDeserializer
        this.singleSelectionAnswerDeserializer = singleSelectionAnswerDeserializer
        this.multipleSelectionAnswerDeserializer = multipleSelectionAnswerDeserializer
    }

    //---- Methods ----
    override fun createDeserializer(): StdDeserializer<Answer> {
        return CustomDeserializer()
    }

    private inner class CustomDeserializer : StdDeserializer<Answer>(mappedClass) {

        override fun deserialize(parser: JsonParser?, ctxt: DeserializationContext?): Answer {
            val node = extractJsonNode(parser)

            return when(extractEnum(node, JsonKeys.Answer.TYPE, AnswerTypes.UNKNOWN)) {
                AnswerTypes.TEXT_INPUT -> textAnswerDeserializer.deserialize(node.toString())
                    .also { dumpLogsFrom(textAnswerDeserializer) }

                AnswerTypes.SINGLE_SELECTION -> singleSelectionAnswerDeserializer.deserialize(node.toString())
                    .also { dumpLogsFrom(singleSelectionAnswerDeserializer) }

                AnswerTypes.MULTIPLE_SELECTION -> multipleSelectionAnswerDeserializer.deserialize(node.toString())
                    .also { dumpLogsFrom(multipleSelectionAnswerDeserializer) }

                else -> UnknownAnswer().also { log.warning("Error. ${AnswerJsonDeserializer::class.simpleName} can't deserialize json received." +
                        " This may be due to an incorrect content key or an incorrect type value. Unknown content returned instead.")}
            }
        }

    }
}