package es.sfernandez.sqg.beans.question.answers.correction

/**
 * An AnswerCorrection doesn't correct an Answer, but it keeps all the necessary information to correct it. For example:
 * an array with all possible correct replies, the grade of each correct choice....
 */
interface AnswerCorrection {

    /**
     * Types of AnswerCorrection
     */
    enum class Type {
        RIGHT_OR_NOT, GRADE, UNSPECIFIED
    }

    /** The type of correction */
    val type: Type

}
