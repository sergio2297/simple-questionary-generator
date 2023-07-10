package es.sfernandez.sqg.deserializer.json.question.contents

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import es.sfernandez.sqg.deserializer.json.JsonDeserializer
import es.sfernandez.sqg.deserializer.json.JsonKeys
import es.sfernandez.sqg.model.contents.Text

class TextJsonDeserializer : JsonDeserializer<Text>(Text::class.java) {

    override fun createDeserializer(): StdDeserializer<Text> {
        return CustomDeserializer()
    }

    private inner class CustomDeserializer : StdDeserializer<Text>(mappedClass) {

        override fun deserialize(parser: JsonParser?, ctxt: DeserializationContext?): Text {
            val node = extractJsonNode(parser)

            val value = extractText(node, JsonKeys.Text.VALUE)
            val markup = extractEnum(node, JsonKeys.Text.MARKUP, Text.Markup.SIMPLE)

            return Text(value, markup)
        }

    }

}