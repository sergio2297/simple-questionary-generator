package es.sfernandez.sqg.deserializer.logs

import es.sfernandez.sqg.utilities.Fixtures
import org.assertj.core.api.Assertions.assertThat
import org.mockito.Mockito
import kotlin.test.Test

class DeserializationLogFactoryTest {

    //---- Attributes ----
    private val factoryContext = mockContext()
    private val factory = DeserializationLogFactory(factoryContext)

    //---- Fixtures ----
    private val someMsg = Fixtures.SOME_TEXT_1

    //---- Methods ----
    private fun mockContext() : DeserializationContext {
        return Mockito.mock(DeserializationContext::class.java)
    }

    //---- Tests ----
    @Test
    fun debug_createsLogWith_debugLevelTest() {
        val log = factory.debug(someMsg)

        assertThat(log.level).isEqualTo(DeserializationLog.Level.DEBUG)
    }

    @Test
    fun debug_createsLogWith_messageCorrectlyTest() {
        val log = factory.debug(someMsg)

        assertThat(log.msg).isEqualTo(someMsg)
    }

    @Test
    fun debug_createsLogWith_contextCorrectlyTest() {
        val log = factory.debug(someMsg)

        assertThat(log.context).isSameAs(factoryContext)
    }

    @Test
    fun warning_createsLogWith_warningLevelTest() {
        val log = factory.warning(someMsg)

        assertThat(log.level).isEqualTo(DeserializationLog.Level.WARNING)
    }

    @Test
    fun warning_createsLogWith_messageCorrectlyTest() {
        val log = factory.warning(someMsg)

        assertThat(log.msg).isEqualTo(someMsg)
    }

    @Test
    fun warning_createsLogWith_contextCorrectlyTest() {
        val log = factory.warning(someMsg)

        assertThat(log.context).isSameAs(factoryContext)
    }

    @Test
    fun error_createsLogWith_errorLevelTest() {
        val log = factory.error(someMsg)

        assertThat(log.level).isEqualTo(DeserializationLog.Level.ERROR)
    }

    @Test
    fun error_createsLogWith_messageCorrectlyTest() {
        val log = factory.error(someMsg)

        assertThat(log.msg).isEqualTo(someMsg)
    }

    @Test
    fun error_createsLogWith_contextCorrectlyTest() {
        val log = factory.error(someMsg)

        assertThat(log.context).isSameAs(factoryContext)
    }

}