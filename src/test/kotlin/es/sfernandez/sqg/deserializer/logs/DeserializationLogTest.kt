package es.sfernandez.sqg.deserializer.logs

import es.sfernandez.sqg.utilities.Fixtures
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.mockito.Mockito

class DeserializationLogTest {

    //---- Attributes ----
    private val regex = Regex("""\[(DEBUG|WARNING|ERROR)] (.*?):\nContext:\n(.*)""")

    //---- Methods ----
    private fun createArbitraryDeserializationLog() : DeserializationLog {
        return DeserializationLog(DeserializationLog.Level.ERROR,
            Fixtures.SOME_TEXT_1, mockDeserializationContext())
    }

    private fun mockDeserializationContext() : DeserializationContext {
        val context = Mockito.mock(DeserializationContext::class.java)
        Mockito.`when`(context.toString()).thenReturn(Fixtures.SOME_TEXT_2)
        return context
    }

    //---- Tests ----
    @Test
    fun toString_matchesLogRegexPatternTest() {
        val logMessage = createArbitraryDeserializationLog()

        val matchResult = regex.matchEntire(logMessage.toString())
            ?: fail("DeserializationLog.toString() does not match ${regex.pattern}")

        assertThat(matchResult.groupValues[1]).isEqualTo(logMessage.level.name)
        assertThat(matchResult.groupValues[2]).isEqualTo(logMessage.msg)
        assertThat(matchResult.groupValues[3]).isEqualTo(logMessage.context.toString())
    }

}