package es.sfernandez.sqg.deserializer.json.questionnaire.contents

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import es.sfernandez.sqg.beans.contents.Content
import es.sfernandez.sqg.beans.contents.ContentType
import es.sfernandez.sqg.beans.contents.UnknownContent
import es.sfernandez.sqg.deserializer.DeserializationException
import es.sfernandez.sqg.deserializer.json.JsonDeserializer

class ContentJsonDeserializer : JsonDeserializer<Content> {

    //---- Attributes ----
    private val textDeserializer : TextJsonDeserializer
    private val soundDeserializer : SoundJsonDeserializer
    private val videoDeserializer : VideoJsonDeserializer
    private val imageDeserializer : ImageJsonDeserializer

    //---- Constructor ----
    constructor() : super(Content::class.java) {
        textDeserializer = TextJsonDeserializer()
        soundDeserializer = SoundJsonDeserializer()
        videoDeserializer = VideoJsonDeserializer()
        imageDeserializer = ImageJsonDeserializer()
    }

    internal constructor(textDeserializer: TextJsonDeserializer,
                         soundDeserializer: SoundJsonDeserializer,
                         videoDeserializer: VideoJsonDeserializer,
                         imageDeserializer: ImageJsonDeserializer
    ) : super(Content::class.java) {
        this.textDeserializer = textDeserializer
        this.soundDeserializer = soundDeserializer
        this.videoDeserializer = videoDeserializer
        this.imageDeserializer = imageDeserializer
    }

    //---- Methods ----
    override fun createDeserializer() : StdDeserializer<Content> {
        return CustomDeserializer()
    }

    private inner class CustomDeserializer : StdDeserializer<Content>(mappedClass) {
        override fun deserialize(parser: JsonParser?, ctxt: DeserializationContext?) : Content {
            val jsonNode = extractJsonNode(parser)

            if(!jsonNode.isObject) {
                log.warning("Error. ${ContentJsonDeserializer::class.simpleName} can only deserialize json objects")
                return UnknownContent()
            }

            if(!nodeIsValidContentType(jsonNode)) {
                log.warning("Error. ${ContentJsonDeserializer::class.simpleName} can't deserialize json received." +
                        " This may be due to an incorrect content key or a json object with more " +
                        "than one content json object. Unknown content returned instead.")
                return UnknownContent()
            }

            val concreteDeserializer = deserializerFor(contentTypeByJsonName(firstFieldName(jsonNode)))
            val content = concreteDeserializer.deserialize(jsonNode[firstFieldName(jsonNode)].toString())
            dumpLogsFrom(concreteDeserializer)

            return content
        }

    }

    fun nodeIsValidContentType(jsonNode : JsonNode) : Boolean {
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

    private fun contentTypeByJsonName(name: String): ContentType {
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