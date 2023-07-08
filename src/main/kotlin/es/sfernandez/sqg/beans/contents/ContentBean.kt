package es.sfernandez.sqg.beans.contents

import es.sfernandez.sqg.model.question.problems.Problem
import es.sfernandez.sqg.model.question.answers.choices.Choice
import es.sfernandez.sqg.model.question.explanations.Explanation

/**
 * Content represents a "box" of information or media that can be contained by [Problem], [Choice] and [Explanation]
 */
/**
 * Content bean
 *
 * @property type The type of content
 * @constructor Create empty Content bean
 */
abstract class ContentBean(val type: ContentType) {
}