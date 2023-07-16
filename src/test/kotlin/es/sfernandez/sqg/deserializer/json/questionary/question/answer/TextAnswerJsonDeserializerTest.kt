package es.sfernandez.sqg.deserializer.json.questionary.question.answer

import es.sfernandez.sqg.BasicFixtures
import es.sfernandez.sqg.beans.question.answers.choices.Choice
import es.sfernandez.sqg.deserializer.json.JsonFixtures
import es.sfernandez.sqg.deserializer.json.JsonKeys
import es.sfernandez.sqg.deserializer.logs.DeserializationLogUtilsForTests
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import kotlin.test.BeforeTest

class TextAnswerJsonDeserializerTest {

    //---- Attributes ----
    private lateinit var deserializer: TextAnswerJsonDeserializer

    //---- Configuration ----
    @BeforeTest
    fun setup() {
        deserializer = createDefaultDeserializer()
    }

    //---- Methods ----
    private fun createDefaultDeserializer(): TextAnswerJsonDeserializer {
        return TextAnswerJsonDeserializer()
    }

    private fun mockChoice() : Choice {
        return Mockito.mock(Choice::class.java)
    }

    //---- Tests ----
    @Test
    fun deserialize_objectWithoutPossibleReplies_returnsAnswerWithEmptyPossibleRepliesTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        val answer = deserializer.deserialize(json)

        Assertions.assertThat(answer.possibleReplies).isEmpty()
    }

    @Test
    fun afterDeserialize_objectWithoutPossibleReplies_logsHaveWarningTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer, JsonKeys.Answer.Text.POSSIBLE_REPLIES)
    }

    @Test
    fun deserialize_objectWithPossibleReplies_returnsAnswerWithPossibleRepliesTest() {
        val replies = arrayOf(BasicFixtures.SOME_TEXT_1, BasicFixtures.SOME_TEXT_2)
        val json = """
            {
                "${JsonKeys.Answer.Text.POSSIBLE_REPLIES}": ["${replies[0]}", "${replies[1]}"]
            }
        """.trimIndent()

        val answer = deserializer.deserialize(json)

        Assertions.assertThat(answer.possibleReplies).containsExactly(*replies)
    }

    @Test
    fun deserialize_objectWithEmptyPossibleReplies_returnsAnswerThatNotCheckWithRegexTest() {
        val json = """
            {
                "${JsonKeys.Answer.Text.POSSIBLE_REPLIES}": []
            }
        """.trimIndent()

        val answer = deserializer.deserialize(json)

        Assertions.assertThat(answer.checkWithRegex).isTrue()
    }
    
    @Test
    fun deserialize_objectWithPossibleReplies_returnsAnswerThatNotCheckWithRegexTest() {
        val replies = arrayOf(BasicFixtures.SOME_TEXT_1, BasicFixtures.SOME_TEXT_2)
        val json = """
            {
                "${JsonKeys.Answer.Text.POSSIBLE_REPLIES}": ["${replies[0]}", "${replies[1]}"]
            }
        """.trimIndent()

        val answer = deserializer.deserialize(json)

        Assertions.assertThat(answer.checkWithRegex).isFalse()
    }

    @Test
    fun afterDeserialize_objectWithNotValidPossibleReplies_logsHaveWarningTest() {
        val json = """
            {
                "${JsonKeys.Answer.Text.POSSIBLE_REPLIES}": {}
            }
        """.trimIndent()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer, JsonKeys.Answer.Text.POSSIBLE_REPLIES)
    }

    @Test
    fun deserialize_objectWithoutReplyRegex_returnsAnswerWithEmptyReplyRegexTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        val answer = deserializer.deserialize(json)

        Assertions.assertThat(answer.replyRegex.pattern).isEmpty()
    }

    @Test
    fun afterDeserialize_objectWithoutReplyRegex_logsHaveWarningTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer, JsonKeys.Answer.Text.REPLY_REGEX)
    }

    @Test
    fun deserialize_objectWithReplyRegex_returnsAnswerWithReplyRegexTest() {
        val pattern = BasicFixtures.SOME_TEXT_1
        val json = """
            {
                "${JsonKeys.Answer.Text.REPLY_REGEX}": "$pattern"
            }
        """.trimIndent()

        val answer = deserializer.deserialize(json)

        Assertions.assertThat(answer.replyRegex.pattern).isEqualTo(pattern)
    }

    @Test
    fun deserialize_objectWithReplyRegex_returnsAnswerThatChecksWithRegexTest() {
        val pattern = BasicFixtures.SOME_TEXT_1
        val json = """
            {
                "${JsonKeys.Answer.Text.REPLY_REGEX}": "$pattern"
            }
        """.trimIndent()

        val answer = deserializer.deserialize(json)

        Assertions.assertThat(answer.checkWithRegex).isTrue()
    }

    @Test
    fun afterDeserialize_objectWithNotValidReplyRegex_logsHaveWarningTest() {
        val json = """
            {
                "${JsonKeys.Answer.Text.REPLY_REGEX}": 7
            }
        """.trimIndent()

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer, JsonKeys.Answer.Text.REPLY_REGEX)
    }
    
}