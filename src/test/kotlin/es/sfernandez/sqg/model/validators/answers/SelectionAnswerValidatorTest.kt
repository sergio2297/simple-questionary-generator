package es.sfernandez.sqg.model.validators.answers

import es.sfernandez.sqg.BasicFixtures
import es.sfernandez.sqg.beans.question.answers.AnswerFixtures
import es.sfernandez.sqg.beans.question.answers.MultipleSelectionAnswer
import es.sfernandez.sqg.beans.question.answers.SingleSelectionAnswer
import es.sfernandez.sqg.beans.question.answers.Choice
import es.sfernandez.sqg.model.validators.ValidationResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SelectionAnswerValidatorTest {

    //---- Attributes ----
    private val validator = SelectionAnswerValidator()

    //---- Fixtures ----
    private val choices = generateIdentifiedChoices(5)

    //---- Configuration ----

    //---- Methods ----
    private fun checkIsOk(result: ValidationResult<*>) {
        assertThat(result.type).isEqualTo(ValidationResult.ValidationResultType.OK)
    }

    private fun checkIsWarning(result: ValidationResult<*>) {
        assertThat(result.type).isEqualTo(ValidationResult.ValidationResultType.WARNING)
    }

    private fun checkIsError(result: ValidationResult<*>) {
        assertThat(result.type).isEqualTo(ValidationResult.ValidationResultType.ERROR)
    }

    private fun <T> checkKeepsContext(result: ValidationResult<T>, context: T) {
        assertThat(result.context).isSameAs(context)
    }

    private fun generateIdentifiedChoices(numOfChoices: Int) : Array<Choice> {
        return generateSequence(1){it + 1}.take(numOfChoices)
            .map { i -> Choice(i.toString(), AnswerFixtures.FooContent()) }
            .toList()
            .toTypedArray()
    }

    //---- Tests ----
    @Test
    fun validate_answerWithoutPossibleChoices_returnErrorTest() {
        val answer = SingleSelectionAnswer(arrayOf(), BasicFixtures.SOME_TEXT_1)

        val result = validator.validate(answer)

        checkIsError(result)
        checkKeepsContext(result, answer)
    }

    @Test
    fun validate_singleSelectionAnswerWithoutRightChoiceId_returnErrorTest() {
        val answer = SingleSelectionAnswer(choices, "")

        val result = validator.validate(answer)

        checkIsError(result)
        checkKeepsContext(result, answer)
    }

    @Test
    fun validate_multipleSelectionAnswerWithoutRightChoicesIds_returnErrorTest() {
        val answer = MultipleSelectionAnswer(choices, arrayOf())

        val result = validator.validate(answer)

        checkIsError(result)
        checkKeepsContext(result, answer)
    }

    @Test
    fun validate_singleSelectionAnswerWithRightChoiceId_outOfPossibleChoices_returnErrorTest() {
        val possibleChoices = generateIdentifiedChoices(5)
        val rightChoiceId = "6"
        val answer = SingleSelectionAnswer(possibleChoices, rightChoiceId)

        val result = validator.validate(answer)

        checkIsError(result)
        checkKeepsContext(result, answer)
    }

    @Test
    fun validate_multipleSelectionAnswerWithAtLeastOneRightChoicesIds_outOfPossibleChoices_returnErrorTest() {
        val possibleChoices = generateIdentifiedChoices(5)
        val rightChoicesId = sequenceOf(1, 2, 7).map(Int::toString).toList().toTypedArray()
        val answer = MultipleSelectionAnswer(possibleChoices, rightChoicesId)

        val result = validator.validate(answer)

        checkIsError(result)
        checkKeepsContext(result, answer)
    }

    @Test
    fun validate_rightSingleSelectionAnswer_returnOkTest() {
        val possibleChoices = generateIdentifiedChoices(5)
        val rightChoiceId = "3"
        val answer = SingleSelectionAnswer(possibleChoices, rightChoiceId)

        val result = validator.validate(answer)

        checkIsOk(result)
    }

    @Test
    fun validate_rightMultipleSelectionAnswer_returnOkTest() {
        val possibleChoices = generateIdentifiedChoices(5)
        val rightChoicesId = sequenceOf(1, 2, 3).map(Int::toString).toList().toTypedArray()
        val answer = MultipleSelectionAnswer(possibleChoices, rightChoicesId)

        val result = validator.validate(answer)

        checkIsOk(result)
    }

}