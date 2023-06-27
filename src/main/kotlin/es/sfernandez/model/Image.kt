package es.sfernandez.model

class Image(
    var imagePath : String
) : IsResource {

    //---- Attributes ----
    override val path: String
        get() = imagePath

    //---- Constructor ----
    constructor() : this("") {}

    //---- Methods ----

}