package es.sfernandez.sqg.deserializer.json.questionary.question.answer

import es.sfernandez.sqg.beans.question.answers.*
import es.sfernandez.sqg.deserializer.json.JsonFixtures
import es.sfernandez.sqg.deserializer.json.JsonKeys
import es.sfernandez.sqg.deserializer.logs.DeserializationLog
import es.sfernandez.sqg.deserializer.logs.DeserializationLogUtilsForTests
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.any

class AnswerJsonDeserializerTest {

    //---- Attributes ----
    private var deserializer = AnswerJsonDeserializer()

    private lateinit var textJsonDeserializer: TextAnswerJsonDeserializer
    private lateinit var singleSelectionJsonDeserializer: SingleSelectionAnswerJsonDeserializer
    private lateinit var multipleSelectionJsonDeserializer: MultipleSelectionAnswerJsonDeserializer

    //---- Fixtures ----
    private val aTextAnswer = Mockito.mock(TextAnswer::class.java)
    private val aSingleSelectionAnswer = Mockito.mock(SingleSelectionAnswer::class.java)
    private val aMultipleSelectionAnswer = Mockito.mock(MultipleSelectionAnswer::class.java)

    //---- Configuration ----
    @BeforeEach
    fun setup() {
        deserializer = createMockedDeserializer()
    }

    //---- Methods ----
    private fun createNormalDeserializer() : AnswerJsonDeserializer {
        return AnswerJsonDeserializer()
    }

    private fun createMockedDeserializer() : AnswerJsonDeserializer {
        textJsonDeserializer = Mockito.mock(TextAnswerJsonDeserializer::class.java)
        singleSelectionJsonDeserializer = Mockito.mock(SingleSelectionAnswerJsonDeserializer::class.java)
        multipleSelectionJsonDeserializer = Mockito.mock(MultipleSelectionAnswerJsonDeserializer::class.java)
        return AnswerJsonDeserializer(textJsonDeserializer, singleSelectionJsonDeserializer,
            multipleSelectionJsonDeserializer)
    }

    private fun mockDeserializersToReturnEmptyLogs() {
        Mockito.lenient().`when`(textJsonDeserializer.logs()).thenReturn(arrayOf())
        Mockito.lenient().`when`(singleSelectionJsonDeserializer.logs()).thenReturn(arrayOf())
        Mockito.lenient().`when`(multipleSelectionJsonDeserializer.logs()).thenReturn(arrayOf())
    }

    private fun mockSomeDeserializationLog() : Array<DeserializationLog> {
        return generateSequence { Mockito.mock(DeserializationLog::class.java) }
            .take(3).toList().toTypedArray()
    }

    //---- Tests ----
    @Test
    fun deserialize_notJsonObject_returnUnknownAnswerTest() {
        val json = JsonFixtures.EMPTY_JSON_ARRAY
        deserializer = createNormalDeserializer()

        val content = deserializer.deserialize(json)

        Assertions.assertThat(content).isInstanceOf(UnknownAnswer::class.java)
    }

    @Test
    fun afterDeserialize_notJsonObject_logsHaveWarningTest() {
        val json = JsonFixtures.EMPTY_JSON_ARRAY
        deserializer = createNormalDeserializer()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(
            deserializer, AnswerJsonDeserializer::class.java.simpleName)
    }

    @Test
    fun deserialize_objectWithNotValidType_returnUnknownAnswerTest() {
        val json = """
             {
                 "not_answer_type": {}
             }"""
        deserializer = createNormalDeserializer()

        val content = deserializer.deserialize(json)

        Assertions.assertThat(content).isInstanceOf(UnknownAnswer::class.java)
    }

    @Test
    fun afterDeserialize_objectWithNotValidType_logsHaveWarningTest() {
        val json = """
             {
                 "not_answer_type": {}
             }"""
        deserializer = createNormalDeserializer()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(
            deserializer, AnswerJsonDeserializer::class.java.simpleName)
    }

    @Test
    fun deserialize_objectWithUnknownAnswerType_returnUnknownAnswerTest() {
        val json = """
             {
                "${JsonKeys.Answer.TYPE}": "${AnswerTypes.UNKNOWN.name}"
             }
             """
        deserializer = createNormalDeserializer()

        val content = deserializer.deserialize(json)

        Assertions.assertThat(content).isInstanceOf(UnknownAnswer::class.java)
    }

