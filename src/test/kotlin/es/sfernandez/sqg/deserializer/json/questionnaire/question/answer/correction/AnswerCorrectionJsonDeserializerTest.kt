package es.sfernandez.sqg.deserializer.json.questionnaire.question.answer.correction

import es.sfernandez.sqg.beans.question.answers.correction.AnswerCorrection
import es.sfernandez.sqg.beans.question.answers.correction.UnspecifiedAnswerCorrection
import es.sfernandez.sqg.beans.question.answers.correction.rightornot.RightOrNotCorrection
import es.sfernandez.sqg.deserializer.json.JsonFixtures
import es.sfernandez.sqg.deserializer.json.JsonKeys
import es.sfernandez.sqg.deserializer.logs.DeserializationLog
import es.sfernandez.sqg.deserializer.logs.DeserializationLogUtilsForTests
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito
import org.mockito.kotlin.any
import kotlin.test.Test

class AnswerCorrectionJsonDeserializerTest {

    //---- Attributes ----
    private lateinit var deserializer: AnswerCorrectionJsonDeserializer

    private lateinit var rightOrNotDeserializer: RightOrNotCorrectionJsonDeserializer

    //---- Fixtures ----
    private val aRightOrNotCorrection = Mockito.mock(RightOrNotCorrection::class.java)

    //---- Configuration ----
    @BeforeEach
    fun setup() {
        deserializer = createMockedDeserializer()
    }

    //---- Methods ----
    private fun createNormalDeserializer() : AnswerCorrectionJsonDeserializer {
        return AnswerCorrectionJsonDeserializer()
    }

    private fun createMockedDeserializer(): AnswerCorrectionJsonDeserializer {
        rightOrNotDeserializer = Mockito.mock(RightOrNotCorrectionJsonDeserializer::class.java)

        return AnswerCorrectionJsonDeserializer(rightOrNotDeserializer)
    }

    private fun mockDeserializersToReturnEmptyLogs() {
        Mockito.lenient().`when`(rightOrNotDeserializer.logs()).thenReturn(arrayOf())
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

        val correction = deserializer.deserialize(json)

        Assertions.assertThat(correction).isInstanceOf(UnspecifiedAnswerCorrection::class.java)
    }

    @Test
    fun afterDeserialize_notJsonObject_logsHaveWarningTest() {
        val json = JsonFixtures.EMPTY_JSON_ARRAY
        deserializer = createNormalDeserializer()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(
            deserializer, AnswerCorrectionJsonDeserializer::class.java.simpleName)
    }

    @Test
    fun deserialize_objectWithNotValidType_returnUnspecifiedAnswerTest() {
        val json = """
             {
                 "${JsonKeys.Answer.Correction.TYPE}": {}
             }"""
        deserializer = createNormalDeserializer()

        val correction = deserializer.deserialize(json)

        Assertions.assertThat(correction).isInstanceOf(UnspecifiedAnswerCorrection::class.java)
    }

    @Test
    fun afterDeserialize_objectWithNotValidType_logsHaveWarningTest() {
        val json = """
             {
                 "${JsonKeys.Answer.Correction.TYPE}": {}
             }"""
        deserializer = createNormalDeserializer()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(
            deserializer, AnswerCorrectionJsonDeserializer::class.java.simpleName)
    }

    @Test
    fun deserialize_objectWithUnspecifiedCorrectionType_returnUnspecifiedAnswerTest() {
        val json = """
             {
                "${JsonKeys.Answer.Correction.TYPE}": "${AnswerCorrection.Type.UNSPECIFIED.name}"
             }
             """
        deserializer = createNormalDeserializer()

        val input = deserializer.deserialize(json)

        Assertions.assertThat(input).isInstanceOf(UnspecifiedAnswerCorrection::class.java)
    }

    @Test
    fun afterDeserialize_objectWithUnspecifiedCorrectionType_logsContainWarningTest() {
        val json = """
             {
                "${JsonKeys.Answer.Correction.TYPE}": "${AnswerCorrection.Type.UNSPECIFIED.name}"
             }
             """
        deserializer = createNormalDeserializer()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(
            deserializer, AnswerCorrectionJsonDeserializer::class.java.simpleName)
    }

    @Test
    fun deserialize_objectWithTextCorrectionType_returnTextAnswerTest() {
        val json = """
            {
                "${JsonKeys.Answer.Correction.TYPE}": "${AnswerCorrection.Type.RIGHT_OR_NOT.name}"
            }
            """
        mockDeserializersToReturnEmptyLogs()
        Mockito.`when`(rightOrNotDeserializer.deserialize(any())).thenReturn(aRightOrNotCorrection)

        val correction = deserializer.deserialize(json)

        Assertions.assertThat(correction).isEqualTo(aRightOrNotCorrection)
    }

    @Test
    fun afterDeserialize_objectWithTextCorrectionType_logsAreDumpTest() {
        val logs = mockSomeDeserializationLog()
        Mockito.`when`(rightOrNotDeserializer.logs()).thenReturn(logs)
        val json = """
            {
                "${JsonKeys.Answer.Correction.TYPE}": "${AnswerCorrection.Type.RIGHT_OR_NOT.name}"
            }
            """
        Mockito.`when`(rightOrNotDeserializer.deserialize(any())).thenReturn(aRightOrNotCorrection)

        deserializer.deserialize(json)

        Assertions.assertThat(deserializer.logs()).contains(*logs)
    }
    
}