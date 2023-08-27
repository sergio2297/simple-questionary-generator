package es.sfernandez.sqg.beans.question

import es.sfernandez.sqg.beans.question.answers.Answer
import es.sfernandez.sqg.beans.question.answers.correction.AnswerCorrection
import es.sfernandez.sqg.beans.question.answers.input.AnswerInput
import es.sfernandez.sqg.beans.question.explanations.Explanation
import es.sfernandez.sqg.beans.question.problems.Problem
import es.sfernandez.sqg.utilities.Fixtures
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import kotlin.test.BeforeTest

class QuestionTest {

    //---- Attributes ----
    private lateinit var question : Question

    //---- Fixtures ----
    private val aProblem = mockProblem()
    private val anAnswer = mockAnswer()
    private val anExplanation = mockExplanation()
    
    //---- Configuration ----
    @BeforeTest
    fun setup() {
        question = Question()
    }
    
    //---- Methods ----
    private fun mockProblem() : Problem {
        return Mockito.mock(Problem::class.java)
    }

    private fun mockAnswer() : Answer {
        return Mockito.mock(Answer::class.java)
    }

    private fun mockExplanation() : Explanation {
        return Mockito.mock(Explanation::class.java)
    }
    
    //---- Tests ----
    @Test
    fun constructor_initializeTitle_asEmptyTest() {
        assertThat(question.title).isEmpty()
    }
    
    @Test
    fun setTitle_worksTest() {
        val title = Fixtures.SOME_TEXT_1
        
        question.title = title
        
        assertThat(question.title).isEqualTo(title)
    }
    
    @Test
    fun constructor_initializeProblem_withNoContentsTest() {
        assertThat(question.problem.groupOfContents.size()).isZero()
    }

    @Test
    fun setProblem_worksTest() {
        question.problem = aProblem

        assertThat(question.problem).isSameAs(aProblem)
    }

    @Test
    fun constructor_initializeAnswer_asUnspecifiedTest() {
        assertThat(question.answer.input.type).isEqualTo(AnswerInput.Type.UNSPECIFIED)
        assertThat(question.answer.correction.type).isEqualTo(AnswerCorrection.Type.UNSPECIFIED)
    }

    @Test
    fun setAnswer_worksTest() {
        question.answer = anAnswer

        assertThat(question.answer).isSameAs(anAnswer)
    }

    @Test
    fun constructor_initializeExplanation_withNoContentsTest() {
        assertThat(question.explanation.groupOfContents.size()).isZero()
    }

    @Test
    fun setExplanation_worksTest() {
        question.explanation = anExplanation

        assertThat(question.explanation).isSameAs(anExplanation)
    }

}