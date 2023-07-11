package es.sfernandez.sqg.model.contents

/**
 * A Sound is a [Content] of type [ContentType.SOUND] that represents a sound media
 *
 * @property autoplay true if it wouldn't be necessary to click "someplace" to play the sound
 */
class Sound
private constructor(
    override val type: ContentType,
    override var path: String,
    val autoplay: Boolean,
) : IsResource, Content {

    //---- Constructor ----
    constructor(path: String = "", autoplay: Boolean = true) : this(ContentType.SOUND,path, autoplay)

}