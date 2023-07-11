package es.sfernandez.sqg.beans.question.explanations

import es.sfernandez.sqg.beans.contents.GroupOfContents
import es.sfernandez.sqg.beans.contents.HasContents

class Explanation
private constructor (
    override val groupOfContents: GroupOfContents,
) : HasContents {

    constructor() : this(GroupOfContents())

}