package es.sfernandez.sqg.model.validators.answers

import es.sfernandez.sqg.BasicFixtures
import es.sfernandez.sqg.beans.question.answers.TextAnswer
import es.sfernandez.sqg.model.validators.ValidationResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TextAnswerValidatorTest {

    //---- Attributes ----
    private val validator = TextAnswerValidator()

    //---- Fixtures ----
    private val answerWithoutPossibleReplies = TextAnswer()
    private val answerWithPossibleReplies = TextAnswer(BasicFixtures.SOME_TEXT_1)

    private val answerWithEmptyRegex = TextAnswer(Regex(""))
    private val answerWithNotEmptyRegex = TextAnswer(Regex(" "))

    //---- Tests ----
    @Test
    fun validate_answerWithoutPossibleReplies_returnErrorTest() {
        val result = validator.validate(answerWithoutPossibleReplies)

        assertThat(result.type).isEqualTo(ValidationResult.ValidationResultType.ERROR)
    }

    @Test
    fun validate_answerWithoutPossibleReplies_returnErrorWithContextTest() {
        val result = validator.validate(answerWithoutPossibleReplies)

        assertThat(result.context).isSameAs(answerWithoutPossibleReplies)
    }

    @Test
    fun validate_answerWithPossibleReplies_returnOkTest() {
        val result = validator.validate(answerWithPossibleReplies)

        assertThat(result.type).isEqualTo(ValidationResult.ValidationResultType.OK)
    }

    @Test
    fun validate_answerWithEmptyRegex_returnErrorTest() {
        val result = validator.validate(answerWithEmptyRegex)

        assertThat(result.type).isEqualTo(ValidationResult.ValidationResultType.ERROR)
    }

    @Test
    fun validate_answerWithEmptyRegex_returnErrorWithContextTest() {
        val result = validator.validate(answerWithEmptyRegex)

        assertThat(result.context).isSameAs(answerWithEmptyRegex)
    }

    @Test
    fun validate_answerWithNotEmptyRegex_returnOkTest() {
        val result = validator.validate(answerWithNotEmptyRegex)

        assertThat(result.type).isEqualTo(ValidationResult.ValidationResultType.OK)
    }

}