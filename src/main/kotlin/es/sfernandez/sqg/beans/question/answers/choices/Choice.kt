package es.sfernandez.sqg.beans.question.answers.choices

import es.sfernandez.sqg.beans.contents.Content
import es.sfernandez.sqg.beans.contents.UnknownContent

class Choice(
    var id : String = "",
    var content : Content = UnknownContent(),
)