package es.sfernandez.sqg.utilities.fixtures.beans.question

import es.sfernandez.sqg.beans.contents.Content
import es.sfernandez.sqg.beans.question.explanations.Explanation
import es.sfernandez.sqg.utilities.fixtures.beans.contents.UsesContentFixtures

interface UsesExplanationFixtures : UsesContentFixtures {

    fun anEmptyExplanation(): Explanation {
        return Explanation()
    }

    fun anEmptyExplanationWith(): ExplanationFixtureBuilder {
        return ExplanationFixtureBuilder(anEmptyExplanation())
    }

    fun anExplanation(): Explanation {
        return anEmptyExplanationWith()
            .contents(anArbitraryContent())
            .build()
    }

    fun anExplanationWith(): ExplanationFixtureBuilder {
        return ExplanationFixtureBuilder(anExplanation())
    }

    class ExplanationFixtureBuilder(
        private val explanation: Explanation
    ) {

        fun contents(vararg contents: Content) : ExplanationFixtureBuilder {
            explanation.groupOfContents.add(*contents)
            return this
        }

        fun build() : Explanation {
            return explanation
        }
    }
    
}