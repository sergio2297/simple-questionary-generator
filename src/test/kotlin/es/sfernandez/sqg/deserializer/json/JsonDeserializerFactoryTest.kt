package es.sfernandez.sqg.deserializer.json

import es.sfernandez.sqg.deserializer.DeserializationException
import es.sfernandez.sqg.deserializer.json.question.*
import es.sfernandez.sqg.deserializer.json.question.contents.*
import es.sfernandez.sqg.beans.contents.*
import es.sfernandez.sqg.beans.question.explanations.Explanation
import es.sfernandez.sqg.beans.question.problems.Problem
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import kotlin.reflect.KClass

class JsonDeserializerFactoryTest {

    //---- Constants and Definitions ----
    data class KClassWithJsonDeserializer(
        val clazz: KClass<*>,
        val deserializerClazz: KClass<*>,
    )

    //---- Attributes ----
    private val deserializerFactory = JsonDeserializerFactory()

    //---- Tests ----
    companion object {
        @JvmStatic
        fun deserializersAvailable(): List<KClassWithJsonDeserializer> {
            return listOf(
                KClassWithJsonDeserializer(GroupOfContents::class, GroupOfContentsJsonDeserializer::class),
                KClassWithJsonDeserializer(Text::class, TextJsonDeserializer::class),
                KClassWithJsonDeserializer(Sound::class, SoundJsonDeserializer::class),
                KClassWithJsonDeserializer(Image::class, ImageJsonDeserializer::class),
                KClassWithJsonDeserializer(Video::class, VideoJsonDeserializer::class),

                KClassWithJsonDeserializer(Problem::class, ProblemJsonDeserializer::class),
//                KClassWithJsonDeserializer(Answer::class, AnswerJsonDeserializer::class),
//                KClassWithJsonDeserializer(Choice::class, ChoiceJsonDeserializer::class),
                KClassWithJsonDeserializer(Explanation::class, ExplanationJsonDeserializer::class),

//                KClassWithJsonDeserializer(Question::class, QuestionJsonDeserializer::class),
            )
        }
    }

    @ParameterizedTest
    @MethodSource("deserializersAvailable")
    fun deserializerForSpecificClass_returnsCorrectDeserializerTest(classDeserializer: KClassWithJsonDeserializer) {
        val clazz = classDeserializer.clazz
        val expectedDeserializerClazz = classDeserializer.deserializerClazz

        val deserializer = deserializerFactory.deserializerFor(clazz)

        assertThat(deserializer::class).isEqualTo(expectedDeserializerClazz)
    }

    @Test
    fun deserializerForUnspecifiedClass_throwsExceptionTest() {
        val clazz = Any::class

        assertThrows<DeserializationException> {deserializerFactory.deserializerFor(clazz)}
    }

}