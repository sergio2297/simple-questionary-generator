package es.sfernandez.sqg.deserializer.json

import es.sfernandez.sqg.deserializer.Deserializer
import es.sfernandez.sqg.deserializer.DeserializerFactory
import es.sfernandez.sqg.model.contents.Image
import es.sfernandez.sqg.model.contents.Sound
import es.sfernandez.sqg.model.contents.Text
import es.sfernandez.sqg.model.question.Question
import es.sfernandez.sqg.model.question.answers.Answer
import es.sfernandez.sqg.model.question.answers.choices.Choice
import es.sfernandez.sqg.model.question.answers.replies.Reply
import es.sfernandez.sqg.model.question.explanations.Explanation
import es.sfernandez.sqg.model.question.problems.Problem
import kotlin.reflect.KClass

class JsonDeserializerFactory : DeserializerFactory {

    override fun createTextDeserializer(): Deserializer<Text> {
        TODO("Not yet implemented")
    }

    override fun createSoundDeserializer(): Deserializer<Sound> {
        TODO("Not yet implemented")
    }

    override fun createImageDeserializer(): Deserializer<Image> {
        TODO("Not yet implemented")
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

    override fun createReplyDeserializer() : JsonDeserializer<Reply<*>> {
        return ReplyJsonDeserializer()
    }

    override fun createExplanationDeserializer(): Deserializer<Explanation> {
        TODO("Not yet implemented")
    }

    override fun createQuestionDeserializer(): Deserializer<Question> {
        TODO("Not yet implemented")
    }

}