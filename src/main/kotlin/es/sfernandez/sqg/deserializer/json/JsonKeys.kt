package es.sfernandez.sqg.deserializer.json

/**
 * Object which stores all json keys for values of every entity that can be deserialized by [JsonDeserializer]
 *
 * This can be used to protected ourselves from further refactor
 *
 */
object JsonKeys {

    object Text {
        const val VALUE = "value"
        const val MARKUP = "markup"
    }

    object Sound {
        const val PATH = "path"
        const val AUTOPLAY = "autoplay"
    }

    object Image {
        const val PATH = "path"
        const val CLICK_TO_SEE = "clickToSee"
    }

    object Problem {
        const val CONTENTS = "contents"
    }

    object Choice {
        const val ID = "id"
        const val CONTENT = "content"
    }

    object Explanation {
        const val CONTENTS = "contents"
    }

}