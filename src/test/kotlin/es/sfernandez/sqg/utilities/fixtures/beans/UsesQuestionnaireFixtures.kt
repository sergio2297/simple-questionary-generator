package es.sfernandez.sqg.utilities.fixtures.beans

import es.sfernandez.sqg.beans.Questionnaire
import es.sfernandez.sqg.beans.question.Question
import es.sfernandez.sqg.utilities.fixtures.beans.question.UsesQuestionFixtures

interface UsesQuestionnaireFixtures : UsesQuestionFixtures {

    fun anEmptyQuestionnaire(): Questionnaire {
        return Questionnaire()
    }

    fun anEmptyQuestionnaireWith(): QuestionnaireFixtureBuilder {
        return QuestionnaireFixtureBuilder(anEmptyQuestionnaire())
    }

    fun aQuestionnaire(): Questionnaire {
        return anEmptyQuestionnaireWith()
            .title("Questionnaire")
            .numOfQuestions(5)
            .build()
    }

    fun aQuestionnaireWith(): QuestionnaireFixtureBuilder {
        return QuestionnaireFixtureBuilder(aQuestionnaire())
    }

    class QuestionnaireFixtureBuilder(
        private val questionnaire: Questionnaire
    ) : UsesQuestionnaireFixtures {

        fun title(title: String): QuestionnaireFixtureBuilder {
            questionnaire.title = title
            return this
        }

        fun emptyTitle(): QuestionnaireFixtureBuilder {
            return title("   ")
        }

        fun questions(vararg questions: Question): QuestionnaireFixtureBuilder {
            questionnaire.questions = arrayOf(*questions)
            return this
        }

        fun numOfQuestions(numOfQuestions: Int): QuestionnaireFixtureBuilder {
            return questions(*someQuestions(numOfQuestions))
        }

        fun build(): Questionnaire {
            return questionnaire
        }

    }

}