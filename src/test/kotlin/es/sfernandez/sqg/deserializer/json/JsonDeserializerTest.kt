package es.sfernandez.sqg.deserializer.json

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import es.sfernandez.sqg.BasicFixtures
import es.sfernandez.sqg.deserializer.logs.DeserializationLog
import es.sfernandez.sqg.deserializer.logs.DeserializationLogFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import kotlin.test.BeforeTest

class JsonDeserializerTest {

    //---- Constants and Definitions ----
    private class FooJsonDeserializer : JsonDeserializer<Any>(Any::class.java) {

        override fun createDeserializer(): StdDeserializer<Any> {
            return object : StdDeserializer<Any>(mappedClass) {
                override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): Any {
                    log.debug("")
                    return Any()
                }
            }
        }

        fun getSupperMappedClass() : Class<Any> {
            return super.mappedClass
        }

        fun setLogFactory(logFactory: DeserializationLogFactory) {
            log.logFactory = logFactory
        }

        fun doExtractTextFromSuper(node: JsonNode, key: String, defaultValue: String = "") : String {
            return super.extractText(node, key, defaultValue)
        }

        fun doExtractBoolFromSuper(node: JsonNode, key: String, defaultValue: Boolean = false) : Boolean {
            return super.extractBool(node, key, defaultValue)
        }

        inline fun <reified T : Enum<T>> doExtractEnumFromSuper(node: JsonNode, key: String, defaultValue: T) : T {
            return super.extractEnum(node, key, defaultValue)
        }

