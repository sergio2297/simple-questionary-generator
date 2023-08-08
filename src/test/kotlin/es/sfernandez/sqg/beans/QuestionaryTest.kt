package es.sfernandez.sqg.beans

import es.sfernandez.sqg.BasicFixtures
import es.sfernandez.sqg.beans.contents.Image
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
    fun constructor_createsByDefault_emptyTitleTest() {
        assertThat(questionary.title).isEmpty()
    }

    @Test
    fun construct_withTitle_worksTest() {
        val title = BasicFixtures.SOME_TEXT_1

        questionary = Questionary(title = title)

        assertThat(questionary.title).isEqualTo(title)
    }

    @Test
    fun constructor_createsByDefault_emptyPortraitTest() {
        assertThat(questionary.portrait.path).isEmpty()
    }

    @Test
    fun construct_withImage_worksTest() {
        val image = Mockito.mock(Image::class.java)

        questionary = Questionary(portrait = image)

        assertThat(questionary.portrait).isEqualTo(image)
    }

    @Test
    fun constructor_createsByDefault_emptyQuestionaryTest() {
        assertThat(questionary.questions).isEmpty()
    }

    @Test
    fun construct_withArrayOfQuestions_worksTest() {
        questionary = Questionary(questions = someQuestions)

        assertThat(questionary.questions).containsExactly(*someQuestions)
    }

}