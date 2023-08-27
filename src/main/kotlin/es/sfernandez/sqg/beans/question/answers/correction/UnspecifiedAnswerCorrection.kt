package es.sfernandez.sqg.beans.question.answers.correction

/**
 * An UnspecifiedAnswerCorrection is an [AnswerCorrection] of type [AnswerCorrection.Type.UNSPECIFIED].
 *
 * It doesn't store anything more information
 *
 */
class UnspecifiedAnswerCorrection : AnswerCorrection {

    override val type: AnswerCorrection.Type
        get() = AnswerCorrection.Type.UNSPECIFIED

}