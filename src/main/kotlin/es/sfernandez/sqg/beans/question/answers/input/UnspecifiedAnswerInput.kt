package es.sfernandez.sqg.beans.question.answers.input

/**
 * An UnspecifiedAnswerInput represents an Answer's input that haven't been specified.
 *
 * In fact, it's a way to avoid the use of null values at the moment of create a new Answer.
 */
class UnspecifiedAnswerInput : AnswerInput {

    override val type: AnswerInput.Type
        get() = AnswerInput.Type.UNSPECIFIED

}