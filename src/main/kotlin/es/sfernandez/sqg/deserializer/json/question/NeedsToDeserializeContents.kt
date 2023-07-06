package es.sfernandez.sqg.deserializer.json.question

import com.fasterxml.jackson.databind.JsonNode
import es.sfernandez.sqg.deserializer.DeserializationException
import es.sfernandez.sqg.deserializer.json.question.contents.GroupOfContentsJsonDeserializer
import es.sfernandez.sqg.model.contents.HasContents

internal interface NeedsToDeserializeContents {

    val deserializer: GroupOfContentsJsonDeserializer
    val contentsKey: String

    fun deserializeContentsIn(node: JsonNode, hasContents: HasContents) {
        val jsonContents = node.get(contentsKey) ?: return

        if(!jsonContents.isArray) throw DeserializationException("Error. Contents must be defined in an array")

        val deserializedContents = deserializer.deserialize(jsonContents.toString())
        hasContents.groupOfContents.add(*deserializedContents.contents().toTypedArray())
    }

}