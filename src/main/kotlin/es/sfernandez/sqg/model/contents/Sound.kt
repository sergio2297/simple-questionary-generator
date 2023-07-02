package es.sfernandez.sqg.model.contents

class Sound
private constructor(
    override val type: ContentType,
    override var path: String,
    val autoplay: Boolean,
) : IsResource, Content {

    //---- Constructor ----
    constructor() : this(ContentType.SOUND,"", true)

}