package es.sfernandez.sqg.beans.question.answers

import es.sfernandez.sqg.beans.contents.Content
import es.sfernandez.sqg.beans.contents.ContentType
import es.sfernandez.sqg.beans.question.answers.choices.Choice

object AnswerFixtures {

    /** An arbitrary type of [Content] */
    class FooContent(override val type: ContentType = ContentType.UNKNOWN) : Content

    /**
     * Generate num [FooChoice]
     *
     * @param num Number of Choices to generate
     * @return array of num Choices
     */
    fun generateSomeChoices(num : Int) : Array<Choice> {
        return generateSequence { Choice() }.take(num).toList().toTypedArray()
    }

}