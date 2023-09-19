package es.sfernandez.sqg.deserializer.json.questionnaire.question.answer.input

import es.sfernandez.sqg.beans.question.answers.input.*
import es.sfernandez.sqg.deserializer.json.JsonFixtures
import es.sfernandez.sqg.deserializer.json.JsonKeys
import es.sfernandez.sqg.deserializer.logs.DeserializationLog
import es.sfernandez.sqg.deserializer.logs.DeserializationLogUtilsForTests
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito
import org.mockito.kotlin.any
import kotlin.test.Test

class AnswerInputJsonDeserializerTest {

    //---- Attributes ----
    private lateinit var deserializer: AnswerInputJsonDeserializer

    private lateinit var textInputDeserializer: TextAnswerInputJsonDeserializer
    private lateinit var singleSelectionInputDeserializer: SingleSelectionAnswerInputJsonDeserializer
    private lateinit var multipleSelectionInputDeserializer: MultipleSelectionAnswerInputJsonDeserializer

    //---- Fixtures ----
    private val aTextInput = Mockito.mock(TextAnswerInput::class.java)
    private val aSingleSelectionInput = Mockito.mock(SingleSelectionAnswerInput::class.java)
    private val aMultipleSelectionInput = Mockito.mock(MultipleSelectionAnswerInput::class.java)

    //---- Configuration ----
    @BeforeEach
    fun setup() {
        deserializer = createMockedDeserializer()
    }

    //---- Methods ----
    private fun createNormalDeserializer() : AnswerInputJsonDeserializer {
        return AnswerInputJsonDeserializer()
    }

    private fun createMockedDeserializer() : AnswerInputJsonDeserializer {
        textInputDeserializer = Mockito.mock(TextAnswerInputJsonDeserializer::class.java)
        singleSelectionInputDeserializer = Mockito.mock(SingleSelectionAnswerInputJsonDeserializer::class.java)
        multipleSelectionInputDeserializer = Mockito.mock(MultipleSelectionAnswerInputJsonDeserializer::class.java)
        return AnswerInputJsonDeserializer(textInputDeserializer, singleSelectionInputDeserializer, multipleSelectionInputDeserializer)
    }

    private fun mockDeserializersToReturnEmptyLogs() {
        Mockito.lenient().`when`(textInputDeserializer.logs()).thenReturn(arrayOf())
        Mockito.lenient().`when`(singleSelectionInputDeserializer.logs()).thenReturn(arrayOf())
        Mockito.lenient().`when`(multipleSelectionInputDeserializer.logs()).thenReturn(arrayOf())
    }

    private fun mockSomeDeserializationLog() : Array<DeserializationLog> {
        return generateSequence { Mockito.mock(DeserializationLog::class.java) }
            .take(3).toList().toTypedArray()
    }

    //---- Tests ----
    @Test
    fun deserialize_notJsonObject_returnUnspecifiedAnswerTest() {
        val json = JsonFixtures.EMPTY_JSON_ARRAY
        deserializer = createNormalDeserializer()

        val input = deserializer.deserialize(json)

        Assertions.assertThat(input).isInstanceOf(UnspecifiedAnswerInput::class.java)
    }

    @Test
    fun afterDeserialize_notJsonObject_logsHaveWarningTest() {
        val json = JsonFixtures.EMPTY_JSON_ARRAY
        deserializer = createNormalDeserializer()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(
            deserializer, AnswerInputJsonDeserializer::class.java.simpleName)
    }

    @Test
    fun deserialize_objectWithNotValidType_returnUnspecifiedAnswerTest() {
        val json = """
             {
                 "${JsonKeys.Answer.Input.TYPE}": {}
             }"""
        deserializer = createNormalDeserializer()

        val input = deserializer.deserialize(json)

        Assertions.assertThat(input).isInstanceOf(UnspecifiedAnswerInput::class.java)
    }

    @Test
    fun afterDeserialize_objectWithNotValidType_logsHaveWarningTest() {
        val json = """
             {
                 "${JsonKeys.Answer.Input.TYPE}": {}
             }"""
        deserializer = createNormalDeserializer()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(
            deserializer, AnswerInputJsonDeserializer::class.java.simpleName)
    }

