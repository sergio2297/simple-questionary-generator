package es.sfernandez.sqg.utilities.fixtures.beans.question.answers

import es.sfernandez.sqg.beans.contents.Content
import es.sfernandez.sqg.beans.contents.UnknownContent
import es.sfernandez.sqg.beans.question.answers.Choice
import es.sfernandez.sqg.utilities.fixtures.beans.contents.UsesContentFixtures

interface UsesChoiceFixtures : UsesContentFixtures {

    fun anEmptyChoice(): Choice {
        return Choice()
    }

    fun anEmptyChoiceWith(): ChoiceFixturesBuilder {
        return ChoiceFixturesBuilder(anEmptyChoice())
    }

    fun aChoice(): Choice {
        return anEmptyChoiceWith()
            .id("Some_ID")
            .content(anArbitraryContent())
            .build()
    }

    fun aChoiceWith(): ChoiceFixturesBuilder {
        return ChoiceFixturesBuilder(aChoice())
    }

    class ChoiceFixturesBuilder(
        private val choice: Choice
    ) {

        fun id(id: String): ChoiceFixturesBuilder {
            choice.id = id
            return this
        }

        fun emptyId(): ChoiceFixturesBuilder {
            choice.id = ""
            return this
        }

        fun content(content: Content): ChoiceFixturesBuilder {
            choice.content = content
            return this
        }

        fun unknownContent(): ChoiceFixturesBuilder {
            choice.content = UnknownContent()
            return this
        }

        fun build(): Choice {
            return choice
        }

    }

}