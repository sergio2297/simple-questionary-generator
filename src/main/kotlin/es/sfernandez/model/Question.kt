package es.sfernandez.model

data class Question(
    var title : String = "",
    var content : String = "",
    val image : Image = Image(),
    val sound : Sound = Sound(),
) {

    //---- Methods ----

}