package es.sfernandez.sqg.beans.question.answers

import es.sfernandez.sqg.beans.question.answers.correction.AnswerCorrection
import es.sfernandez.sqg.beans.question.answers.correction.UnspecifiedAnswerCorrection
import es.sfernandez.sqg.beans.question.answers.input.AnswerInput
import es.sfernandez.sqg.beans.question.answers.input.UnspecifiedAnswerInput

/**
 * An Answer represents all the information necessary to represent the answer of a question: its input and its correction.
 *
 * @see AnswerInput.Type
 */
class Answer(
    var input: AnswerInput,
    var correction: AnswerCorrection,
) {

    constructor() : this(UnspecifiedAnswerInput(), UnspecifiedAnswerCorrection())

}