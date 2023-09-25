package es.sfernandez.sqg.utilities.fixtures.beans.question

import es.sfernandez.sqg.beans.contents.Content
import es.sfernandez.sqg.beans.question.problems.Problem
import es.sfernandez.sqg.utilities.fixtures.beans.contents.UsesContentFixtures

interface UsesProblemFixtures : UsesContentFixtures {

    fun anEmptyProblem(): Problem {
        return Problem()
    }

    fun anEmptyProblemWith(): ProblemFixtureBuilder {
        return ProblemFixtureBuilder(anEmptyProblem())
    }

    fun aProblem(): Problem {
        return anEmptyProblemWith()
            .contents(anArbitraryContent())
            .build()
    }

    fun aProblemWith(): ProblemFixtureBuilder {
        return ProblemFixtureBuilder(aProblem())
    }

    class ProblemFixtureBuilder(
        private val problem: Problem
    ) {

        fun contents(vararg contents: Content) : ProblemFixtureBuilder {
            problem.groupOfContents.add(*contents)
            return this
        }

        fun build() : Problem {
            return problem
        }
    }

}