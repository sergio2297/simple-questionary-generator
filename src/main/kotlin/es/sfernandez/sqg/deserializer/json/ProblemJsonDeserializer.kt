package es.sfernandez.sqg.deserializer.json

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import es.sfernandez.sqg.deserializer.DeserializationException
import es.sfernandez.sqg.model.question.problems.Problem


class ProblemJsonDeserializer() : JsonDeserializer<Problem>(Problem::class.java) {
    override fun createDeserializer() : StdDeserializer<Problem> {
        return CustomDeserializer()
    }

    private inner class CustomDeserializer : StdDeserializer<Problem>(mappedClass) {
        override fun deserialize(parser : JsonParser?, ctxt : DeserializationContext?) : Problem {
            val node = extractJsonNode(parser)

            val problem = Problem()

//            val codec: ObjectCodec = parser?.codec ?: ObjectCodec()
//            val node = codec.readTree<JsonNode>(parser)
//
//            // try catch block
//
//            // try catch block
//            val colorNode = node["color"]
//            val color = colorNode.asText()
//            car.setColor(color)

            return problem
        }

    }

}