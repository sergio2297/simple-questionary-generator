package es.sfernandez.sqg.deserializer.json.questionary.contents

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import es.sfernandez.sqg.deserializer.json.JsonDeserializer
import es.sfernandez.sqg.deserializer.json.JsonKeys
import es.sfernandez.sqg.beans.contents.Image

class ImageJsonDeserializer : JsonDeserializer<Image>(Image::class.java) {

    override fun createDeserializer(): StdDeserializer<Image> {
        return CustomDeserializer()
    }

    private inner class CustomDeserializer : StdDeserializer<Image>(mappedClass) {
        override fun deserialize(parser: JsonParser?, ctxt: DeserializationContext?): Image {
            val node = extractJsonNode(parser)

            val path = extractText(node, JsonKeys.Contents.Image.PATH)
            val clickToSee = extractBool(node, JsonKeys.Contents.Image.CLICK_TO_SEE)

            return Image(path, clickToSee)
        }

    }

}