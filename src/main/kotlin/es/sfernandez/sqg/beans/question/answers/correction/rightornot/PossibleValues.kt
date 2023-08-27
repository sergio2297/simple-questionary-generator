package es.sfernandez.sqg.beans.question.answers.correction.rightornot

/**
 * PossibleValues is a [RightOrNotCorrection] that stores all the possible right values of its answer. This means that the
 * answer's reply will be right iff contains a possible value
 *
 * @param values Array of possible values
 */
class PossibleValues(
    vararg values: String
) : RightOrNotCorrection {

    //---- Attributes ----
    var values: Array<String>

    //---- constructor ----
    init {
        this.values = arrayOf(*values)
    }

}