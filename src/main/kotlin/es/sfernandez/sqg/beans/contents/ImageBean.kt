package es.sfernandez.sqg.beans.contents

class ImageBean(
    override var path : String = "",
    var clickToSee : Boolean = false,
) : HasResourcePath, ContentBean(ContentType.IMAGE) {

}