    @Test
    fun afterDeserialize_objectWithUnknownAnswerType_logsContainWarningTest() {
        val json = """
             {
                "${JsonKeys.Answer.TYPE}": "${AnswerTypes.UNKNOWN.name}"
             }
             """
        deserializer = createNormalDeserializer()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(
            deserializer, AnswerJsonDeserializer::class.java.simpleName)
    }

    @Test
    fun deserialize_objectWithTextAnswerType_returnTextAnswerTest() {
        mockDeserializersToReturnEmptyLogs()
        val json = """
            {
                "${JsonKeys.Answer.TYPE}": "${AnswerTypes.TEXT_INPUT.name}"
            }
            """
        Mockito.`when`(textJsonDeserializer.deserialize(any())).thenReturn(aTextAnswer)

        val answer = deserializer.deserialize(json)

        Assertions.assertThat(answer).isEqualTo(aTextAnswer)
    }

    @Test
    fun afterDeserialize_objectWithTextAnswerType_logsAreDumpTest() {
        val logs = mockSomeDeserializationLog()
        Mockito.`when`(textJsonDeserializer.logs()).thenReturn(logs)
        val json = """
            {
                "${JsonKeys.Answer.TYPE}": "${AnswerTypes.TEXT_INPUT.name}"
            }
            """
        Mockito.`when`(textJsonDeserializer.deserialize(any())).thenReturn(aTextAnswer)

        deserializer.deserialize(json)

        Assertions.assertThat(deserializer.logs()).contains(*logs)
    }

    @Test
    fun deserialize_objectWithSingleSelectionAnswerType_returnSingleSelectionAnswerTest() {
        mockDeserializersToReturnEmptyLogs()
        val json = """
            {
                "${JsonKeys.Answer.TYPE}": "${AnswerTypes.SINGLE_SELECTION.name}"
            }
            """
        Mockito.`when`(singleSelectionJsonDeserializer.deserialize(any())).thenReturn(aSingleSelectionAnswer)

        val answer = deserializer.deserialize(json)

        Assertions.assertThat(answer).isEqualTo(aSingleSelectionAnswer)
    }

    @Test
    fun afterDeserialize_objectWithSingleSelectionAnswerType_logsAreDumpTest() {
        val logs = mockSomeDeserializationLog()
        Mockito.`when`(singleSelectionJsonDeserializer.logs()).thenReturn(logs)
        val json = """
            {
                "${JsonKeys.Answer.TYPE}": "${AnswerTypes.SINGLE_SELECTION.name}"
            }
            """
        Mockito.`when`(singleSelectionJsonDeserializer.deserialize(json)).thenReturn(aSingleSelectionAnswer)

        deserializer.deserialize(json)

        Assertions.assertThat(deserializer.logs()).contains(*logs)
    }

    @Test
    fun deserialize_objectWithMultipleSelectionAnswerType_returnMultipleSelectionAnswerTest() {
        mockDeserializersToReturnEmptyLogs()
        val json = """
            {
                "${JsonKeys.Answer.TYPE}": "${AnswerTypes.MULTIPLE_SELECTION.name}"
            }
            """
        Mockito.`when`(multipleSelectionJsonDeserializer.deserialize(any())).thenReturn(aMultipleSelectionAnswer)

        val answer = deserializer.deserialize(json)

        Assertions.assertThat(answer).isEqualTo(aMultipleSelectionAnswer)
    }

    @Test
    fun afterDeserialize_objectWithMultipleSelectionAnswerType_logsAreDumpTest() {
        val logs = mockSomeDeserializationLog()
        Mockito.`when`(multipleSelectionJsonDeserializer.logs()).thenReturn(logs)
        val json = """
            {
                "${JsonKeys.Answer.TYPE}": "${AnswerTypes.MULTIPLE_SELECTION.name}"
            }
            """
        Mockito.`when`(multipleSelectionJsonDeserializer.deserialize(json)).thenReturn(aMultipleSelectionAnswer)

        deserializer.deserialize(json)

        Assertions.assertThat(deserializer.logs()).contains(*logs)
    }

}