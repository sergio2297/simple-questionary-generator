package es.sfernandez.sqg.model.question

import es.sfernandez.sqg.model.question.answers.Answer
import es.sfernandez.sqg.model.question.explanations.Explanation
import es.sfernandez.sqg.model.question.problems.Problem

class Question(
    val title : String,
    val problem : Problem,
    val answer : Answer<*>,
    val explanation : Explanation,
) {
}