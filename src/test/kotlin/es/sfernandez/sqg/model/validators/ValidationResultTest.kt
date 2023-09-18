package es.sfernandez.sqg.model.validators

import es.sfernandez.sqg.utilities.Fixtures
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ValidationResultTest {

    //---- Constants and Definitions ----
    private class Foo() {}

    //---- Attributes ----
    private lateinit var result: ValidationResult<Foo>

    //---- Fixtures ----
    private val msg = Fixtures.SOME_TEXT_1
    private val fooValue = Foo()

    //---- Tests ----
    @Test
    fun validationResult_createdWithOk_isOkTypeTest() {
        result = ValidationResult.ok()

        assertThat(result.type).isEqualTo(ValidationResult.ValidationResultType.OK)
    }

    @Test
    fun validationResult_createdWithOk_hasEmptyMessageTest() {
        result = ValidationResult.ok()

        assertThat(result.msg).isEmpty()
    }

    @Test
    fun validationResult_createdWithOk_hasNullContextTest() {
        result = ValidationResult.ok()

        assertThat(result.context).isNull()
    }

    @Test
    fun validationResult_createdWithOk_isOkTest() {
        result = ValidationResult.ok()

        assertThat(result.isOk()).isTrue()
    }

    @Test
    fun validationResult_createdWithWarning_isWarningTypeTest() {
        result = ValidationResult.warning(msg)

        assertThat(result.type).isEqualTo(ValidationResult.ValidationResultType.WARNING)
    }

    @Test
    fun validationResult_createdWithWarning_keepsGivenMsgTest() {
        result = ValidationResult.warning(msg)

        assertThat(result.msg).isEqualTo(msg)
    }

    @Test
    fun validationResult_createdWithWarning_hasNullContextIfNotGivenTest() {
        result = ValidationResult.warning(msg)

        assertThat(result.context).isNull()
    }

    @Test
    fun validationResult_createdWithWarning_keepsContextTest() {
        result = ValidationResult.warning(msg, fooValue)

        assertThat(result.context).isEqualTo(fooValue)
    }

    @Test
    fun validationResult_createdWithWarning_isNotOkTest() {
        result = ValidationResult.warning(msg)

        assertThat(result.isOk()).isFalse()
    }

    @Test
    fun validationResult_createdWithError_isErrorTypeTest() {
        result = ValidationResult.error(msg)

        assertThat(result.type).isEqualTo(ValidationResult.ValidationResultType.ERROR)
    }

    @Test
    fun validationResult_createdWithError_keepsGivenMsgTest() {
        result = ValidationResult.error(msg)

        assertThat(result.msg).isEqualTo(msg)
    }

    @Test
    fun validationResult_createdWithError_hasNullContextIfNotGivenTest() {
        result = ValidationResult.error(msg)

        assertThat(result.context).isNull()
    }

    @Test
    fun validationResult_createdWithError_keepsContextTest() {
        result = ValidationResult.error(msg, fooValue)

        assertThat(result.context).isEqualTo(fooValue)
    }

    @Test
    fun validationResult_createdWithError_isNotOkTest() {
        result = ValidationResult.error(msg)

        assertThat(result.isOk()).isFalse()
    }

}