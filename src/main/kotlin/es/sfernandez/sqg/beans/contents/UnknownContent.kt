package es.sfernandez.sqg.beans.contents

class UnknownContent
private constructor(
    override val type: ContentType = ContentType.UNKNOWN
) : Content {

    constructor() : this(ContentType.UNKNOWN)

}