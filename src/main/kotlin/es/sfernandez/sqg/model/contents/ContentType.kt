package es.sfernandez.sqg.model.contents

enum class ContentType(val jsonName : String) {
    UNKNOWN("unknown"),
    TEXT("text"),
    IMAGE("image"),
    SOUND("sound"),
    VIDEO("video")
}
