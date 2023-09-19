package es.sfernandez.sqg.deserializer.json.questionnaire.question.answer.input

import es.sfernandez.sqg.beans.question.answers.Choice
import es.sfernandez.sqg.deserializer.json.JsonFixtures
import es.sfernandez.sqg.deserializer.json.JsonKeys
import es.sfernandez.sqg.deserializer.json.questionnaire.question.ChoiceJsonDeserializer
import es.sfernandez.sqg.deserializer.logs.DeserializationLog
import es.sfernandez.sqg.deserializer.logs.DeserializationLogUtilsForTests
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.any

internal sealed class SelectionAnswerInputJsonDeserializerTest {

    //---- Attributes ----
    protected lateinit var deserializer: SelectionAnswerInputJsonDeserializer<*>

    private lateinit var choiceDeserializer: ChoiceJsonDeserializer

    //---- Fixtures ----
    private val someChoices = generateSequence { Choice() }.take(5).toList().toTypedArray()

    //---- Configuration ----
    @BeforeEach
    fun setup() {
        deserializer = createMockedDeserializer()
    }

    //---- Methods ----
    protected fun createNormalDeserializer(): SelectionAnswerInputJsonDeserializer<*> {
        return instantiateDeserializer()
    }

    abstract fun instantiateDeserializer(): SelectionAnswerInputJsonDeserializer<*>

    protected fun createMockedDeserializer(): SelectionAnswerInputJsonDeserializer<*> {
        choiceDeserializer = Mockito.mock(ChoiceJsonDeserializer::class.java)
        return instantiateDeserializer(choiceDeserializer)
    }

    abstract fun instantiateDeserializer(choiceDeserializer: ChoiceJsonDeserializer): SelectionAnswerInputJsonDeserializer<*>

    private fun mockDeserializersToReturnEmptyLogs() {
        Mockito.lenient().`when`(choiceDeserializer.logs()).thenReturn(arrayOf())
    }

    private fun mockSomeDeserializationLog() : Array<DeserializationLog> {
        return generateSequence { Mockito.mock(DeserializationLog::class.java) }
            .take(3).toList().toTypedArray()
    }

    //---- Tests ----
    @Test
    fun deserialize_emptyJsonObject_returnInputWithoutPossibleChoicesTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT
        deserializer = createNormalDeserializer()

        val input = deserializer.deserialize(json)

        assertThat(input.possibleChoices).isEmpty()
    }

    @Test
    fun afterDeserialize_emptyJsonObject_logsHaveWarningTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT
        deserializer = createNormalDeserializer()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer,
            JsonKeys.Answer.Input.Selection.POSSIBLE_CHOICES)
    }

    @Test
    fun deserialize_jsonObjectWithNotValidPossibleChoiceType_returnInputWithoutPossibleChoicesTest() {
        val json = """
            {
                "${JsonKeys.Answer.Input.Selection.POSSIBLE_CHOICES}": {}
            }
        """
        deserializer = createNormalDeserializer()

        val input = deserializer.deserialize(json)

        assertThat(input.possibleChoices).isEmpty()
    }

    @Test
    fun afterDeserialize_jsonObjectWithNotValidPossibleChoiceType_logsHaveWarningTest() {
        val json = """
            {
                "${JsonKeys.Answer.Input.Selection.POSSIBLE_CHOICES}": {}
            }
        """
        deserializer = createNormalDeserializer()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer,
            JsonKeys.Answer.Input.Selection.POSSIBLE_CHOICES)
    }

    @Test
    fun deserialize_jsonWithEmptyPossibleChoice_returnInputWithoutPossibleChoicesTest() {
        val json = """
            {
                "${JsonKeys.Answer.Input.Selection.POSSIBLE_CHOICES}": []
            }
        """
        mockDeserializersToReturnEmptyLogs()
        Mockito.`when`(choiceDeserializer.deserialize(any())).thenReturn(someChoices[0], *someChoices)
        val input = deserializer.deserialize(json)

        assertThat(input.possibleChoices).isEmpty()
    }

    @Test
    fun deserialize_jsonWithOnePossibleChoice_worksTest() {
        val json = """
            {
                "${JsonKeys.Answer.Input.Selection.POSSIBLE_CHOICES}": [{}]
            }
        """
        mockDeserializersToReturnEmptyLogs()
        Mockito.`when`(choiceDeserializer.deserialize(any())).thenReturn(someChoices[0])
        val input = deserializer.deserialize(json)

        assertThat(input.possibleChoices).containsOnly(someChoices[0])
    }

    @Test
    fun deserialize_jsonWithMoreThanOnePossibleChoice_worksTest() {
        val json = """
            {
                "${JsonKeys.Answer.Input.Selection.POSSIBLE_CHOICES}": [{}, {}, {}]
            }
        """
        mockDeserializersToReturnEmptyLogs()
        Mockito.`when`(choiceDeserializer.deserialize(any())).thenReturn(someChoices[0], someChoices[1], someChoices[2])
        val input = deserializer.deserialize(json)

        assertThat(input.possibleChoices).containsOnly(someChoices[0], someChoices[1], someChoices[2])
    }

    @Test
    fun afterDeserialize_logsAreDump_fromChoiceDeserializerTest() {
        val json = """
            {
                "${JsonKeys.Answer.Input.Selection.POSSIBLE_CHOICES}": [{}]
            }
        """
        val logs = mockSomeDeserializationLog()
        Mockito.`when`(choiceDeserializer.deserialize(any())).thenReturn(someChoices[0])
        Mockito.`when`(choiceDeserializer.logs()).thenReturn(logs)

        deserializer.deserialize(json)

        assertThat(deserializer.logs()).contains(*logs)
    }

}