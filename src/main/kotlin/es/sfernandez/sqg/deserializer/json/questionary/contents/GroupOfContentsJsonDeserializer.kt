package es.sfernandez.sqg.deserializer.json.questionary.contents

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.node.ArrayNode
import es.sfernandez.sqg.beans.contents.GroupOfContents
import es.sfernandez.sqg.deserializer.DeserializationException
import es.sfernandez.sqg.deserializer.json.JsonDeserializer

class GroupOfContentsJsonDeserializer : JsonDeserializer<GroupOfContents> {

    //---- Attributes ----
    private val contentDeserializer: ContentJsonDeserializer

    //---- Constructor ----
    constructor() : super(GroupOfContents::class.java) {
        contentDeserializer = ContentJsonDeserializer()
    }

    internal constructor(contentDeserializer: ContentJsonDeserializer) : super(GroupOfContents::class.java) {
        this.contentDeserializer = contentDeserializer
    }

    //---- Methods ----
    override fun createDeserializer(): StdDeserializer<GroupOfContents> {
        return CustomDeserializer()
    }

    private inner class CustomDeserializer : StdDeserializer<GroupOfContents>(mappedClass) {

        override fun deserialize(parser: JsonParser?, ctxt: DeserializationContext?): GroupOfContents {
            val jsonArray = toJsonArray(extractJsonNode(parser))

            val validNodes = jsonArray.asSequence()
                .filter { node -> node.isObject && contentDeserializer.nodeIsValidContentType(node)}
                .toList()

            logsWarningOfIgnoredValuesIfNecessary(validNodes, jsonArray)

            val manager = GroupOfContents()
            for(node in validNodes) {
                val content = contentDeserializer.deserialize(node.toString())
                manager.add(content)
            }

            dumpLogsFrom(contentDeserializer)

            return manager
        }

        private fun logsWarningOfIgnoredValuesIfNecessary(validNodes: List<JsonNode>, jsonArray: ArrayNode) {
            val ignoredElements = jsonArray.size() - validNodes.size
            if(ignoredElements > 0)
                log.warning("There are $ignoredElements ignored elements while tying to deserialize " +
                        "a group of contents. This may be due to an incorrect content key or a json object with more " +
                        "than one content json object.")
        }

    }

}