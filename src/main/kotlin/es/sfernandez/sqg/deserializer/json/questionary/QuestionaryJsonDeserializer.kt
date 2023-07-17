package es.sfernandez.sqg.deserializer.json.questionary

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import es.sfernandez.sqg.beans.Questionary
import es.sfernandez.sqg.deserializer.json.JsonDeserializer
import es.sfernandez.sqg.deserializer.json.JsonKeys
import es.sfernandez.sqg.deserializer.json.questionary.question.QuestionJsonDeserializer

class QuestionaryJsonDeserializer : JsonDeserializer<Questionary> {

    //---- Attributes ----
    private val questionDeserializer: QuestionJsonDeserializer

    //---- Constructor ----
    internal constructor(questionDeserializer: QuestionJsonDeserializer) : super(Questionary::class.java) {
        this.questionDeserializer = questionDeserializer
    }

    internal constructor() : super(Questionary::class.java) {
        this.questionDeserializer = QuestionJsonDeserializer()
    }

    //---- Methods ----
    override fun createDeserializer(): StdDeserializer<Questionary> {
        return CustomDeserializer()
    }

    private inner class CustomDeserializer : StdDeserializer<Questionary>(mappedClass) {

        override fun deserialize(parser: JsonParser?, ctxt: DeserializationContext?): Questionary {
            val node = extractJsonNode(parser)

            val questions = extractArrayOfObjects(node, JsonKeys.Questionary.QUESTIONS, questionDeserializer)

            return Questionary(questions)
        }

    }

}