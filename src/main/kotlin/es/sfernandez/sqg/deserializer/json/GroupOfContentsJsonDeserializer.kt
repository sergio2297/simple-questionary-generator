package es.sfernandez.sqg.deserializer.json

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.node.ArrayNode
import es.sfernandez.sqg.deserializer.DeserializationException
import es.sfernandez.sqg.model.contents.Content
import es.sfernandez.sqg.model.contents.GroupOfContents
import es.sfernandez.sqg.model.contents.ContentType

class GroupOfContentsJsonDeserializer : JsonDeserializer<GroupOfContents> {

    //---- Attributes ----
    private val textDeserializer : TextJsonDeserializer
    private val soundDeserializer : SoundJsonDeserializer
    private val videoDeserializer : VideoJsonDeserializer
    private val imageDeserializer : ImageJsonDeserializer

    //---- Constructor ----
    constructor() : super(GroupOfContents::class.java) {
        textDeserializer = TextJsonDeserializer()
        soundDeserializer = SoundJsonDeserializer()
        videoDeserializer = VideoJsonDeserializer()
        imageDeserializer = ImageJsonDeserializer()
    }

    internal constructor(textDeserializer: TextJsonDeserializer,
            soundDeserializer: SoundJsonDeserializer,
            videoDeserializer: VideoJsonDeserializer,
            imageDeserializer: ImageJsonDeserializer) : super(GroupOfContents::class.java) {
        this.textDeserializer = textDeserializer
        this.soundDeserializer = soundDeserializer
        this.videoDeserializer = videoDeserializer
        this.imageDeserializer = imageDeserializer
    }

    //---- Methods ----
    override fun createDeserializer(): StdDeserializer<GroupOfContents> {
        return CustomDeserializer()
    }

    private inner class CustomDeserializer : StdDeserializer<GroupOfContents>(mappedClass) {

        override fun deserialize(parser: JsonParser?, ctxt: DeserializationContext?): GroupOfContents {
            val jsonArray = toJsonArray(extractJsonNode(parser))

            val validNodes = jsonArray.asSequence()
                .filter { node -> node.isObject && nodeIsValidContentType(node)}
                .toList()

            val manager = GroupOfContents()
            for(node in validNodes) {
                val deserializer = deserializerFor(contentTypeByName(firstFieldName(node)))
                val content = deserializer.deserialize(node[firstFieldName(node)].toString())
                manager.add(content)
            }

            return manager
        }

        private fun toJsonArray(jsonNode: JsonNode) : ArrayNode {
            if(!jsonNode.isArray) throw DeserializationException("Error. ${GroupOfContentsJsonDeserializer::class.simpleName}" +
                    " expects a JSON Array not: '${jsonNode}'")
            return jsonNode as ArrayNode
        }

        private fun nodeIsValidContentType(jsonNode : JsonNode) : Boolean {
            val validJsonNames = validContentTypes().map { type -> type.jsonName }
            return nodeHasOnlyOneField(jsonNode)
                    && validJsonNames.contains(firstFieldName(jsonNode))
        }

        private fun validContentTypes() : Iterable<ContentType> {
            return enumValues<ContentType>().filter { type -> type != ContentType.UNKNOWN }
        }

        private fun nodeHasOnlyOneField(jsonNode : JsonNode) : Boolean {
            return jsonNode.fieldNames().asSequence().count() == 1
        }

        private fun firstFieldName(jsonNode : JsonNode) : String {
            return jsonNode.fieldNames().asSequence().first()
        }

        private fun contentTypeByName(name: String): ContentType {
            return enumValues<ContentType>().first { value -> value.jsonName == name }
        }

        private fun deserializerFor(contentType : ContentType) : JsonDeserializer<out Content> {
            return when (contentType) {
                ContentType.TEXT -> textDeserializer
                ContentType.SOUND -> soundDeserializer
                ContentType.VIDEO -> videoDeserializer
                ContentType.IMAGE -> imageDeserializer
                else -> throw DeserializationException("Error. There is no ${JsonDeserializer::class.simpleName} available " +
                        "for content type ${contentType.name}")
            }

        }

    }

}