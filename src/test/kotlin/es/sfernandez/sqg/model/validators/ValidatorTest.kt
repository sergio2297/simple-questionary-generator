package es.sfernandez.sqg.model.validators

import org.assertj.core.api.Assertions.assertThat

interface ValidatorTest<T> {

    //---- Attributes ----
    val validator: Validator<T>

    //---- Methods ----
    fun assertIsOkValidate(value: T) {
        val result = validator.validate(value)

        assertThat(result.type).isEqualTo(ValidationResult.Type.OK)
    }

    fun assertIsWarningValidate(value: T) {
        val result = validator.validate(value)

        assertThat(result.type).isEqualTo(ValidationResult.Type.WARNING)
    }

    fun assertIsErrorValidate(value: T) {
        val result = validator.validate(value)

        assertThat(result.type).isEqualTo(ValidationResult.Type.ERROR)
    }

}