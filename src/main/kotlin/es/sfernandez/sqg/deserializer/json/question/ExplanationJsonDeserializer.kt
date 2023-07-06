package es.sfernandez.sqg.deserializer.json.question

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import es.sfernandez.sqg.deserializer.DeserializationException
import es.sfernandez.sqg.deserializer.json.JsonDeserializer
import es.sfernandez.sqg.deserializer.json.JsonKeys
import es.sfernandez.sqg.deserializer.json.question.contents.GroupOfContentsJsonDeserializer
import es.sfernandez.sqg.model.contents.HasContents
import es.sfernandez.sqg.model.question.explanations.Explanation


class ExplanationJsonDeserializer : JsonDeserializer<Explanation> {

    //--- Attributes ----
    private val groupOfContentsDeserializer: GroupOfContentsJsonDeserializer

    //----- Constructor ----
    constructor() : super(Explanation::class.java) {
        groupOfContentsDeserializer = GroupOfContentsJsonDeserializer()
    }

    internal constructor(groupOfContentsDeserializer: GroupOfContentsJsonDeserializer) : super(Explanation::class.java) {
        this.groupOfContentsDeserializer = groupOfContentsDeserializer
    }

    //---- Methods ----
    override fun createDeserializer() : StdDeserializer<Explanation> {
        return CustomDeserializer()
    }

    private inner class CustomDeserializer : StdDeserializer<Explanation>(mappedClass) {
        override fun deserialize(parser : JsonParser?, ctxt : DeserializationContext?) : Explanation {
            val node = extractJsonNode(parser)

            val explanation = Explanation()

            deserializeContents(explanation, node)

            return explanation
        }

        private fun deserializeContents(hasContents : HasContents, node: JsonNode) {
            val jsonContents = node.get(JsonKeys.Explanation.CONTENTS) ?: return

            if(!jsonContents.isArray) throw DeserializationException("Error. Contents must be defined in an array")

            val deserializedContents = groupOfContentsDeserializer.deserialize(jsonContents.toString())
            hasContents.groupOfContents.add(*deserializedContents.contents().toTypedArray())
        }

    }

}