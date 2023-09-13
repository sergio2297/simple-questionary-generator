package es.sfernandez.sqg.beans.question

import es.sfernandez.sqg.beans.question.answers.Answer
import es.sfernandez.sqg.beans.question.explanations.Explanation
import es.sfernandez.sqg.beans.question.problems.Problem

class Question
private constructor(
    var title : String = "",
    var problem : Problem = Problem(),
    var answer : Answer = Answer(),
    var explanation : Explanation = Explanation(),
) {

    //---- Constructor ----
    constructor() : this("")

}