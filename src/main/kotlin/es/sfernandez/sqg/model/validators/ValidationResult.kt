package es.sfernandez.sqg.model.validators

/**
 * ValidationResults are generated by [Validator] as result of validate a given value.
 *
 * @param T type of value validated
 * @property type Result's type: [Type.OK], [Type.WARNING] y [Type.ERROR]
 * @property msg Result's message
 * @property context The value that was validated
 */
class ValidationResult<T>
private constructor(
    val type: Type,
    val msg: String = "",
    val context: T? = null,
){

    enum class Type {
        /** Everything is perfect */
        OK,
        /** Something isn't right, but is not critical */
        WARNING,
        /** Something isn't right */
        ERROR,
    }

    /**
     * @return true iff type is equal to [Type.OK]
     */
    fun isOk() : Boolean {
        return type == Type.OK
    }

    companion object {

        /**
         * Creates a new [Type.OK] ValidationResult
         *
         * ValidationResult of type OK have empty message and no need context
         *
         * @return a new [Type.OK] ValidationResult with empty message and no context
         */
        fun <T> ok() : ValidationResult<T> {
            return ValidationResult(Type.OK)
        }

        /**
         * Creates a new [Type.WARNING] ValidationResult
         *
         * ValidationResult of type WARNING needs a message. It's possible to aggregate a context
         *
         * @param msg help message of the result
         * @param context value that has been validated. null by default
         * @return a new [Type.WARNING] ValidationResult with the given message and context
         */
        fun <T> warning(msg: String, context: T? = null) : ValidationResult<T> {
            return ValidationResult(Type.WARNING, msg, context)
        }

        /**
         * Creates a new [Type.ERROR] ValidationResult
         *
         * ValidationResult of type ERROR needs a message. It's possible to aggregate a context
         *
         * @param msg help message of the result
         * @param context value that has been validated. null by default
         * @return a new [Type.ERROR] ValidationResult with the given message and context
         */
        fun <T> error(msg: String, context: T? = null) : ValidationResult<T> {
            return ValidationResult(Type.ERROR, msg, context)
        }

    }

}
