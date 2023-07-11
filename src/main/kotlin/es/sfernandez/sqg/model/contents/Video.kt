package es.sfernandez.sqg.model.contents

class Video
private constructor(
    override val type: ContentType,
    override var path: String,
    var autoplay: Boolean,
) : IsResource, Content {

    //---- Constructor ----
    constructor(path : String = "", autoplay: Boolean = true) : this(ContentType.VIDEO, path, autoplay)

}