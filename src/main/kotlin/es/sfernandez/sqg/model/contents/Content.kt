package es.sfernandez.sqg.model.contents

import es.sfernandez.sqg.model.question.problems.Problem
import es.sfernandez.sqg.model.question.answers.choices.Choice
import es.sfernandez.sqg.model.question.explanations.Explanation

/**
 * A Content represents a "box" of information or media that can be stored by [Problem], [Choice] and [Explanation].
 *
 * Usually, it is managed by a [GroupOfContents].
 *
 * @see Text
 * @see Sound
 * @see Image
 */
interface Content {

    /** The type of content */
    val type: ContentType

}