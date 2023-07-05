package es.sfernandez.sqg.model.contents

class Video
private constructor(
    override val type: ContentType,
    override var path: String,
    val autoplay: Boolean,
) : IsResource, Content {

    //---- Constructor ----
    constructor() : this(ContentType.VIDEO, "", true)
}