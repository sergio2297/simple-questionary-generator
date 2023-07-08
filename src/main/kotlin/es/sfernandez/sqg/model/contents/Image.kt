package es.sfernandez.sqg.model.contents

class Image
private constructor(
    override val type: ContentType,
    override var path : String,
    var clickToSee : Boolean,
) : IsResource, Content {

    //---- Constructor ----
    constructor() : this(ContentType.IMAGE, "", false)

}