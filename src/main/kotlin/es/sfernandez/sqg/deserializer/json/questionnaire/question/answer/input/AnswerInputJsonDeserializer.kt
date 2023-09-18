package es.sfernandez.sqg.deserializer.json.questionnaire.question.answer.input

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import es.sfernandez.sqg.beans.question.answers.input.AnswerInput
import es.sfernandez.sqg.beans.question.answers.input.UnspecifiedAnswerInput
import es.sfernandez.sqg.deserializer.json.JsonDeserializer


class AnswerInputJsonDeserializer : JsonDeserializer<AnswerInput>(AnswerInput::class.java) {

    //---- Attributes ----

    //---- constructor ----

    //---- Methods ----
    override fun createDeserializer(): StdDeserializer<AnswerInput> {
        return CustomDeserializer()
    }

    private inner class CustomDeserializer : StdDeserializer<AnswerInput>(mappedClass) {

        override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): AnswerInput {
//            TODO("Not yet implemented")
            return UnspecifiedAnswerInput()
        }

    }

    //    override fun createDeserializer(): StdDeserializer<Answer> {
//        return CustomDeserializer()
//    }
//
//    private inner class CustomDeserializer : StdDeserializer<Answer>(mappedClass) {
//
//        override fun deserialize(parser: JsonParser?, ctxt: DeserializationContext?): Answer {
//            val node = extractJsonNode(parser)
//
//            return when(extractEnum(node, JsonKeys.Answer.TYPE, AnswerInput.Type.UNSPECIFIED)) {
//                AnswerInput.Type.TEXT -> textAnswerDeserializer.deserialize(node.toString())
//                    .also { dumpLogsFrom(textAnswerDeserializer) }
//
//                AnswerInput.Type.SINGLE_SELECTION -> singleSelectionAnswerDeserializer.deserialize(node.toString())
//                    .also { dumpLogsFrom(singleSelectionAnswerDeserializer) }
//
//                AnswerInput.Type.MULTIPLE_SELECTION -> multipleSelectionAnswerDeserializer.deserialize(node.toString())
//                    .also { dumpLogsFrom(multipleSelectionAnswerDeserializer) }
//
//                else -> Answer().also { log.warning("Error. ${AnswerJsonDeserializer::class.simpleName} can't deserialize json received." +
//                        " This may be due to an incorrect content key or an incorrect type value. Unknown content returned instead.")}
//            }
//        }
//
//    }

}