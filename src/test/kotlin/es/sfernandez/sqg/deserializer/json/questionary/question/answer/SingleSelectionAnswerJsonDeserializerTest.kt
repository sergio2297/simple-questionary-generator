package es.sfernandez.sqg.deserializer.json.questionary.question.answer

import es.sfernandez.sqg.BasicFixtures
import es.sfernandez.sqg.beans.question.answers.choices.Choice
import es.sfernandez.sqg.deserializer.json.JsonFixtures
import es.sfernandez.sqg.deserializer.json.JsonKeys
import es.sfernandez.sqg.deserializer.json.questionary.question.ChoiceJsonDeserializer
import es.sfernandez.sqg.deserializer.logs.DeserializationLog
import es.sfernandez.sqg.deserializer.logs.DeserializationLogUtilsForTests
import org.assertj.core.api.Assertions.assertThat
import org.mockito.Mockito
import org.mockito.kotlin.any
import kotlin.test.BeforeTest
import kotlin.test.Test

class SingleSelectionAnswerJsonDeserializerTest {

    //---- Attributes ----
    private lateinit var deserializer: SingleSelectionAnswerJsonDeserializer
    private lateinit var choiceDeserializer: ChoiceJsonDeserializer

    //---- Fixtures ----
    private val someChoices = arrayOf(mockChoice(), mockChoice())

    //---- Configuration ----
    @BeforeTest
    fun setup() {
        deserializer = createDefaultDeserializer()
    }

    //---- Methods ----
    private fun createDefaultDeserializer(): SingleSelectionAnswerJsonDeserializer {
        return SingleSelectionAnswerJsonDeserializer()
    }

    private fun createMockedDeserializer() : SingleSelectionAnswerJsonDeserializer {
        choiceDeserializer = Mockito.mock(ChoiceJsonDeserializer::class.java)
        Mockito.lenient().`when`(choiceDeserializer.logs())
            .thenReturn(arrayOf(Mockito.mock(DeserializationLog::class.java)))
        return SingleSelectionAnswerJsonDeserializer(choiceDeserializer)
    }

    private fun mockChoice() : Choice {
        return Mockito.mock(Choice::class.java)
    }

    //---- Tests ----
    @Test
    fun deserialize_objectWithoutPossibleChoices_returnsAnswerWithEmptyPossibleChoicesTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        val answer = deserializer.deserialize(json)

        assertThat(answer.possibleChoices).isEmpty()
    }

    @Test
    fun afterDeserialize_objectWithoutPossibleChoices_logsHaveWarningTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer, JsonKeys.Answer.SingleSelection.POSSIBLE_CHOICES)
    }

    @Test
    fun deserialize_objectWithPossibleChoices_returnsAnswerWithPossibleChoicesTest() {
        deserializer = createMockedDeserializer()
        val expectedArray = someChoices
        Mockito.`when`(choiceDeserializer.deserialize(any())).thenReturn(expectedArray[0], expectedArray[1])
        val json = """
            {
                "${JsonKeys.Answer.SingleSelection.POSSIBLE_CHOICES}": ["", ""]
            }
        """.trimIndent()

        val answer = deserializer.deserialize(json)

        assertThat(answer.possibleChoices).containsExactly(*someChoices)
    }

    @Test
    fun afterDeserialize_objectWithNotValidPossibleChoices_logsHaveWarningTest() {
        val json = """
            {
                "${JsonKeys.Answer.SingleSelection.POSSIBLE_CHOICES}": {}
            }
        """.trimIndent()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer, JsonKeys.Answer.SingleSelection.POSSIBLE_CHOICES)
    }



    @Test
    fun deserialize_objectWithoutRightChoiceIdRightChoiceId_returnsAnswerWithEmptyRightChoiceIdTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        val answer = deserializer.deserialize(json)

        assertThat(answer.possibleChoices).isEmpty()
    }

    @Test
    fun afterDeserialize_objectWithoutRightChoiceId_logsHaveWarningTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer, JsonKeys.Answer.SingleSelection.RIGHT_CHOICE_ID)
    }

    @Test
    fun deserialize_objectWithRightChoiceId_returnsAnswerWithRightChoiceIdTest() {
        val id = BasicFixtures.SOME_TEXT_1
        val json = """
            {
                "${JsonKeys.Answer.SingleSelection.RIGHT_CHOICE_ID}": "$id"
            }
        """.trimIndent()

        val answer = deserializer.deserialize(json)

        assertThat(answer.rightChoiceId).isEqualTo(id)
    }

    @Test
    fun afterDeserialize_objectWithNotValidRightChoiceId_logsHaveWarningTest() {
        val json = """
            {
                "${JsonKeys.Answer.SingleSelection.RIGHT_CHOICE_ID}": 7
            }
        """.trimIndent()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer, JsonKeys.Answer.SingleSelection.RIGHT_CHOICE_ID)
    }

}