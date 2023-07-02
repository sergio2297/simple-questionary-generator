package es.sfernandez.sqg.deserializer

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

/**
 * Deserializer factory provides a method for creating [Deserializer] for a given [KClass]
 */
interface DeserializerFactory {

    /**
     * @param clazz Class used to create the Deserializer
     * @return a new [Deserializer] for the given class
     * @throws DeserializationException if the factory doesn't define a Deserializer for the given class
     */
    fun <T : Any> deserializerFor(clazz : KClass<T>) : Deserializer<out Any> {
        if(clazz == Text::class)
            return createTextDeserializer()
        if(clazz == Sound::class)
            return createSoundDeserializer()
        if(clazz == Image::class)
            return createImageDeserializer()

        if(clazz == Problem::class)
            return createProblemDeserializer()
        if(clazz == Answer::class)
            return createAnswerDeserializer()
        if(clazz == Choice::class)
            return createChoiceDeserializer()
        if(clazz == Reply::class)
            return createReplyDeserializer()
        if(clazz == Explanation::class)
            return createExplanationDeserializer()

        if(clazz == Question::class)
            return createQuestionDeserializer()

        throw DeserializationException("Error. Given class ${clazz.simpleName} isn't deserializable.")
    }

    fun createTextDeserializer(): Deserializer<Text>

    fun createSoundDeserializer(): Deserializer<Sound>

    fun createImageDeserializer(): Deserializer<Image>

    fun createProblemDeserializer() : Deserializer<Problem>

    fun createAnswerDeserializer(): Deserializer<Answer<*>>

    fun createChoiceDeserializer(): Deserializer<Choice>

    fun createReplyDeserializer() : Deserializer<Reply<*>>

    fun createExplanationDeserializer(): Deserializer<Explanation>

    fun createQuestionDeserializer(): Deserializer<Question>

}