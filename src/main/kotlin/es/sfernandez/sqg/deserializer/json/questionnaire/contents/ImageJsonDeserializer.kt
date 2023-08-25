package es.sfernandez.sqg.deserializer.json.questionnaire.contents

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import es.sfernandez.sqg.beans.contents.Image
import es.sfernandez.sqg.deserializer.json.JsonDeserializer
import es.sfernandez.sqg.deserializer.json.JsonKeys

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