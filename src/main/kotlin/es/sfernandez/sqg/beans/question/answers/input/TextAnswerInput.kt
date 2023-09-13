package es.sfernandez.sqg.beans.question.answers.input

/**
 * TextAnswerInput is a [AnswerInput] which represents an input where the user has to type something
 */
class TextAnswerInput : AnswerInput {

    override val type: AnswerInput.Type
        get() = AnswerInput.Type.TEXT

}