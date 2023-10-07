package es.sfernandez.sqg.utilities.fixtures.beans.question.answers.correction.rightornot

import es.sfernandez.sqg.beans.question.answers.correction.rightornot.PossibleValues

interface UsesPossibleValuesFixtures {

    fun aPossibleValues(): PossibleValues {
        return aPossibleValuesWith()
            .values("1", "a", "-%")
            .build()
    }

    fun aPossibleValuesWith(): PossibleValuesFixtureBuilder {
        return PossibleValuesFixtureBuilder()
    }

    class PossibleValuesFixtureBuilder(
        private vararg var possibleValues: String
    ) {

        fun noValues(): PossibleValuesFixtureBuilder {
            possibleValues = arrayOf()
            return this
        }

        fun values(vararg values: String): PossibleValuesFixtureBuilder {
            possibleValues = arrayOf(*values)
            return this
        }

        fun build(): PossibleValues {
            return PossibleValues(*possibleValues)
        }

    }

}