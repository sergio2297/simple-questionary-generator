package es.sfernandez.sqg.model.contents

class Text
private constructor(
    override val type: ContentType = ContentType.TEXT,
    val value : String,
) : Content {

    //---- Constructor ----
    constructor(value: String) : this(ContentType.TEXT, value)

    constructor() : this("")

}