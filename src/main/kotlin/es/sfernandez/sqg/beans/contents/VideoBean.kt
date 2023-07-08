package es.sfernandez.sqg.beans.contents

class VideoBean(
    override var path: String,
    var autoplay: Boolean,
) : HasResourcePath, ContentBean(ContentType.VIDEO) {

}