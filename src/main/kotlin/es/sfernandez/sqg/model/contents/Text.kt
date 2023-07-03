package es.sfernandez.sqg.model.contents

class Text
private constructor(
    override val type: ContentType = ContentType.TEXT,
    var value : String = "",
    var markup : Markup = Markup.SIMPLE
) : Content {

    //---- Constants and Definitions ----
    enum class Markup {
        SIMPLE, HTML
    }

    //---- Constructor ----
    constructor() : this("")

    constructor(value: String) : this(value, Markup.SIMPLE)

    constructor(value: String, markup : Markup) : this(ContentType.TEXT, value, markup)

}