        fun doDumpLogsFrom(deserializer: JsonDeserializer<*>) {
            super.dumpLogsFrom(deserializer)
        }

    }

    //---- Attributes ----
    private lateinit var deserializer : FooJsonDeserializer
    private lateinit var logFactory: DeserializationLogFactory

    //---- Fixtures ----
    private val someKey = BasicFixtures.SOME_TEXT_1
    private val defaultEnumValue = BasicFixtures.FooEnum.FOO_4

    //---- Configuration ----
    @BeforeTest
    fun setup() {
        deserializer = FooJsonDeserializer()

        logFactory = mockLogFactory()
        deserializer.setLogFactory(logFactory)
    }

    //---- Methods ----
    private fun mockJsonNode() : JsonNode {
        return Mockito.mock(JsonNode::class.java)
    }

    private fun mockJsonNodeWithKey(key: String, value: JsonNode) : JsonNode {
        val node = mockJsonNode()
        Mockito.`when`(node.get(key)).thenReturn(value)
        return node
    }

    private fun mockJsonNodeWithoutKey(key: String) : JsonNode {
        val node = mockJsonNode()
        Mockito.`when`(node.get(key)).thenReturn(null)
        return node
    }

    private fun mockNotBooleanJsonNode() : JsonNode {
        val node = mockJsonNode()
        Mockito.`when`(node.isBoolean).thenReturn(false)
        return node
    }

    private fun mockBooleanJsonNode(value: Boolean) : JsonNode {
        val node = mockJsonNode()
        Mockito.`when`(node.isBoolean).thenReturn(true)
        Mockito.`when`(node.asBoolean()).thenReturn(value)
        return node
    }

    private fun mockNotTextJsonNode() : JsonNode {
        val node = mockJsonNode()
        Mockito.`when`(node.isTextual).thenReturn(false)
        return node
    }

    private fun mockTextJsonNode(value: String) : JsonNode {
        val node = mockJsonNode()
        Mockito.`when`(node.isTextual).thenReturn(true)
        Mockito.`when`(node.asText()).thenReturn(value)
        return node
    }

    private fun mockLogFactory() : DeserializationLogFactory {
        return Mockito.mock(DeserializationLogFactory::class.java)
    }

    private fun mockLog() : DeserializationLog {
        return Mockito.mock(DeserializationLog::class.java)
    }

    private fun mockLogFactoryWarningMethod() : DeserializationLog {
        val log = mockLog()
        Mockito.`when`(logFactory.warning(anyString())).thenReturn(log)
        return log
    }

    //---- Tests ----
    @Test
    fun construction_assignsMappedClass_correctlyTest() {
        val deserializer = FooJsonDeserializer()

        assertThat(deserializer.getSupperMappedClass()).isEqualTo(Any::class.java)
    }

    @Test
    fun logsFromDifferentDeserialization_haveDifferentContextTest() {
        val context1 = "{}"
        val context2 = "[]"

        deserializer.deserialize(context1)
        deserializer.deserialize(context2)

        assertThat(deserializer.logs()[0].context.input).isEqualTo(context1)
        assertThat(deserializer.logs()[1].context.input).isEqualTo(context2)
    }

    @Test
    fun extractBool_fromBooleanNode_returnsCorrectBooleanValueTest() {
        val expectedValue = true
        val jsonNode = mockJsonNodeWithKey(someKey, mockBooleanJsonNode(expectedValue))

        val returnedValue = deserializer.doExtractBoolFromSuper(jsonNode, someKey)

        assertThat(returnedValue).isEqualTo(expectedValue)
    }

    @Test
    fun extractBoolDefaultValue_isFalseTest() {
        val jsonNode = mockJsonNodeWithoutKey(someKey)

        val returnedValue = deserializer.doExtractBoolFromSuper(jsonNode, someKey)

        assertThat(returnedValue).isFalse()
    }

    @Test
    fun extractBool_fromNode_withoutSearchedKey_returnsDefaultValueTest() {
        val defaultValue = true
        val jsonNode = mockJsonNodeWithoutKey(someKey)

        val returnedValue = deserializer.doExtractBoolFromSuper(jsonNode, someKey, defaultValue)

        assertThat(returnedValue).isEqualTo(defaultValue)
    }

    @Test
    fun extractBool_fromNode_withoutSearchedKey_addsWarningLogTest() {
        val jsonNode = mockJsonNodeWithoutKey(someKey)
        val expectedLog = mockLogFactoryWarningMethod()

        deserializer.doExtractBoolFromSuper(jsonNode, someKey)

        assertThat(deserializer.logs()).contains(expectedLog)
    }

    @Test
    fun extractBool_fromNotBooleanNode_returnsDefaultBooleanValueTest() {
        val defaultValue = true
        val jsonNode = mockJsonNodeWithKey(someKey, mockNotBooleanJsonNode())

        val returnedValue = deserializer.doExtractBoolFromSuper(jsonNode, someKey, defaultValue)

        assertThat(returnedValue).isEqualTo(defaultValue)
    }

    @Test
    fun extractBool_fromNotBooleanNode_addsWarningLogTest() {
        val jsonNode = mockJsonNodeWithKey(someKey, mockNotBooleanJsonNode())
        val expectedLog = mockLogFactoryWarningMethod()

        deserializer.doExtractBoolFromSuper(jsonNode, someKey)

        assertThat(deserializer.logs()).contains(expectedLog)
    }

    @Test
    fun extractText_fromTextualNode_returnsCorrectTextValueTest() {
        val expectedValue = BasicFixtures.SOME_TEXT_1
        val jsonNode = mockJsonNodeWithKey(someKey, mockTextJsonNode(expectedValue))

        val returnedValue = deserializer.doExtractTextFromSuper(jsonNode, someKey)

        assertThat(returnedValue).isEqualTo(expectedValue)
    }

    @Test
    fun extractTextDefaultValue_isEmptyStringTest() {
        val jsonNode = mockJsonNodeWithoutKey(someKey)

        val returnedValue = deserializer.doExtractTextFromSuper(jsonNode, someKey)

        assertThat(returnedValue).isEmpty()
    }

    @Test
    fun extractText_fromNode_withoutSearchedKey_returnsDefaultValueTest() {
        val defaultValue = BasicFixtures.SOME_TEXT_1
        val jsonNode = mockJsonNodeWithoutKey(someKey)

        val returnedValue = deserializer.doExtractTextFromSuper(jsonNode, someKey, defaultValue)

        assertThat(returnedValue).isEqualTo(defaultValue)
    }

    @Test
    fun extractText_fromNode_withoutSearchedKey_addsWarningLogTest() {
        val jsonNode = mockJsonNodeWithoutKey(someKey)
        val expectedLog = mockLogFactoryWarningMethod()

        deserializer.doExtractTextFromSuper(jsonNode, someKey)

        assertThat(deserializer.logs()).contains(expectedLog)
    }

    @Test
    fun extractText_fromNotTextualNode_returnsDefaultTextValueTest() {
        val defaultValue = BasicFixtures.SOME_TEXT_1
        val jsonNode = mockJsonNodeWithKey(someKey, mockNotTextJsonNode())

        val returnedValue = deserializer.doExtractTextFromSuper(jsonNode, someKey, defaultValue)

        assertThat(returnedValue).isEqualTo(defaultValue)
    }

    @Test
    fun extractText_fromNotTextualNode_addsWarningLogTest() {
        val jsonNode = mockJsonNodeWithKey(someKey, mockNotTextJsonNode())
        val expectedLog = mockLogFactoryWarningMethod()

        deserializer.doExtractTextFromSuper(jsonNode, someKey)

        assertThat(deserializer.logs()).contains(expectedLog)
    }




    @Test
    fun extractEnum_fromTextualNode_returnsCorrectEnumValueTest() {
        val expectedValue = BasicFixtures.FooEnum.FOO_1
        val jsonNode = mockJsonNodeWithKey(someKey, mockTextJsonNode(expectedValue.name))

        val returnedValue = deserializer.doExtractEnumFromSuper(jsonNode, someKey, defaultEnumValue)

        assertThat(returnedValue).isEqualTo(expectedValue)
    }

    @Test
    fun extractEnum_fromNode_withoutSearchedKey_returnsDefaultValueTest() {
        val defaultValue = BasicFixtures.FooEnum.FOO_1
        val jsonNode = mockJsonNodeWithoutKey(someKey)

        val returnedValue = deserializer.doExtractEnumFromSuper(jsonNode, someKey, defaultValue)

        assertThat(returnedValue).isEqualTo(defaultValue)
    }

    @Test
    fun extractEnum_fromNode_withoutSearchedKey_addsWarningLogTest() {
        val jsonNode = mockJsonNodeWithoutKey(someKey)
        val expectedLog = mockLogFactoryWarningMethod()

        deserializer.doExtractEnumFromSuper(jsonNode, someKey, defaultEnumValue)

        assertThat(deserializer.logs()).contains(expectedLog)
    }

    @Test
    fun extractEnum_fromNotTextualNode_returnsDefaultEnumValueTest() {
        val defaultValue = BasicFixtures.FooEnum.FOO_1
        val jsonNode = mockJsonNodeWithKey(someKey, mockNotTextJsonNode())

        val returnedValue = deserializer.doExtractEnumFromSuper(jsonNode, someKey, defaultValue)

        assertThat(returnedValue).isEqualTo(defaultValue)
    }

    @Test
    fun extractEnum_fromNotTextualNode_addsWarningLogTest() {
        val jsonNode = mockJsonNodeWithKey(someKey, mockNotTextJsonNode())
        val expectedLog = mockLogFactoryWarningMethod()

        deserializer.doExtractEnumFromSuper(jsonNode, someKey, defaultEnumValue)

        assertThat(deserializer.logs()).contains(expectedLog)
    }

    @Test
    fun extractEnum_fromTextualNode_withUndefinedEnumConstant_returnsDefaultEnumValueTest() {
        val defaultValue = BasicFixtures.FooEnum.FOO_1
        val jsonNode = mockJsonNodeWithKey(someKey, mockTextJsonNode(BasicFixtures.SOME_TEXT_1))

        val returnedValue = deserializer.doExtractEnumFromSuper(jsonNode, someKey, defaultValue)

        assertThat(returnedValue).isEqualTo(defaultValue)
    }

    @Test
    fun extractEnum_fromTextualNode_withUndefinedEnumConstant_addsWarningLogTest() {
        val jsonNode = mockJsonNodeWithKey(someKey, mockTextJsonNode(BasicFixtures.SOME_TEXT_1))
        val expectedLog = mockLogFactoryWarningMethod()

        deserializer.doExtractEnumFromSuper(jsonNode, someKey, defaultEnumValue)

        assertThat(deserializer.logs()).contains(expectedLog)
    }

    @Test
    fun dumpLogsFrom_worksTest() {
        val someLogs = arrayOf(mockLog(), mockLog())
        val anotherDeserializer = Mockito.mock(JsonDeserializer::class.java)
        Mockito.`when`(anotherDeserializer.logs()).thenReturn(someLogs)

        deserializer.doDumpLogsFrom(anotherDeserializer)

        assertThat(deserializer.logs()).contains(*someLogs)
    }

}