package es.sfernandez.sqg.utilities.fixtures.beans.question.answers.correction.rightornot

import es.sfernandez.sqg.beans.question.answers.correction.rightornot.RightChoiceIds

interface UsesRightChoiceIdsFixtures {

    fun aRightChoiceIds(): RightChoiceIds {
        return aRightChoiceIdsWith()
            .rightIds("ID_1", "1.3", "a")
            .build()
    }

    fun aRightChoiceIdsWith(): RightChoiceIdsFixtureBuilder {
        return RightChoiceIdsFixtureBuilder()
    }

    class RightChoiceIdsFixtureBuilder(
        private vararg var choiceIds: String
    ) {

        fun noRightIds(): RightChoiceIdsFixtureBuilder {
            choiceIds = arrayOf()
            return this
        }

        fun rightIds(vararg ids: String): RightChoiceIdsFixtureBuilder {
            choiceIds = arrayOf(*ids)
            return this
        }

        fun build(): RightChoiceIds {
            return RightChoiceIds(*choiceIds)
        }

    }

}