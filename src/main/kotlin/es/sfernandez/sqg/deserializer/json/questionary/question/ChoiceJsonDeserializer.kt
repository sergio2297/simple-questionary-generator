package es.sfernandez.sqg.deserializer.json.questionary.question

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import es.sfernandez.sqg.beans.contents.UnknownContent
import es.sfernandez.sqg.beans.question.answers.Choice
import es.sfernandez.sqg.deserializer.json.JsonDeserializer
import es.sfernandez.sqg.deserializer.json.JsonKeys
import es.sfernandez.sqg.deserializer.json.questionary.contents.ContentJsonDeserializer


class ChoiceJsonDeserializer : JsonDeserializer<Choice> {

    //---- Attributes ----
    private val contentDeserializer: ContentJsonDeserializer

    //---- Constructor ----
    constructor() : this(ContentJsonDeserializer())

    internal constructor(contentDeserializer: ContentJsonDeserializer) : super(Choice::class.java) {
        this.contentDeserializer = contentDeserializer
    }

    //---- Methods ----
    override fun createDeserializer(): StdDeserializer<Choice> {
        return CustomDeserializer()
    }

    inner class CustomDeserializer : StdDeserializer<Choice>(mappedClass) {
        override fun deserialize(parser: JsonParser?, ctxt: DeserializationContext?): Choice {
            val node = extractJsonNode(parser)

            val id = extractText(node, JsonKeys.Choice.ID)
            val content = extractObject(node, JsonKeys.Choice.CONTENT, contentDeserializer, UnknownContent())

            return Choice(id, content)
        }

    }

}