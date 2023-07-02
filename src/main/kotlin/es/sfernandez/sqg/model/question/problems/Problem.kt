package es.sfernandez.sqg.model.question.problems

import es.sfernandez.sqg.model.contents.Image
import es.sfernandez.sqg.model.contents.Sound

data class Problem(
    var title : String = "",
    var content : String = "",
    val image : Image = Image(),
    val sound : Sound = Sound(),
) {

    //---- Methods ----

}