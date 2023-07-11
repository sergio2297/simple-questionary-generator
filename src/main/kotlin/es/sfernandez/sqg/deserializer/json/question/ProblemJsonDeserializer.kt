package es.sfernandez.sqg.deserializer.json.question

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import es.sfernandez.sqg.deserializer.json.JsonDeserializer
import es.sfernandez.sqg.deserializer.json.JsonKeys
import es.sfernandez.sqg.deserializer.json.question.contents.GroupOfContentsJsonDeserializer
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

    private inner class CustomDeserializer :
        StdDeserializer<Problem>(mappedClass),
        NeedsToDeserializeContents {

        override val deserializer: GroupOfContentsJsonDeserializer
            get() = groupOfContentsDeserializer
        override val contentsKey: String
            get() = JsonKeys.Problem.CONTENTS

        override fun deserialize(parser : JsonParser?, ctxt : DeserializationContext?) : Problem {
            val node = extractJsonNode(parser)

            val problem = Problem()

            deserializeContentsIn(problem, node, log)

            return problem
        }

    }

}