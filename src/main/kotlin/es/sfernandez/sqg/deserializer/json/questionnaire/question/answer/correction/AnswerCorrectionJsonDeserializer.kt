package es.sfernandez.sqg.deserializer.json.questionnaire.question.answer.correction

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import es.sfernandez.sqg.beans.question.answers.correction.AnswerCorrection
import es.sfernandez.sqg.beans.question.answers.correction.UnspecifiedAnswerCorrection
import es.sfernandez.sqg.deserializer.json.JsonDeserializer
import es.sfernandez.sqg.deserializer.json.JsonKeys

class AnswerCorrectionJsonDeserializer : JsonDeserializer<AnswerCorrection> {

    //---- Attributes ----
    private val rightOrNotDeserializer: RightOrNotCorrectionJsonDeserializer

    //---- Constructor ----
    constructor() : super(AnswerCorrection::class.java) {
        rightOrNotDeserializer = RightOrNotCorrectionJsonDeserializer()
    }

    internal constructor(rightOrNotDeserializer: RightOrNotCorrectionJsonDeserializer) : super(AnswerCorrection::class.java) {
        this.rightOrNotDeserializer = rightOrNotDeserializer
    }

    //---- Methods ----
    override fun createDeserializer(): StdDeserializer<AnswerCorrection> {
        return CustomDeserializer()
    }

    private inner class CustomDeserializer : StdDeserializer<AnswerCorrection>(mappedClass) {

        override fun deserialize(parser: JsonParser?, ctxt: DeserializationContext?): AnswerCorrection {
            val node = extractJsonNode(parser)

            return when(extractEnum(node, JsonKeys.Answer.Correction.TYPE, AnswerCorrection.Type.UNSPECIFIED)) {
                AnswerCorrection.Type.RIGHT_OR_NOT -> rightOrNotDeserializer.deserialize(node.toString())
                    .also { dumpLogsFrom(rightOrNotDeserializer) }

                else -> UnspecifiedAnswerCorrection().also { log.warning("Error. ${AnswerCorrectionJsonDeserializer::class.simpleName} can't deserialize json received." +
                        " This may be due to an incorrect content key or an incorrect type value. Unspecified correction returned instead.") }
            }
        }

    }

}