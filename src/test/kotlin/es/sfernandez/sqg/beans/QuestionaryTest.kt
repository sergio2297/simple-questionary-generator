package es.sfernandez.sqg.beans

import es.sfernandez.sqg.beans.question.Question
import org.assertj.core.api.Assertions.assertThat
import org.mockito.Mockito
import kotlin.test.BeforeTest
import kotlin.test.Test

class QuestionaryTest {

    //---- Attributes ----
    private lateinit var questionary: Questionary

    //---- Fixtures ----
    private val someQuestions = sequence<Question> { mockQuestion() }.take(5).toList().toTypedArray()

    //---- Methods ----
    private fun mockQuestion() : Question {
        return Mockito.mock(Question::class.java)
    }

    //---- Configuration ----
    @BeforeTest
    fun setup() {
        questionary = Questionary()
    }

    //---- Tests ----
    @Test
    fun constructor_createsByDefault_emptyQuestionaryTest() {
        assertThat(questionary.questions).isEmpty()
    }

    @Test
    fun construct_withArrayOfQuestions_worksTest() {
        questionary = Questionary(someQuestions)

        assertThat(questionary.questions).containsExactly(*someQuestions)
    }

}