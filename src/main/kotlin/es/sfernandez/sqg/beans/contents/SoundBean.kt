package es.sfernandez.sqg.beans.contents

class SoundBean(
    override var path: String = "",
    var autoplay: Boolean = false,
) : HasResourcePath, ContentBean(ContentType.SOUND) {

}