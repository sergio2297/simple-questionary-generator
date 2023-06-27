package es.sfernandez.model

class Sound(
    var soundPath : String
) : IsResource {

    //---- Attributes ----
    override val path
        get() = soundPath

    //---- Constructor ----
    constructor() : this("")

    //---- Methods ----
}