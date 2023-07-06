package es.sfernandez.sqg.model.question.problems

import es.sfernandez.sqg.model.contents.*

class Problem
private constructor(
    override val groupOfContents: GroupOfContents
) : HasContents {

    //---- Constructor ----
    constructor() : this(GroupOfContents())

}