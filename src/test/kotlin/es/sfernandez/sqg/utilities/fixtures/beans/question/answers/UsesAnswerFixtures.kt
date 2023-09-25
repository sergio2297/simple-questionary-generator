package es.sfernandez.sqg.utilities.fixtures.beans.question.answers

import es.sfernandez.sqg.beans.question.answers.Answer
import es.sfernandez.sqg.beans.question.answers.correction.AnswerCorrection
import es.sfernandez.sqg.beans.question.answers.input.AnswerInput
import es.sfernandez.sqg.utilities.fixtures.beans.question.answers.correction.UsesAnswerCorrectionFixtures
import es.sfernandez.sqg.utilities.fixtures.beans.question.answers.input.UsesAnswerInputFixtures

interface UsesAnswerFixtures : UsesAnswerInputFixtures, UsesAnswerCorrectionFixtures {

    fun anUnspecifiedAnswer() : Answer {
        return Answer()
    }

    fun anUnspecifiedAnswerWith() : AnswerFixtureBuilder {
        return AnswerFixtureBuilder(anUnspecifiedAnswer())
    }

    fun anAnswer() : Answer {
        return anUnspecifiedAnswerWith()
            .input(anAnswerInput())
            .correction(anAnswerCorrection())
            .build()
    }

    fun anAnswerWith() : AnswerFixtureBuilder {
        return AnswerFixtureBuilder(anAnswer())
    }

    class AnswerFixtureBuilder(
        private val answer: Answer
    ) : UsesAnswerFixtures {

        fun input(input: AnswerInput): AnswerFixtureBuilder {
            this.answer.input = input
            return this
        }

        fun unspecifiedInput(): AnswerFixtureBuilder {
            this.answer.input = anUnspecifiedAnswerInput()
            return this
        }

        fun correction(correction: AnswerCorrection): AnswerFixtureBuilder {
            this.answer.correction = correction
            return this
        }

        fun unspecifiedCorrection(): AnswerFixtureBuilder {
            this.answer.correction = anUnspecifiedAnswerCorrection()
            return this
        }

        fun build() : Answer {
            return answer
        }
    }

}