package es.sfernandez.sqg.deserializer.json.questionnaire.question.answer.input

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import es.sfernandez.sqg.beans.question.answers.input.AnswerInput
import es.sfernandez.sqg.beans.question.answers.input.UnspecifiedAnswerInput
import es.sfernandez.sqg.deserializer.json.JsonDeserializer
import es.sfernandez.sqg.deserializer.json.JsonKeys


class AnswerInputJsonDeserializer : JsonDeserializer<AnswerInput> {

    //---- Attributes ----
    private val textInputDeserializer: TextAnswerInputJsonDeserializer
    private val singleSelectionInputDeserializer: SingleSelectionAnswerInputJsonDeserializer
    private val multipleSelectionInputDeserializer: MultipleSelectionAnswerInputJsonDeserializer

    //---- Constructor ----
    constructor() : super(AnswerInput::class.java) {
        textInputDeserializer = TextAnswerInputJsonDeserializer()
        singleSelectionInputDeserializer = SingleSelectionAnswerInputJsonDeserializer()
        multipleSelectionInputDeserializer = MultipleSelectionAnswerInputJsonDeserializer()
    }

    internal constructor(
        textInputDeserializer: TextAnswerInputJsonDeserializer,
        singleSelectionInputDeserializer: SingleSelectionAnswerInputJsonDeserializer,
        multipleSelectionInputDeserializer: MultipleSelectionAnswerInputJsonDeserializer
    ) : super(AnswerInput::class.java) {
        this.textInputDeserializer = textInputDeserializer
        this.singleSelectionInputDeserializer = singleSelectionInputDeserializer
        this.multipleSelectionInputDeserializer = multipleSelectionInputDeserializer
    }

    //---- Methods ----
    override fun createDeserializer(): StdDeserializer<AnswerInput> {
        return CustomDeserializer()
    }

    private inner class CustomDeserializer : StdDeserializer<AnswerInput>(mappedClass) {

        override fun deserialize(parser: JsonParser?, ctxt: DeserializationContext?): AnswerInput {
            val node = extractJsonNode(parser)

            return when(extractEnum(node, JsonKeys.Answer.Input.TYPE, AnswerInput.Type.UNSPECIFIED)) {
                AnswerInput.Type.TEXT -> textInputDeserializer.deserialize(node.toString())
                    .also { dumpLogsFrom(textInputDeserializer) }

                AnswerInput.Type.SINGLE_SELECTION -> singleSelectionInputDeserializer.deserialize(node.toString())
                    .also { dumpLogsFrom(singleSelectionInputDeserializer) }

                AnswerInput.Type.MULTIPLE_SELECTION -> multipleSelectionInputDeserializer.deserialize(node.toString())
                    .also { dumpLogsFrom(multipleSelectionInputDeserializer) }

                else -> UnspecifiedAnswerInput().also { log.warning("Error. ${AnswerInputJsonDeserializer::class.simpleName} can't deserialize json received." +
                        " This may be due to an incorrect content key or an incorrect type value. Unspecified input returned instead.") }
            }
        }

    }

}