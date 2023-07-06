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
import es.sfernandez.sqg.model.question.problems.Problem


class ProblemJsonDeserializer : JsonDeserializer<Problem> {

    //--- Attributes ----
    private val groupOfContentsDeserializer: GroupOfContentsJsonDeserializer

    //----- Constructor ----
    constructor() : super(Problem::class.java) {
        groupOfContentsDeserializer = GroupOfContentsJsonDeserializer()
    }

    internal constructor(groupOfContentsDeserializer: GroupOfContentsJsonDeserializer) : super(Problem::class.java) {
        this.groupOfContentsDeserializer = groupOfContentsDeserializer
    }

    //---- Methods ----
    override fun createDeserializer() : StdDeserializer<Problem> {
        return CustomDeserializer()
    }

    private inner class CustomDeserializer : StdDeserializer<Problem>(mappedClass) {
        override fun deserialize(parser : JsonParser?, ctxt : DeserializationContext?) : Problem {
            val node = extractJsonNode(parser)

            val problem = Problem()

            deserializeContents(problem, node)

            return problem
        }

        private fun deserializeContents(hasContents : HasContents, node: JsonNode) {
            val jsonContents = node.get(JsonKeys.Problem.CONTENTS) ?: return

            if(!jsonContents.isArray) throw DeserializationException("Error. Contents must be defined in an array")

            val deserializedContents = groupOfContentsDeserializer.deserialize(jsonContents.toString())
            hasContents.groupOfContents.add(*deserializedContents.contents().toTypedArray())
        }

    }

}