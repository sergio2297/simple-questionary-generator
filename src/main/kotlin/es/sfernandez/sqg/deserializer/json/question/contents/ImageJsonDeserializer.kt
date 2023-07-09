package es.sfernandez.sqg.deserializer.json.question.contents

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import es.sfernandez.sqg.deserializer.json.JsonDeserializer
import es.sfernandez.sqg.deserializer.json.JsonKeys
import es.sfernandez.sqg.model.contents.Image

class ImageJsonDeserializer : JsonDeserializer<Image>(Image::class.java) {

    override fun createDeserializer(): StdDeserializer<Image> {
        return CustomDeserializer()
    }

    private inner class CustomDeserializer : StdDeserializer<Image>(mappedClass) {
        override fun deserialize(parser: JsonParser?, ctxt: DeserializationContext?): Image {
            val node = extractJsonNode(parser)

            val path = extractPath(node)
            val clickToSee = extractClickToSee(node)

            return Image(path, clickToSee)
        }

        private fun extractPath(node: JsonNode): String {
            return try {
                node[JsonKeys.Image.PATH].asText()
            } catch (ex: NullPointerException) {
                ""
            }
        }

        private fun extractClickToSee(node: JsonNode): Boolean {
            return try {
                node[JsonKeys.Image.CLICK_TO_SEE].asBoolean()
            } catch (ex: NullPointerException) {
                false
            }
        }

    }

}