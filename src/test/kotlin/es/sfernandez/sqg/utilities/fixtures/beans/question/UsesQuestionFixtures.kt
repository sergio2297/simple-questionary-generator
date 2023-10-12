package es.sfernandez.sqg.utilities.fixtures.beans.question

import es.sfernandez.sqg.beans.question.Question
import es.sfernandez.sqg.beans.question.answers.Answer
import es.sfernandez.sqg.beans.question.explanations.Explanation
import es.sfernandez.sqg.beans.question.problems.Problem
import es.sfernandez.sqg.utilities.fixtures.beans.question.answers.UsesAnswerFixtures

interface UsesQuestionFixtures : UsesProblemFixtures, UsesAnswerFixtures, UsesExplanationFixtures {

    fun anEmptyQuestion() : Question {
        return Question()
    }

    fun anEmptyQuestionWith() : QuestionFixtureBuilder {
        return QuestionFixtureBuilder(anEmptyQuestion())
    }

    fun aQuestion() : Question {
        return anEmptyQuestionWith()
            .title("Title")
            .problem(aProblem())
            .answer(anAnswer())
            .explanation(anExplanation())
            .build()
    }

    fun aQuestionWith() : QuestionFixtureBuilder {
        return QuestionFixtureBuilder(aQuestion())
    }

    fun someQuestions(numOfQuestions: Int): Array<Question> {
        return generateSequence { aQuestion() }
            .take(numOfQuestions)
            .toList()
            .toTypedArray()
    }

    class QuestionFixtureBuilder(
        private val question: Question
    ) : UsesQuestionFixtures {

        //---- Methods ----
        fun title(title: String): QuestionFixtureBuilder {
            question.title = title
            return this
        }

        fun emptyTitle(): QuestionFixtureBuilder {
            question.title = "   "
            return this
        }

        fun problem(problem: Problem): QuestionFixtureBuilder {
            question.problem = problem
            return this
        }

        fun emptyProblem(): QuestionFixtureBuilder {
            question.problem = anEmptyProblem()
            return this
        }

        fun answer(answer: Answer): QuestionFixtureBuilder {
            question.answer = answer
            return this
        }

        fun unspecifiedAnswer(): QuestionFixtureBuilder {
            question.answer = anUnspecifiedAnswer()
            return this
        }

        fun explanation(explanation: Explanation): QuestionFixtureBuilder {
            question.explanation = explanation
            return this
        }

        fun build(): Question {
            return question
        }

    }
}