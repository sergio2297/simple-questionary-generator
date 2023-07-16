package es.sfernandez.sqg.deserializer.json.questionary.question.answer

import es.sfernandez.sqg.BasicFixtures
import es.sfernandez.sqg.beans.question.answers.choices.Choice
import es.sfernandez.sqg.deserializer.json.JsonFixtures
import es.sfernandez.sqg.deserializer.json.JsonKeys
import es.sfernandez.sqg.deserializer.json.questionary.question.ChoiceJsonDeserializer
import es.sfernandez.sqg.deserializer.logs.DeserializationLog
import es.sfernandez.sqg.deserializer.logs.DeserializationLogUtilsForTests
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.any
import kotlin.test.BeforeTest

class MultipleSelectionAnswerJsonDeserializerTest {

    //---- Attributes ----
    private lateinit var deserializer: MultipleSelectionAnswerJsonDeserializer
    private lateinit var choiceDeserializer: ChoiceJsonDeserializer

    //---- Fixtures ----
    private val someChoices = arrayOf(mockChoice(), mockChoice())

    //---- Configuration ----
    @BeforeTest
    fun setup() {
        deserializer = createDefaultDeserializer()
    }

    //---- Methods ----
    private fun createDefaultDeserializer(): MultipleSelectionAnswerJsonDeserializer {
        return MultipleSelectionAnswerJsonDeserializer()
    }

    private fun createMockedDeserializer() : MultipleSelectionAnswerJsonDeserializer {
        choiceDeserializer = Mockito.mock(ChoiceJsonDeserializer::class.java)
        Mockito.lenient().`when`(choiceDeserializer.logs())
            .thenReturn(arrayOf(Mockito.mock(DeserializationLog::class.java)))
        return MultipleSelectionAnswerJsonDeserializer(choiceDeserializer)
    }

    private fun mockChoice() : Choice {
        return Mockito.mock(Choice::class.java)
    }

    //---- Tests ----
    @Test
    fun deserialize_objectWithoutPossibleChoices_returnsAnswerWithEmptyPossibleChoicesTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        val answer = deserializer.deserialize(json)

        Assertions.assertThat(answer.possibleChoices).isEmpty()
    }

    @Test
    fun afterDeserialize_objectWithoutPossibleChoices_logsHaveWarningTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer, JsonKeys.Answer.MultipleSelection.POSSIBLE_CHOICES)
    }

    @Test
    fun deserialize_objectWithPossibleChoices_returnsAnswerWithPossibleChoicesTest() {
        deserializer = createMockedDeserializer()
        val expectedArray = someChoices
        Mockito.`when`(choiceDeserializer.deserialize(any())).thenReturn(expectedArray[0], expectedArray[1])
        val json = """
            {
                "${JsonKeys.Answer.MultipleSelection.POSSIBLE_CHOICES}": ["", ""]
            }
        """.trimIndent()

        val answer = deserializer.deserialize(json)

        Assertions.assertThat(answer.possibleChoices).containsExactly(*someChoices)
    }

    @Test
    fun afterDeserialize_objectWithNotValidPossibleChoices_logsHaveWarningTest() {
        val json = """
            {
                "${JsonKeys.Answer.MultipleSelection.POSSIBLE_CHOICES}": {}
            }
        """.trimIndent()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer, JsonKeys.Answer.MultipleSelection.POSSIBLE_CHOICES)
    }

    @Test
    fun deserialize_objectWithoutRightChoicesIds_returnsAnswerWithEmptyRightChoicesIdsTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        val answer = deserializer.deserialize(json)

        Assertions.assertThat(answer.possibleChoices).isEmpty()
    }

    @Test
    fun afterDeserialize_objectWithoutRightChoicesIds_logsHaveWarningTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer, JsonKeys.Answer.MultipleSelection.RIGHT_CHOICES_IDS)
    }

    @Test
    fun deserialize_objectWithRightChoicesIds_returnsAnswerWithRightChoicesIdsTest() {
        val ids = arrayOf(BasicFixtures.SOME_TEXT_1, BasicFixtures.SOME_TEXT_2)
        val json = """
            {
                "${JsonKeys.Answer.MultipleSelection.RIGHT_CHOICES_IDS}": ["${ids[0]}", "${ids[1]}"]
            }
        """.trimIndent()

        val answer = deserializer.deserialize(json)

        Assertions.assertThat(answer.rightChoicesIds).containsExactly(*ids)
    }

    @Test
    fun afterDeserialize_objectWithNotValidRightChoicesIds_logsHaveWarningTest() {
        val json = """
            {
                "${JsonKeys.Answer.MultipleSelection.RIGHT_CHOICES_IDS}": 7
            }
        """.trimIndent()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer, JsonKeys.Answer.MultipleSelection.RIGHT_CHOICES_IDS)
    }
    
}