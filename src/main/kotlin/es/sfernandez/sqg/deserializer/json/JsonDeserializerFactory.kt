package es.sfernandez.sqg.deserializer.json

import es.sfernandez.sqg.deserializer.Deserializer
import es.sfernandez.sqg.deserializer.DeserializerFactory
import es.sfernandez.sqg.deserializer.json.question.ExplanationJsonDeserializer
import es.sfernandez.sqg.deserializer.json.question.ProblemJsonDeserializer
import es.sfernandez.sqg.deserializer.json.question.contents.*
import es.sfernandez.sqg.beans.contents.*
import es.sfernandez.sqg.beans.question.Question
import es.sfernandez.sqg.beans.question.answers.Answer
import es.sfernandez.sqg.beans.question.answers.choices.Choice
import es.sfernandez.sqg.beans.question.explanations.Explanation
import es.sfernandez.sqg.beans.question.problems.Problem

class JsonDeserializerFactory : DeserializerFactory {

    override fun createGroupOfContentsDeserializer(): Deserializer<GroupOfContents> {
        return GroupOfContentsJsonDeserializer()
    }

    override fun createTextDeserializer(): Deserializer<Text> {
        return TextJsonDeserializer()
    }

    override fun createSoundDeserializer(): Deserializer<Sound> {
        return SoundJsonDeserializer()
    }

    override fun createImageDeserializer(): Deserializer<Image> {
        return ImageJsonDeserializer()
    }

    override fun createVideoDeserializer(): Deserializer<Video> {
        return VideoJsonDeserializer()
    }

    override fun createProblemDeserializer() : JsonDeserializer<Problem> {
        return ProblemJsonDeserializer()
    }

    override fun createAnswerDeserializer(): Deserializer<Answer<*>> {
        TODO("Not yet implemented")
    }

    override fun createChoiceDeserializer(): Deserializer<Choice> {
        TODO("Not yet implemented")
    }

    override fun createExplanationDeserializer(): Deserializer<Explanation> {
        return ExplanationJsonDeserializer()
    }

    override fun createQuestionDeserializer(): Deserializer<Question> {
        TODO("Not yet implemented")
    }

}