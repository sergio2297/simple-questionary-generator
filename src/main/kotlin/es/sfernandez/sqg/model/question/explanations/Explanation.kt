package es.sfernandez.sqg.model.question.explanations

import es.sfernandez.sqg.model.contents.GroupOfContents
import es.sfernandez.sqg.model.contents.HasContents

class Explanation
private constructor (
    override val groupOfContents: GroupOfContents,
) : HasContents {

    constructor() : this(GroupOfContents())

}