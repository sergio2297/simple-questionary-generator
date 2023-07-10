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
    constructor(value: String = "", markup : Markup = Markup.SIMPLE) : this(ContentType.TEXT, value, markup)

}