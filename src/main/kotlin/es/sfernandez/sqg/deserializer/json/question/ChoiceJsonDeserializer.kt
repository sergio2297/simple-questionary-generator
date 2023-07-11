package es.sfernandez.sqg.deserializer.json.question

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import es.sfernandez.sqg.deserializer.json.JsonDeserializer
import es.sfernandez.sqg.deserializer.json.JsonKeys
import es.sfernandez.sqg.beans.contents.Content
import es.sfernandez.sqg.beans.contents.UnknownContent
import es.sfernandez.sqg.beans.question.answers.choices.Choice


class ChoiceJsonDeserializer : JsonDeserializer<Choice> {

    //---- Constructor ----
    constructor() : super(Choice::class.java) {

    }

//    internal constructor(groupOfContentsDeserializer: GroupOfContentsJsonDeserializer) : super(Choice::class.java) {

//    }
//

    //---- Methods ----
    override fun createDeserializer(): StdDeserializer<Choice> {
        return CustomDeserializer()
    }

    inner class CustomDeserializer : StdDeserializer<Choice>(mappedClass) {
        override fun deserialize(parser: JsonParser?, ctxt: DeserializationContext?): Choice {
            val node = extractJsonNode(parser)

            val id = deserializeIdFrom(node)
            val content = deserializeContentFrom(node)

            return Choice(id, content)
        }

        private fun deserializeIdFrom(node: JsonNode): String {
            val id = node.get(JsonKeys.Choice.ID)
            return if (id != null) id.asText() else ""
        }

        private fun deserializeContentFrom(node: JsonNode): Content {
            return UnknownContent()
        }

    }

}