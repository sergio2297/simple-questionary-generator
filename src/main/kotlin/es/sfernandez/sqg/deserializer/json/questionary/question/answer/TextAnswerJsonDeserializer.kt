package es.sfernandez.sqg.deserializer.json.questionary.question.answer

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import es.sfernandez.sqg.beans.question.answers.TextAnswer
import es.sfernandez.sqg.deserializer.json.JsonDeserializer
import es.sfernandez.sqg.deserializer.json.JsonKeys

class TextAnswerJsonDeserializer : JsonDeserializer<TextAnswer>(TextAnswer::class.java) {

    override fun createDeserializer(): StdDeserializer<TextAnswer> {
        return CustomDeserializer()
    }

    private inner class CustomDeserializer : StdDeserializer<TextAnswer>(mappedClass) {

        override fun deserialize(parser: JsonParser?, ctxt: DeserializationContext?): TextAnswer {
            val node = extractJsonNode(parser)

            val possibleReplies = extractArray(node, JsonKeys.Answer.Text.POSSIBLE_REPLIES, JsonNode::asText)
            if(possibleReplies.isNotEmpty())
                return TextAnswer(*possibleReplies)

            val replyRegex = Regex(extractText(node, JsonKeys.Answer.Text.REPLY_REGEX))
            return TextAnswer(replyRegex)
        }

    }

}