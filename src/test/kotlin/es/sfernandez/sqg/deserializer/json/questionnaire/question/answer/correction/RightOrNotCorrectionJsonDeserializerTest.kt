package es.sfernandez.sqg.deserializer.json.questionnaire.question.answer.correction

import es.sfernandez.sqg.beans.question.answers.correction.rightornot.CorrectValuesRegex
import es.sfernandez.sqg.beans.question.answers.correction.rightornot.PossibleValues
import es.sfernandez.sqg.beans.question.answers.correction.rightornot.RightChoiceIds
import es.sfernandez.sqg.beans.question.answers.correction.rightornot.UnspecifiedRightOrNotCorrection
import es.sfernandez.sqg.deserializer.json.JsonFixtures
import es.sfernandez.sqg.deserializer.json.JsonKeys
import es.sfernandez.sqg.deserializer.logs.DeserializationLogUtilsForTests
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class RightOrNotCorrectionJsonDeserializerTest {

    //---- Attributes ----
    private lateinit var deserializer: RightOrNotCorrectionJsonDeserializer

    //---- Configuration ----
    @BeforeEach
    fun setup() {
        deserializer = RightOrNotCorrectionJsonDeserializer()
    }

    //---- Tests ----
    @Test
    fun deserialize_unspecifiedCorrection_returnUnspecifiedRightOrNotCorrectionTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        val correction = deserializer.deserialize(json)

        assertThat(correction).isInstanceOf(UnspecifiedRightOrNotCorrection::class.java)
    }

    @Test
    fun afterDeserialize_unspecifiedCorrection_logsHaveWarningTest() {
        val json = JsonFixtures.EMPTY_JSON_OBJECT

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer,
            RightOrNotCorrectionJsonDeserializer::class.java.simpleName)
    }

    @Test
    fun deserialize_jsonWithChoiceIdsOfIncorrectType_returnRightChoiceIdsCorrectionTest() {
        val json = """
            {
                "${JsonKeys.Answer.Correction.RightOrNot.IDS}": 3
            }
        """

        val correction = deserializer.deserialize(json)

        assertThat(correction).isInstanceOf(RightChoiceIds::class.java)
    }

    @Test
    fun afterDeserialize_jsonWithChoiceIdsOfIncorrectType_logsHaveWarningTest() {
        val json = """
            {
                "${JsonKeys.Answer.Correction.RightOrNot.IDS}": 3
            }
        """

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer,
            JsonKeys.Answer.Correction.RightOrNot.IDS)
    }

    @Test
    fun deserialize_jsonWithChoiceIds_returnRightChoiceIdsCorrectionTest() {
        val json = """
            {
                "${JsonKeys.Answer.Correction.RightOrNot.IDS}": [1, 2, 3]           
            }
        """

        val correction = deserializer.deserialize(json)

        assertThat(correction).isInstanceOf(RightChoiceIds::class.java)
        assertThat((correction as RightChoiceIds).ids).containsExactly("1", "2", "3")
    }

    @Test
    fun deserialize_jsonWithPossibleValuesOfIncorrectType_returnPossibleValuesCorrectionTest() {
        val json = """
            {
                "${JsonKeys.Answer.Correction.RightOrNot.VALUES}": 3
            }
        """

        val correction = deserializer.deserialize(json)

        assertThat(correction).isInstanceOf(PossibleValues::class.java)
    }

    @Test
    fun afterDeserialize_jsonWithPossibleValuesOfIncorrectType_logsHaveWarningTest() {
        val json = """
            {
                "${JsonKeys.Answer.Correction.RightOrNot.VALUES}": 3
            }
        """

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer,
            JsonKeys.Answer.Correction.RightOrNot.VALUES)
    }

    @Test
    fun deserialize_jsonWithPossibleValues_returnPossibleValuesCorrectionTest() {
        val json = """
            {
                "${JsonKeys.Answer.Correction.RightOrNot.VALUES}": ["hola", "adios", "nombre completo"]           
            }
        """

        val correction = deserializer.deserialize(json)

        assertThat(correction).isInstanceOf(PossibleValues::class.java)
        assertThat((correction as PossibleValues).values).containsExactly("hola", "adios", "nombre completo")
    }

    @Test
    fun deserialize_jsonWithCorrectValuesRegexOfIncorrectType_returnCorrectValuesRegexCorrectionTest() {
        val json = """
            {
                "${JsonKeys.Answer.Correction.RightOrNot.REGEX}": 3
            }
        """

        val correction = deserializer.deserialize(json)

        assertThat(correction).isInstanceOf(CorrectValuesRegex::class.java)
    }

    @Test
    fun afterDeserialize_jsonWithCorrectValuesRegexOfIncorrectType_logsHaveWarningTest() {
        val json = """
            {
                "${JsonKeys.Answer.Correction.RightOrNot.REGEX}": 3
            }
        """

        deserializer.deserialize(json)

        DeserializationLogUtilsForTests.checkDeserializerLogsContainsWarningWithWord(deserializer,
            JsonKeys.Answer.Correction.RightOrNot.REGEX)
    }

    @Test
    fun deserialize_jsonWithCorrectValuesRegex_returnCorrectValuesRegexCorrectionTest() {
        val pattern = "\\\\(I am Sergio\\\\)"

        val json = """
            {
                "${JsonKeys.Answer.Correction.RightOrNot.REGEX}": "$pattern"           
            }
        """

        val correction = deserializer.deserialize(json)

        assertThat(correction).isInstanceOf(CorrectValuesRegex::class.java)
        assertThat((correction as CorrectValuesRegex).regex.pattern).isEqualTo("\\(I am Sergio\\)")
    }

}