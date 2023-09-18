package es.sfernandez.sqg.deserializer.json.questionnaire.question.answer

import es.sfernandez.sqg.beans.question.answers.correction.AnswerCorrection
import es.sfernandez.sqg.beans.question.answers.correction.UnspecifiedAnswerCorrection
import es.sfernandez.sqg.beans.question.answers.input.AnswerInput
import es.sfernandez.sqg.beans.question.answers.input.UnspecifiedAnswerInput
import es.sfernandez.sqg.deserializer.json.JsonFixtures
import es.sfernandez.sqg.deserializer.json.JsonKeys
import es.sfernandez.sqg.deserializer.json.questionnaire.question.answer.correction.AnswerCorrectionJsonDeserializer
import es.sfernandez.sqg.deserializer.json.questionnaire.question.answer.input.AnswerInputJsonDeserializer
import es.sfernandez.sqg.deserializer.logs.DeserializationLog
import es.sfernandez.sqg.deserializer.logs.DeserializationLogUtilsForTests
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.any

class AnswerJsonDeserializerTest {
    
    //---- Attributes ----
    private lateinit var deserializer: AnswerJsonDeserializer
    
    private lateinit var inputDeserializer: AnswerInputJsonDeserializer
    private lateinit var correctionDeserializer: AnswerCorrectionJsonDeserializer
    
    //---- Fixtures ----
    private val anInput = Mockito.mock(AnswerInput::class.java)
    private val aCorrection = Mockito.mock(AnswerCorrection::class.java)
    
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
        inputDeserializer = Mockito.mock(AnswerInputJsonDeserializer::class.java)
        correctionDeserializer = Mockito.mock(AnswerCorrectionJsonDeserializer::class.java)
        return AnswerJsonDeserializer(inputDeserializer, correctionDeserializer)
    }

    private fun mockDeserializersToReturnEmptyLogs() {
        Mockito.lenient().`when`(inputDeserializer.logs()).thenReturn(arrayOf())
        Mockito.lenient().`when`(correctionDeserializer.logs()).thenReturn(arrayOf())
    }

    private fun mockSomeDeserializationLog() : Array<DeserializationLog> {
        return generateSequence { Mockito.mock(DeserializationLog::class.java) }
            .take(3).toList().toTypedArray()
    }
    
    //---- Tests ----
    @Test
    fun deserialize_objectWithoutInput_returnAnswerWithUnspecifiedInputTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT
        deserializer = createNormalDeserializer()

        val answer = deserializer.deserialize(json)

        Assertions.assertThat(answer.input).isInstanceOf(UnspecifiedAnswerInput::class.java)
    }

    @Test
    fun afterDeserialize_objectWithNoInput_logsHaveWarningTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT
        deserializer = createNormalDeserializer()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer,
            JsonKeys.Answer.INPUT)
    }

    @Test
    fun deserialize_objectWithNoValidInput_returnAnswerWithUnspecifiedInputTest() {
        val json = """
            {
                "${JsonKeys.Answer.INPUT}": "123"
            }
        """
        deserializer = createNormalDeserializer()

        val answer = deserializer.deserialize(json)

        Assertions.assertThat(answer.input).isInstanceOf(UnspecifiedAnswerInput::class.java)
    }

    @Test
    fun afterDeserialize_objectWithNoValidInput_logsHaveWarningTest() {
        val json = """
            {
                "${JsonKeys.Answer.INPUT}": "123"
            }
        """
        deserializer = createNormalDeserializer()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer,
            JsonKeys.Answer.INPUT)
    }

    @Test
    fun deserialize_objectWithInput_worksTest() {
        val json = """
            {
                "${JsonKeys.Answer.INPUT}": {}
            }
        """
        mockDeserializersToReturnEmptyLogs()
        Mockito.`when`(inputDeserializer.deserialize(any())).thenReturn(anInput)

        val answer = deserializer.deserialize(json)

        Assertions.assertThat(answer.input).isEqualTo(anInput)
    }

    @Test
    fun afterDeserialize_objectWithInput_logsAreDumpTest() {
        val json = """
            {
                "${JsonKeys.Answer.INPUT}": {}
            }
        """
        mockDeserializersToReturnEmptyLogs()
        val logs = mockSomeDeserializationLog()
        Mockito.`when`(inputDeserializer.deserialize(any())).thenReturn(anInput)
        Mockito.`when`(inputDeserializer.logs()).thenReturn(logs)

        deserializer.deserialize(json)

        Assertions.assertThat(deserializer.logs()).contains(*logs)
    }

    @Test
    fun deserialize_objectWithoutCorrection_returnAnswerWithUnspecifiedCorrectionTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT
        deserializer = createNormalDeserializer()

        val answer = deserializer.deserialize(json)

        Assertions.assertThat(answer.correction).isInstanceOf(UnspecifiedAnswerCorrection::class.java)
    }

    @Test
    fun afterDeserialize_objectWithNoCorrection_logsHaveWarningTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT
        deserializer = createNormalDeserializer()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer,
            JsonKeys.Answer.CORRECTION)
    }

    @Test
    fun deserialize_objectWithNoValidCorrection_returnAnswerWithUnspecifiedCorrectionTest() {
        val json = """
            {
                "${JsonKeys.Answer.CORRECTION}": "123"
            }
        """
        deserializer = createNormalDeserializer()

        val answer = deserializer.deserialize(json)

        Assertions.assertThat(answer.correction).isInstanceOf(UnspecifiedAnswerCorrection::class.java)
    }

    @Test
    fun afterDeserialize_objectWithNoValidCorrection_logsHaveWarningTest() {
        val json = """
            {
                "${JsonKeys.Answer.CORRECTION}": "123"
            }
        """
        deserializer = createNormalDeserializer()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer,
            JsonKeys.Answer.CORRECTION)
    }

    @Test
    fun deserialize_objectWithCorrection_worksTest() {
        val json = """
            {
                "${JsonKeys.Answer.CORRECTION}": {}
            }
        """
        mockDeserializersToReturnEmptyLogs()
        Mockito.`when`(correctionDeserializer.deserialize(any())).thenReturn(aCorrection)

        val answer = deserializer.deserialize(json)

        Assertions.assertThat(answer.correction).isEqualTo(aCorrection)
    }

    @Test
    fun afterDeserialize_objectWithCorrection_logsAreDumpTest() {
        val json = """
            {
                "${JsonKeys.Answer.CORRECTION}": {}
            }
        """
        mockDeserializersToReturnEmptyLogs()
        val logs = mockSomeDeserializationLog()
        Mockito.`when`(correctionDeserializer.deserialize(any())).thenReturn(aCorrection)
        Mockito.`when`(correctionDeserializer.logs()).thenReturn(logs)

        deserializer.deserialize(json)

        Assertions.assertThat(deserializer.logs()).contains(*logs)
    }
    
}