package es.sfernandez.sqg.deserializer.json.question.contents

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import es.sfernandez.sqg.deserializer.json.JsonDeserializer
import es.sfernandez.sqg.deserializer.json.JsonKeys
import es.sfernandez.sqg.model.contents.Sound
import es.sfernandez.sqg.model.contents.Video

class VideoJsonDeserializer : JsonDeserializer<Video>(Video::class.java) {

    override fun createDeserializer(): StdDeserializer<Video> {
        return CustomDeserializer()
    }

    private inner class CustomDeserializer : StdDeserializer<Video>(mappedClass) {
        override fun deserialize(parser: JsonParser?, ctxt: DeserializationContext?): Video {
            val node = extractJsonNode(parser)

            val path = extractText(node, JsonKeys.Video.PATH)
            val autoplay = extractBool(node, JsonKeys.Video.AUTOPLAY, true)

            return Video(path, autoplay)
        }

    }

}