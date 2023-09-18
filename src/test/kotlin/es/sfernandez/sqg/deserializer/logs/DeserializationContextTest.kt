package es.sfernandez.sqg.deserializer.logs

import es.sfernandez.sqg.deserializer.Deserializer
import es.sfernandez.sqg.utilities.Fixtures
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Test

class DeserializationContextTest {

    //---- Attributes ----
    private val regex = Regex("""Deserializer: (.*)\nInput: (.*)""")

    //---- Fixtures ----
    private class FooDeserializer : Deserializer<Any> {
        override fun deserialize(text: String): Any {
            return Any()
        }
    }

    //---- Methods ----
    private fun createArbitraryDeserializationContext() : DeserializationContext {
        return DeserializationContext(Fixtures.SOME_TEXT_1, FooDeserializer::class.java)
    }

    //---- Tests ----
    @Test
    fun toString_matchesLogRegexPatternTest() {
        val context = createArbitraryDeserializationContext()

        val matchResult = regex.matchEntire(context.toString())
            ?: fail("DeserializationContext.toString() does not match ${regex.pattern}")

        Assertions.assertThat(matchResult.groupValues[1]).isEqualTo(context.deserializerClass.simpleName)
        Assertions.assertThat(matchResult.groupValues[2]).isEqualTo(context.input)
    }

}