package es.sfernandez.sqg.beans.question.answers.input

/**
 * An AnswerInput defines the input necessary to reply an Answer. For example: text input, selection of choices...
 */
interface AnswerInput {

    /**
     * Types of AnswerInput
     */
    enum class Type {
        SINGLE_SELECTION, MULTIPLE_SELECTION, TEXT, UNSPECIFIED
    }

    /** The type of input */
    val type: Type

}