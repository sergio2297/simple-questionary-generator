package es.sfernandez.sqg.deserializer.json

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import es.sfernandez.sqg.model.question.answers.replies.Reply
import es.sfernandez.sqg.model.question.answers.replies.TextReply
import es.sfernandez.sqg.model.question.problems.Problem

class ReplyJsonDeserializer() : JsonDeserializer<Reply<*>>(Reply::class.java) {
    override fun createDeserializer(): StdDeserializer<Reply<*>> {
        return CustomDeserializer()
    }

    private class CustomDeserializer : StdDeserializer<Reply<*>>(Reply::class.java) {
        override fun deserialize(parser : JsonParser?, ctxt : DeserializationContext?) : Reply<*> {
            val reply = TextReply("text")

//            val codec: ObjectCodec = parser?.codec ?: ObjectCodec()
//            val node = codec.readTree<JsonNode>(parser)
//
//            // try catch block
//
//            // try catch block
//            val colorNode = node["color"]
//            val color = colorNode.asText()
//            car.setColor(color)

            return reply
        }

    }

}