package es.sfernandez.sqg.model.validators

/**
 * Validators can validate a given value and generate a [ValidationResult]
 *
 * @param T Type of value to validate
 * @see ValidationResult
 */
interface Validator<T> {

    /**
     * Validate the given value and generates a [ValidationResult]
     *
     * @param value Value to validate
     * @return the result of validate the given value.
     * @see ValidationResult
     */
    fun validate(value: T) : ValidationResult<T>

    companion object {

        fun <T> validateIteratively(value: T, vararg validations: (T) -> ValidationResult<T>) : ValidationResult<T> {
            for(validation in validations) {
                val result = validation.invoke(value)
                if(!result.isOk())
                    return result
            }

            return ValidationResult.ok()
        }

    }

}