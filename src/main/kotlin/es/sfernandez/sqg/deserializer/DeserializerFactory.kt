package es.sfernandez.sqg.deserializer

import es.sfernandez.sqg.beans.Questionnaire
import es.sfernandez.sqg.beans.contents.*
import es.sfernandez.sqg.beans.question.Question
import es.sfernandez.sqg.beans.question.answers.Answer
import es.sfernandez.sqg.beans.question.answers.Choice
import es.sfernandez.sqg.beans.question.answers.correction.AnswerCorrection
import es.sfernandez.sqg.beans.question.answers.input.AnswerInput
import es.sfernandez.sqg.beans.question.explanations.Explanation
import es.sfernandez.sqg.beans.question.problems.Problem
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
        if(clazz == GroupOfContents::class)
            return createGroupOfContentsDeserializer()
        if(clazz == Text::class)
            return createTextDeserializer()
        if(clazz == Sound::class)
            return createSoundDeserializer()
        if(clazz == Image::class)
            return createImageDeserializer()
        if(clazz == Video::class)
            return createVideoDeserializer()

        if(clazz == Problem::class)
            return createProblemDeserializer()
        if(clazz == Answer::class)
            return createAnswerDeserializer()
        if(clazz == AnswerCorrection::class)
            return createAnswerCorrectionDeserializer()
        if(clazz == AnswerInput::class)
            return createAnswerInputDeserializer()
        if(clazz == Choice::class)
            return createChoiceDeserializer()
        if(clazz == Explanation::class)
            return createExplanationDeserializer()

        if(clazz == Question::class)
            return createQuestionDeserializer()
        if(clazz == Questionnaire::class)
            return createQuestionnaireDeserializer()

        throw DeserializationException("Error. Given class ${clazz.simpleName} isn't deserializable.")
    }

    fun createGroupOfContentsDeserializer(): Deserializer<GroupOfContents>

    fun createTextDeserializer(): Deserializer<Text>

    fun createSoundDeserializer(): Deserializer<Sound>

    fun createImageDeserializer(): Deserializer<Image>

    fun createVideoDeserializer(): Deserializer<Video>

    fun createProblemDeserializer() : Deserializer<Problem>

    fun createAnswerDeserializer(): Deserializer<Answer>

    fun createAnswerCorrectionDeserializer(): Deserializer<AnswerCorrection>

    fun createAnswerInputDeserializer(): Deserializer<AnswerInput>

    fun createChoiceDeserializer(): Deserializer<Choice>

    fun createExplanationDeserializer(): Deserializer<Explanation>

    fun createQuestionDeserializer(): Deserializer<Question>

    fun createQuestionnaireDeserializer(): Deserializer<Questionnaire>

}
