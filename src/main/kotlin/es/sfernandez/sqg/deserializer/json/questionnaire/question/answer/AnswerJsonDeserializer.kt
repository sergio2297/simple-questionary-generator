package es.sfernandez.sqg.deserializer.json.questionnaire.question.answer

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import es.sfernandez.sqg.beans.question.answers.Answer
import es.sfernandez.sqg.beans.question.answers.correction.UnspecifiedAnswerCorrection
import es.sfernandez.sqg.beans.question.answers.input.UnspecifiedAnswerInput
import es.sfernandez.sqg.deserializer.json.JsonDeserializer
import es.sfernandez.sqg.deserializer.json.JsonKeys
import es.sfernandez.sqg.deserializer.json.questionnaire.question.answer.correction.AnswerCorrectionJsonDeserializer
import es.sfernandez.sqg.deserializer.json.questionnaire.question.answer.input.AnswerInputJsonDeserializer

class AnswerJsonDeserializer : JsonDeserializer<Answer> {

    //---- Attributes ----
    private val inputDeserializer: AnswerInputJsonDeserializer
    private val correctionDeserializer: AnswerCorrectionJsonDeserializer

    // ---- Constructor ----
    constructor() : super(Answer::class.java) {
        inputDeserializer = AnswerInputJsonDeserializer()
        correctionDeserializer = AnswerCorrectionJsonDeserializer()
    }

    internal constructor(
        inputDeserializer: AnswerInputJsonDeserializer,
        correctionDeserializer: AnswerCorrectionJsonDeserializer
    ) : super(Answer::class.java) {
        this.inputDeserializer = inputDeserializer
        this.correctionDeserializer = correctionDeserializer
    }

    //---- Methods ----
    override fun createDeserializer(): StdDeserializer<Answer> {
        return CustomDeserializer()
    }

    private inner class CustomDeserializer : StdDeserializer<Answer>(mappedClass) {

        override fun deserialize(parser: JsonParser?, ctxt: DeserializationContext?): Answer {
            val node = extractJsonNode(parser)

            val answer = Answer()
            answer.input = extractObject(node, JsonKeys.Answer.INPUT, inputDeserializer, UnspecifiedAnswerInput())
            answer.correction = extractObject(node, JsonKeys.Answer.CORRECTION, correctionDeserializer, UnspecifiedAnswerCorrection())
            return answer
        }

    }

}