package es.sfernandez.sqg.deserializer.json.questionnaire

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import es.sfernandez.sqg.beans.Questionnaire
import es.sfernandez.sqg.beans.contents.Image
import es.sfernandez.sqg.deserializer.json.JsonDeserializer
import es.sfernandez.sqg.deserializer.json.JsonKeys
import es.sfernandez.sqg.deserializer.json.questionnaire.contents.ImageJsonDeserializer
import es.sfernandez.sqg.deserializer.json.questionnaire.question.QuestionJsonDeserializer

class QuestionnaireJsonDeserializer : JsonDeserializer<Questionnaire> {

    //---- Attributes ----
    private val imageDeserializer: ImageJsonDeserializer
    private val questionDeserializer: QuestionJsonDeserializer

    //---- Constructor ----
    internal constructor(imageDeserializer: ImageJsonDeserializer,
                         questionDeserializer: QuestionJsonDeserializer) : super(Questionnaire::class.java) {
        this.imageDeserializer = imageDeserializer
        this.questionDeserializer = questionDeserializer
    }

    internal constructor() : super(Questionnaire::class.java) {
        this.imageDeserializer = ImageJsonDeserializer()
        this.questionDeserializer = QuestionJsonDeserializer()
    }

    //---- Methods ----
    override fun createDeserializer(): StdDeserializer<Questionnaire> {
        return CustomDeserializer()
    }

    private inner class CustomDeserializer : StdDeserializer<Questionnaire>(mappedClass) {

        override fun deserialize(parser: JsonParser?, ctxt: DeserializationContext?): Questionnaire {
            val node = extractJsonNode(parser)

            val title = extractText(node, JsonKeys.Questionnaire.TITLE)
            val portrait = extractObject(node, JsonKeys.Questionnaire.PORTRAIT, imageDeserializer, Image())
            val questions = extractArrayOfObjects(node, JsonKeys.Questionnaire.QUESTIONS, questionDeserializer)

            return Questionnaire(title, portrait, questions)
        }

    }

}