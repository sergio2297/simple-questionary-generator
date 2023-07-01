package es.sfernandez.sqg.model.answers

import es.sfernandez.sqg.BasicFixtures
import es.sfernandez.sqg.model.answers.choices.Choice

object AnswerFixtures {

    /** An arbitrary type of [Choice] */
    class FooChoice : Choice

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