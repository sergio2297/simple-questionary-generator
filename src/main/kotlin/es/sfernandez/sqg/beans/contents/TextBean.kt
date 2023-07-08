package es.sfernandez.sqg.beans.contents

class TextBean(
    var value : String = "",
    var markup : Markup = Markup.SIMPLE
) : ContentBean(ContentType.TEXT) {

    //---- Constants and Definitions ----
    enum class Markup {
        SIMPLE, HTML
    }

}