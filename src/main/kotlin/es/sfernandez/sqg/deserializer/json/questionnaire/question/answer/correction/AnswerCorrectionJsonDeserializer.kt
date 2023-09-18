package es.sfernandez.sqg.deserializer.json.questionnaire.question.answer.correction

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import es.sfernandez.sqg.beans.question.answers.correction.AnswerCorrection
import es.sfernandez.sqg.beans.question.answers.correction.UnspecifiedAnswerCorrection
import es.sfernandez.sqg.deserializer.json.JsonDeserializer

class AnswerCorrectionJsonDeserializer : JsonDeserializer<AnswerCorrection>(AnswerCorrection::class.java) {

    //---- Attributes ----

    //---- constructor ----

    //---- Methods ----
    override fun createDeserializer(): StdDeserializer<AnswerCorrection> {
        return CustomDeserializer()
    }

    private inner class CustomDeserializer : StdDeserializer<AnswerCorrection>(mappedClass) {

        override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): AnswerCorrection {
            // TODO("Not yet implemented")
            return UnspecifiedAnswerCorrection()
        }

    }

}