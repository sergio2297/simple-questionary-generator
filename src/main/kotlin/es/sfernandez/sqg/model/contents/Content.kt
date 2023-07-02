package es.sfernandez.sqg.model.contents

import es.sfernandez.sqg.model.question.problems.Problem
import es.sfernandez.sqg.model.question.answers.choices.Choice
import es.sfernandez.sqg.model.question.explanations.Explanation

/**
 * Content represents a "box" of information or media that can be contained by [Problem], [Choice] and [Explanation]
 */
interface Content {

    /** The type of content */
    val type: ContentType

}