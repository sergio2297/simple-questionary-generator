package es.sfernandez.sqg.deserializer.json.questionnaire.question.answer.input

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import es.sfernandez.sqg.beans.question.answers.input.TextAnswerInput
import es.sfernandez.sqg.deserializer.json.JsonDeserializer

internal class TextAnswerInputJsonDeserializer : JsonDeserializer<TextAnswerInput>(TextAnswerInput::class.java) {

    //---- Methods ----
    override fun createDeserializer(): StdDeserializer<TextAnswerInput> {
        return CustomDeserializer()
    }

    private inner class CustomDeserializer : StdDeserializer<TextAnswerInput>(mappedClass) {

        override fun deserialize(parser: JsonParser?, ctxt: DeserializationContext?): TextAnswerInput {
            return TextAnswerInput()
        }

    }

}