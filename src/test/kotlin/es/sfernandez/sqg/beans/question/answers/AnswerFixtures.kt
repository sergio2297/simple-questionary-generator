package es.sfernandez.sqg.beans.question.answers

import es.sfernandez.sqg.beans.contents.*
import es.sfernandez.sqg.beans.question.answers.choices.Choice

object AnswerFixtures {

    /** An arbitrary type of [Content] */
    class FooContent(override val type: ContentType = ContentType.UNKNOWN) : Content

    val SOME_TEXT = Text()
    val SOME_SOUND = Sound()
    val SOME_VIDEO = Video()
    val SOME_IMAGE = Image()

    /**
     * Generate num [Choice]
     *
     * @param num Number of Choices to generate
     * @return array of num Choices
     */
    fun generateSomeChoices(num : Int) : Array<Choice> {
        return generateSequence { Choice() }.take(num).toList().toTypedArray()
    }

}