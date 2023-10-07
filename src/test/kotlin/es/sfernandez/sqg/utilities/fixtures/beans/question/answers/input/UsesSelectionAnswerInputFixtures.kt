package es.sfernandez.sqg.utilities.fixtures.beans.question.answers.input

import es.sfernandez.sqg.beans.question.answers.Choice
import es.sfernandez.sqg.beans.question.answers.input.SelectionAnswerInput
import es.sfernandez.sqg.beans.question.answers.input.SingleSelectionAnswerInput
import es.sfernandez.sqg.utilities.fixtures.beans.question.answers.UsesChoiceFixtures

interface UsesSelectionAnswerInputFixtures : UsesChoiceFixtures {

    fun anEmptySelectionAnswerInput(): SelectionAnswerInput {
        return SingleSelectionAnswerInput()
    }

    fun anEmptySelectionAnswerInputWith(): SelectionAnswerInputFixturesBuilder {
        return SelectionAnswerInputFixturesBuilder(anEmptySelectionAnswerInput())
    }

    fun aSelectionAnswerInput(): SelectionAnswerInput {
        return anEmptySelectionAnswerInputWith()
            .numOfPossibleChoices(5)
            .build()
    }

    fun aSelectionAnswerInputWith(): SelectionAnswerInputFixturesBuilder {
        return SelectionAnswerInputFixturesBuilder(aSelectionAnswerInput())
    }

    class SelectionAnswerInputFixturesBuilder(
        private val input: SelectionAnswerInput
    ) : UsesSelectionAnswerInputFixtures {

        fun noPossibleChoices(): SelectionAnswerInputFixturesBuilder {
            input.possibleChoices = arrayOf()
            return this
        }

        fun possibleChoices(vararg choices: Choice): SelectionAnswerInputFixturesBuilder {
            input.possibleChoices = arrayOf(*choices)
            return this
        }

        fun numOfPossibleChoices(numOfChoices: Int): SelectionAnswerInputFixturesBuilder {
            return possibleChoices(
                *generateSequence { aChoice() }
                    .take(numOfChoices)
                    .toList()
                    .toTypedArray()
            )
        }

        fun build(): SelectionAnswerInput {
            return input
        }

    }

}