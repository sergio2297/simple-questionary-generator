package es.sfernandez.sqg.model.contents

data class Image(
    override var path : String
) : IsResource, Content {

    //---- Constructor ----
    constructor() : this("") {}

}