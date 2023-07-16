package es.sfernandez.sqg.deserializer.json.questionary.contents

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import es.sfernandez.sqg.deserializer.json.JsonDeserializer
import es.sfernandez.sqg.deserializer.json.JsonKeys
import es.sfernandez.sqg.beans.contents.Video

class VideoJsonDeserializer : JsonDeserializer<Video>(Video::class.java) {

    override fun createDeserializer(): StdDeserializer<Video> {
        return CustomDeserializer()
    }

    private inner class CustomDeserializer : StdDeserializer<Video>(mappedClass) {
        override fun deserialize(parser: JsonParser?, ctxt: DeserializationContext?): Video {
            val node = extractJsonNode(parser)

            val path = extractText(node, JsonKeys.Contents.Video.PATH)
            val autoplay = extractBool(node, JsonKeys.Contents.Video.AUTOPLAY, true)

            return Video(path, autoplay)
        }

    }

}