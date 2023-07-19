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

}