    @Test
    fun deserialize_objectWithUnspecifiedInputType_returnUnspecifiedAnswerTest() {
        val json = """
             {
                "${JsonKeys.Answer.Input.TYPE}": "${AnswerInput.Type.UNSPECIFIED.name}"
             }
             """
        deserializer = createNormalDeserializer()

        val input = deserializer.deserialize(json)

        Assertions.assertThat(input).isInstanceOf(UnspecifiedAnswerInput::class.java)
    }

    @Test
    fun afterDeserialize_objectWithUnspecifiedInputType_logsContainWarningTest() {
        val json = """
             {
                "${JsonKeys.Answer.Input.TYPE}": "${AnswerInput.Type.UNSPECIFIED.name}"
             }
             """
        deserializer = createNormalDeserializer()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(
            deserializer, AnswerInputJsonDeserializer::class.java.simpleName)
    }

    @Test
    fun deserialize_objectWithTextInputType_returnTextAnswerTest() {
        mockDeserializersToReturnEmptyLogs()
        val json = """
            {
                "${JsonKeys.Answer.Input.TYPE}": "${AnswerInput.Type.TEXT.name}"
            }
            """
        Mockito.`when`(textInputDeserializer.deserialize(any())).thenReturn(aTextInput)

        val input = deserializer.deserialize(json)

        Assertions.assertThat(input).isEqualTo(aTextInput)
    }

    @Test
    fun afterDeserialize_objectWithTextInputType_logsAreDumpTest() {
        val logs = mockSomeDeserializationLog()
        Mockito.`when`(textInputDeserializer.logs()).thenReturn(logs)
        val json = """
            {
                "${JsonKeys.Answer.Input.TYPE}": "${AnswerInput.Type.TEXT.name}"
            }
            """
        Mockito.`when`(textInputDeserializer.deserialize(any())).thenReturn(aTextInput)

        deserializer.deserialize(json)

        Assertions.assertThat(deserializer.logs()).contains(*logs)
    }

    @Test
    fun deserialize_objectWithSingleSelectionInputType_returnTextAnswerTest() {
        mockDeserializersToReturnEmptyLogs()
        val json = """
            {
                "${JsonKeys.Answer.Input.TYPE}": "${AnswerInput.Type.SINGLE_SELECTION.name}"
            }
            """
        Mockito.`when`(singleSelectionInputDeserializer.deserialize(any())).thenReturn(aSingleSelectionInput)

        val input = deserializer.deserialize(json)

        Assertions.assertThat(input).isEqualTo(aSingleSelectionInput)
    }

    @Test
    fun afterDeserialize_objectWithSingleSelectionInputType_logsAreDumpTest() {
        val logs = mockSomeDeserializationLog()
        Mockito.`when`(singleSelectionInputDeserializer.logs()).thenReturn(logs)
        val json = """
            {
                "${JsonKeys.Answer.Input.TYPE}": "${AnswerInput.Type.SINGLE_SELECTION.name}"
            }
            """
        Mockito.`when`(singleSelectionInputDeserializer.deserialize(any())).thenReturn(aSingleSelectionInput)

        deserializer.deserialize(json)

        Assertions.assertThat(deserializer.logs()).contains(*logs)
    }

    @Test
    fun deserialize_objectWithMultipleSelectionInputType_returnTextAnswerTest() {
        mockDeserializersToReturnEmptyLogs()
        val json = """
            {
                "${JsonKeys.Answer.Input.TYPE}": "${AnswerInput.Type.MULTIPLE_SELECTION.name}"
            }
            """
        Mockito.`when`(multipleSelectionInputDeserializer.deserialize(any())).thenReturn(aMultipleSelectionInput)

        val input = deserializer.deserialize(json)

        Assertions.assertThat(input).isEqualTo(aMultipleSelectionInput)
    }

    @Test
    fun afterDeserialize_objectWithMultipleSelectionInputType_logsAreDumpTest() {
        val logs = mockSomeDeserializationLog()
        Mockito.`when`(multipleSelectionInputDeserializer.logs()).thenReturn(logs)
        val json = """
            {
                "${JsonKeys.Answer.Input.TYPE}": "${AnswerInput.Type.MULTIPLE_SELECTION.name}"
            }
            """
        Mockito.`when`(multipleSelectionInputDeserializer.deserialize(any())).thenReturn(aMultipleSelectionInput)

        deserializer.deserialize(json)

        Assertions.assertThat(deserializer.logs()).contains(*logs)
    }
    
}