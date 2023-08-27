package es.sfernandez.sqg.beans.question.answers.correction.rightornot

/**
 * RightChoiceIds is a [RightOrNotCorrection] that stores all the right choice's ids of its answer. This means that the
 * answer's reply will be right iff all choice's ids are present
 *
 * @param ids Array of right choice's ids
 */
class RightChoiceIds(
    vararg ids: String
) : RightOrNotCorrection {

    //---- Attributes ----
    var ids: Array<String>

    //---- Constructor ----
    init {
        this.ids = arrayOf(*ids)
    }

}