package es.sfernandez.sqg.deserializer.json

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import es.sfernandez.sqg.model.contents.Text

class TextJsonDeserializer : JsonDeserializer<Text>(Text::class.java) {

    override fun createDeserializer(): StdDeserializer<Text> {
        return CustomDeserializer()
    }

    private inner class CustomDeserializer : StdDeserializer<Text>(mappedClass) {

        override fun deserialize(parser: JsonParser?, ctxt: DeserializationContext?): Text {
            val node = extractJsonNode(parser)

            val text = Text()
            text.value = extractValue(node)
            text.markup = extractMarkup(node)
            return text
        }

        private fun extractValue(node: JsonNode) : String {
            val valueNode = node[JsonKeys.Text.VALUE]
            return if(valueNode == null) "" else valueNode.asText()
        }

        private fun extractMarkup(node: JsonNode): Text.Markup {
            val markupNode = node[JsonKeys.Text.MARKUP]
            return if(markupNode == null) Text.Markup.SIMPLE else Text.Markup.valueOf(markupNode.asText())
        }

    }

}