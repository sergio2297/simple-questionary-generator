package es.sfernandez.sqg.model.contents

/**
 * A Video is a [Content] of type [ContentType.VIDEO] that represents a video media
 *
 * @property autoplay true if it wouldn't be necessary to click "someplace" to play the video
 */
class Video
private constructor(
    override val type: ContentType,
    override var path: String,
    var autoplay: Boolean,
) : IsResource, Content {

    //---- Constructor ----
    constructor(path : String = "", autoplay: Boolean = true) : this(ContentType.VIDEO, path, autoplay)

}