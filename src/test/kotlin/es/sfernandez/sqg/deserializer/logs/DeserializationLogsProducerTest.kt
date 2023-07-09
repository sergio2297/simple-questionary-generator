package es.sfernandez.sqg.deserializer.logs

import es.sfernandez.sqg.BasicFixtures
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import kotlin.test.BeforeTest

class DeserializationLogsProducerTest {

    //---- Attributes ----
    private val producer = DeserializationLogsProducer()

    //---- Fixtures ----
    private lateinit var factory : DeserializationLogFactory
    private val someMsg = BasicFixtures.SOME_TEXT_1
    private val someProperty = BasicFixtures.SOME_TEXT_2
    private val someReplacement = BasicFixtures.SOME_TEXT_3

    //---- Configuration ----
    @BeforeTest
    fun setup() {
        factory = mockFactory()
        producer.logFactory = factory
    }

    //---- Methods ----
    private fun mockFactory() : DeserializationLogFactory {
        return Mockito.mock(DeserializationLogFactory::class.java)
    }

    private fun mockDeserializationLog() : DeserializationLog {
        return Mockito.mock(DeserializationLog::class.java)
    }

    //---- Tests ----
    @Test
    fun afterConstruction_producerHasNoLogsTest() {
        val producer = DeserializationLogsProducer()

        assertThat(producer.logs()).isEmpty()
    }

    @Test
    fun afterClear_producerHasNoLogsTest() {
        Mockito.`when`(factory.debug(anyString())).thenReturn(mockDeserializationLog())
        producer.debug(someMsg)
        assertThat(producer.logs()).isNotEmpty()

        producer.clear()

        assertThat(producer.logs()).isEmpty()
    }

    @Test
    fun debug_storesDeserializationLog_createdFormFactoryTest() {
        val log = mockDeserializationLog()
        Mockito.`when`(factory.debug(anyString())).thenReturn(log)

        producer.debug(someMsg)

        assertThat(producer.logs()).contains(log)
    }

    @Test
    fun debug_requestFromFactory_debugLogTest() {
        producer.debug(someMsg)

        Mockito.verify(factory).debug(someMsg)
    }

    @Test
    fun warning_storesDeserializationLog_createdFormFactoryTest() {
        val log = mockDeserializationLog()
        Mockito.`when`(factory.warning(anyString())).thenReturn(log)

        producer.warning(someMsg)

        assertThat(producer.logs()).contains(log)
    }

    @Test
    fun warning_requestFromFactory_warningLogTest() {
        producer.warning(someMsg)

        Mockito.verify(factory).warning(someMsg)
    }

    @Test
    fun warningMissingProperty_storesDeserializationLog_createdFormFactoryTest() {
        val log = mockDeserializationLog()
        Mockito.`when`(factory.warning(anyString())).thenReturn(log)

        producer.warningMissingProperty(someProperty)

        assertThat(producer.logs()).contains(log)
    }

    @Test
    fun warningMissingProperty_requestFromFactory_warningLogTest() {
        producer.warningMissingProperty(someProperty, someReplacement)

        val msg = "Missing property \"$someProperty\". " +
                "Default value \"$someReplacement\" assigned."
        Mockito.verify(factory).warning(msg)
    }

    @Test
    fun warningMissingProperty_withoutReplacement_requestFromFactory_warningLogTest() {
        producer.warningMissingProperty(someProperty)

        val msg = "Missing property \"$someProperty\". " +
                "Default value \"\" assigned."
        Mockito.verify(factory).warning(msg)
    }

    @Test
    fun warningIncorrectType_storesDeserializationLog_createdFormFactoryTest() {
        val log = mockDeserializationLog()
        Mockito.`when`(factory.warning(anyString())).thenReturn(log)

        producer.warningIncorrectType(someProperty)

        assertThat(producer.logs()).contains(log)
    }

    @Test
    fun warningIncorrectType_requestFromFactory_warningLogTest() {
        producer.warningIncorrectType(someProperty, someReplacement)

        val msg = "Incorrect type used for property \"$someProperty\". " +
                "Default value \"$someReplacement\" assigned."
        Mockito.verify(factory).warning(msg)
    }

    @Test
    fun warningIncorrectType_withoutReplacement_requestFromFactory_warningLogTest() {
        producer.warningIncorrectType(someProperty)

        val msg = "Incorrect type used for property \"$someProperty\". " +
                "Default value \"\" assigned."
        Mockito.verify(factory).warning(msg)
    }

    @Test
    fun error_storesDeserializationLog_createdFormFactoryTest() {
        val log = mockDeserializationLog()
        Mockito.`when`(factory.error(anyString())).thenReturn(log)

        producer.error(someMsg)

        assertThat(producer.logs()).contains(log)
    }

    @Test
    fun error_requestFromFactory_errorLogTest() {
        producer.error(someMsg)

        Mockito.verify(factory).error(someMsg)
    }

    @Test
    fun errorMissingProperty_storesDeserializationLog_createdFormFactoryTest() {
        val log = mockDeserializationLog()
        Mockito.`when`(factory.error(anyString())).thenReturn(log)

        producer.errorMissingProperty(someProperty)

        assertThat(producer.logs()).contains(log)
    }

    @Test
    fun errorMissingProperty_requestFromFactory_errorLogTest() {
        producer.errorMissingProperty(someProperty)

        val msg = "Missing mandatory property \"$someProperty\"."
        Mockito.verify(factory).error(msg)
    }

    @Test
    fun errorIncorrectType_storesDeserializationLog_createdFormFactoryTest() {
        val log = mockDeserializationLog()
        Mockito.`when`(factory.error(anyString())).thenReturn(log)

        producer.errorIncorrectType(someProperty)

        assertThat(producer.logs()).contains(log)
    }

    @Test
    fun errorIncorrectType_requestFromFactory_errorLogTest() {
        producer.errorIncorrectType(someProperty)

        val msg = "Incorrect type used for property \"$someProperty\"."
        Mockito.verify(factory).error(msg)
    }

}