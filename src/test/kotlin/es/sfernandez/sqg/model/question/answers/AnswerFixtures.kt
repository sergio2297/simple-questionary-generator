package es.sfernandez.sqg.model.question.answers

import es.sfernandez.sqg.model.contents.Content
import es.sfernandez.sqg.model.question.answers.choices.Choice

object AnswerFixtures {

    /** An arbitrary type of [Content] */
    class FooContent : Content

    /** An arbitrary type of [Choice] */
    class FooChoice(override val id: String, override val content: Content) : Choice {
        constructor() : this("", FooContent())
    }

    /**
     * Generate num [FooChoice]
     *
     * @param num Number of Choices to generate
     * @return array of num Choices
     */
    fun generateSomeChoices(num : Int) : Array<Choice> {
        return generateSequence { FooChoice() }.take(num).toList().toTypedArray()
    }

}