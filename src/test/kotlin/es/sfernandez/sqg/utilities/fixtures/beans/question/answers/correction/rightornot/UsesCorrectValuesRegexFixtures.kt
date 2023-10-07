package es.sfernandez.sqg.utilities.fixtures.beans.question.answers.correction.rightornot

import es.sfernandez.sqg.beans.question.answers.correction.rightornot.CorrectValuesRegex

interface UsesCorrectValuesRegexFixtures {

    fun aCorrectValuesRegexWith(): CorrectValuesRegexFixtureBuilder {
        return CorrectValuesRegexFixtureBuilder()
    }

    fun aCorrectValuesRegex(): CorrectValuesRegex {
        return aCorrectValuesRegexWith()
            .pattern(".*")
            .build()
    }

    class CorrectValuesRegexFixtureBuilder {

        //---- Attributes ----
        private var pattern = ""

        //---- Methods ----
        fun pattern(pattern: String): CorrectValuesRegexFixtureBuilder {
            this.pattern = pattern
            return this
        }

        fun emptyPattern(): CorrectValuesRegexFixtureBuilder {
            this.pattern = ""
            return this
        }

        fun build(): CorrectValuesRegex {
            return CorrectValuesRegex(Regex(pattern))
        }

    }

}