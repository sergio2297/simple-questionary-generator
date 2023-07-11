package es.sfernandez.sqg.beans.contents

/**
 * An Image is a [Content] of type [ContentType.IMAGE] that represents an image media
 *
 * @property clickToSee true if it would be necessary to click "someplace" to show the image
 */
class Image
private constructor(
    override val type: ContentType,
    override var path : String = "",
    var clickToSee : Boolean = false,
) : IsResource, Content {

    //---- Constructor ----
    constructor(path : String = "", clickToSee: Boolean = false) : this(ContentType.IMAGE, path, clickToSee)

}