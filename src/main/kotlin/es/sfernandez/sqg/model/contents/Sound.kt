package es.sfernandez.sqg.model.contents

class Sound(
    override var path : String,
    val autoplay : Boolean,
) : IsResource, Content {

    //---- Constructor ----
    constructor() : this("", true)

}