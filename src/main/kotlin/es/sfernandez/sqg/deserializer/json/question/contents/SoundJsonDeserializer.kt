package es.sfernandez.sqg.deserializer.json.question.contents

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import es.sfernandez.sqg.deserializer.json.JsonDeserializer
import es.sfernandez.sqg.model.contents.Sound

class SoundJsonDeserializer : JsonDeserializer<Sound>(Sound::class.java) {

    override fun createDeserializer(): StdDeserializer<Sound> {
        return CustomDeserializer()
    }

    private inner class CustomDeserializer : StdDeserializer<Sound>(mappedClass) {
        override fun deserialize(parser: JsonParser?, ctxt: DeserializationContext?): Sound {
            val node = extractJsonNode(parser)
            TODO("Not yet implemented")
        }

    }

}