package es.sfernandez.sqg.deserializer.json.questionnaire.question.answer.correction

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.node.TextNode
import es.sfernandez.sqg.beans.question.answers.correction.rightornot.*
import es.sfernandez.sqg.deserializer.json.JsonDeserializer
import es.sfernandez.sqg.deserializer.json.JsonKeys
import java.util.*

internal class RightOrNotCorrectionJsonDeserializer : JsonDeserializer<RightOrNotCorrection>(RightOrNotCorrection::class.java) {

    //---- Methods ----
    override fun createDeserializer(): StdDeserializer<RightOrNotCorrection> {
        return CustomDeserializer()
    }

    private inner class CustomDeserializer : StdDeserializer<RightOrNotCorrection>(mappedClass) {

        override fun deserialize(parser: JsonParser?, ctxt: DeserializationContext?): RightOrNotCorrection {
            val node = extractJsonNode(parser)

            if(isRightChoiceIds(node))
                return deserializeAsRightChoiceIdsCorrection(node)

            if(isPossibleValues(node))
                return deserializeAsPossibleValuesCorrection(node)

            if(isCorrectValuesRegex(node))
                return deserializeAsCorrectValuesRegexCorrection(node)

            log.warning("Error. ${RightOrNotCorrectionJsonDeserializer::class.simpleName} can't deserialize json received." +
                    " This may be due to an incorrect content key or an incorrect type value. Unspecified correction returned instead.")
            return UnspecifiedRightOrNotCorrection()
        }

        private fun isRightChoiceIds(node: JsonNode) : Boolean {
            return node.has(JsonKeys.Answer.Correction.RightOrNot.IDS)
        }

        private fun deserializeAsRightChoiceIdsCorrection(node: JsonNode): RightOrNotCorrection {
            return RightChoiceIds(*extractArray(node, JsonKeys.Answer.Correction.RightOrNot.IDS, this::castToString))
        }

        private fun isPossibleValues(node: JsonNode) : Boolean {
            return node.has(JsonKeys.Answer.Correction.RightOrNot.VALUES)
        }

        private fun deserializeAsPossibleValuesCorrection(node: JsonNode): RightOrNotCorrection {
            return PossibleValues(*extractArray(node, JsonKeys.Answer.Correction.RightOrNot.VALUES, this::castToString))
        }

        private fun isCorrectValuesRegex(node: JsonNode) : Boolean {
            return node.has(JsonKeys.Answer.Correction.RightOrNot.REGEX)
        }

        private fun deserializeAsCorrectValuesRegexCorrection(node: JsonNode): RightOrNotCorrection {
            return CorrectValuesRegex(Regex(extractText(node, JsonKeys.Answer.Correction.RightOrNot.REGEX)))
        }

        private fun <T> castToString(value: T) : String {
            return if(value is TextNode) value.textValue() else Objects.toString(value)
        }

    }

}