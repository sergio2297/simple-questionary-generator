package es.sfernandez.sqg.deserializer.json.questionnaire.question

import com.fasterxml.jackson.databind.JsonNode
import es.sfernandez.sqg.beans.contents.HasContents
import es.sfernandez.sqg.deserializer.json.questionnaire.contents.GroupOfContentsJsonDeserializer
import es.sfernandez.sqg.deserializer.logs.DeserializationLogsProducer

internal interface NeedsToDeserializeGroupOfContents {

    val deserializer: GroupOfContentsJsonDeserializer
    val contentsKey: String

    fun deserializeContentsIn(hasContents: HasContents, node: JsonNode, log: DeserializationLogsProducer) {
        val jsonProperty = node[contentsKey]

        if (jsonProperty == null) {
            log.errorMissingProperty(contentsKey)
            return
        }

        if(!jsonProperty.isArray) {
            log.errorIncorrectType(contentsKey)
            return
        }

        val deserializedContents = deserializer.deserialize(jsonProperty.toString())
        hasContents.groupOfContents.add(*deserializedContents.contents().toTypedArray())
    }

}