package es.sfernandez.sqg.beans.question.answers.correction.rightornot

import es.sfernandez.sqg.beans.question.answers.correction.AnswerCorrection

interface RightOrNotCorrection : AnswerCorrection {

    override val type: AnswerCorrection.Type
        get() = AnswerCorrection.Type.RIGHT_OR_NOT

}