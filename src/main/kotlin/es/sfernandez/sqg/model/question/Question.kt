package es.sfernandez.sqg.model.question

import es.sfernandez.sqg.model.question.answers.Answer
import es.sfernandez.sqg.model.question.explanations.Explanation
import es.sfernandez.sqg.model.question.problems.Problem

class Question
private constructor(
    val title : String,
//        val problem : Problem = Problem(),
//        var answer : Answer<*>,
//        var explanation : Explanation = Explanation(),
) {

    //---- Constructor ----
    constructor() : this("")

}