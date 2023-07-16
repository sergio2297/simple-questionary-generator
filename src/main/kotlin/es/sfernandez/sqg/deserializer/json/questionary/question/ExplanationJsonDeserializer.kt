package es.sfernandez.sqg.deserializer.json.questionary.question

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import es.sfernandez.sqg.deserializer.json.JsonDeserializer
import es.sfernandez.sqg.deserializer.json.JsonKeys
import es.sfernandez.sqg.deserializer.json.questionary.contents.GroupOfContentsJsonDeserializer
import es.sfernandez.sqg.beans.question.explanations.Explanation


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

    private inner class CustomDeserializer :
        StdDeserializer<Explanation>(mappedClass),
        NeedsToDeserializeGroupOfContents {

        override val deserializer: GroupOfContentsJsonDeserializer
            get() = groupOfContentsDeserializer
        override val contentsKey: String
            get() = JsonKeys.Explanation.CONTENTS

        override fun deserialize(parser : JsonParser?, ctxt : DeserializationContext?) : Explanation {
            val node = extractJsonNode(parser)

            val explanation = Explanation()

            deserializeContentsIn(explanation, node, log)

            return explanation
        }

    }

}