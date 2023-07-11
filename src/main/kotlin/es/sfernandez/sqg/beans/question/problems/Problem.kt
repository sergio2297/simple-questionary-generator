package es.sfernandez.sqg.beans.question.problems

import es.sfernandez.sqg.beans.contents.*

class Problem
private constructor(
    override val groupOfContents: GroupOfContents
) : HasContents {

    //---- Constructor ----
    constructor() : this(GroupOfContents())

}