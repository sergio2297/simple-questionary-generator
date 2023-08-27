package es.sfernandez.sqg.beans.question.answers.correction.rightornot

/**
 * CorrectValuesRegex is a [RightOrNotCorrection] that stores the regex that all possible right replies match.
 * This means that the answer's reply will be right iff its value matches the regex
 *
 * @param regex Regex
 */
class CorrectValuesRegex(
    var regex: Regex
) : RightOrNotCorrection