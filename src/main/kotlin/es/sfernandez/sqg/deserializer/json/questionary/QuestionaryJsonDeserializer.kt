package es.sfernandez.sqg.deserializer.json.questionary

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import es.sfernandez.sqg.beans.Questionary
import es.sfernandez.sqg.beans.contents.Image
import es.sfernandez.sqg.deserializer.json.JsonDeserializer
import es.sfernandez.sqg.deserializer.json.JsonKeys
import es.sfernandez.sqg.deserializer.json.questionary.contents.ImageJsonDeserializer
import es.sfernandez.sqg.deserializer.json.questionary.question.QuestionJsonDeserializer

class QuestionaryJsonDeserializer : JsonDeserializer<Questionary> {

    //---- Attributes ----
    private val imageDeserializer: ImageJsonDeserializer
    private val questionDeserializer: QuestionJsonDeserializer

    //---- Constructor ----
    internal constructor(imageDeserializer: ImageJsonDeserializer,
                         questionDeserializer: QuestionJsonDeserializer) : super(Questionary::class.java) {
        this.imageDeserializer = imageDeserializer
        this.questionDeserializer = questionDeserializer
    }

    internal constructor() : super(Questionary::class.java) {
        this.imageDeserializer = ImageJsonDeserializer()
        this.questionDeserializer = QuestionJsonDeserializer()
    }

    //---- Methods ----
    override fun createDeserializer(): StdDeserializer<Questionary> {
        return CustomDeserializer()
    }

    private inner class CustomDeserializer : StdDeserializer<Questionary>(mappedClass) {

        override fun deserialize(parser: JsonParser?, ctxt: DeserializationContext?): Questionary {
            val node = extractJsonNode(parser)

            val title = extractText(node, JsonKeys.Questionary.TITLE)
            val portrait = extractObject(node, JsonKeys.Questionary.PORTRAIT, imageDeserializer, Image())
            val questions = extractArrayOfObjects(node, JsonKeys.Questionary.QUESTIONS, questionDeserializer)

            return Questionary(title, portrait, questions)
        }

    }

}