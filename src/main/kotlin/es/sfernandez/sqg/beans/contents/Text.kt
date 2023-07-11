package es.sfernandez.sqg.beans.contents

/**
 * A Text is a [Content] of type [ContentType.TEXT] that represents a text
 *
 * @property markup type of markup of its value
 */
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