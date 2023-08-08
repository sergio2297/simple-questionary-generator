package es.sfernandez.sqg.beans

import es.sfernandez.sqg.beans.contents.Image
import es.sfernandez.sqg.beans.question.Question

class Questionary(
    var title: String = "",
    var portrait: Image = Image(),
    var questions : Array<Question> = arrayOf(),
)