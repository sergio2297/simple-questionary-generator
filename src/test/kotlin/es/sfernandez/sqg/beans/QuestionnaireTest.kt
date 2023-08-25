package es.sfernandez.sqg.beans

import es.sfernandez.sqg.BasicFixtures
import es.sfernandez.sqg.beans.contents.Image
import es.sfernandez.sqg.beans.question.Question
import org.assertj.core.api.Assertions.assertThat
import org.mockito.Mockito
import kotlin.test.BeforeTest
import kotlin.test.Test

class QuestionnaireTest {

    //---- Attributes ----
    private lateinit var questionnaire: Questionnaire

    //---- Fixtures ----
    private val someQuestions = sequence<Question> { mockQuestion() }.take(5).toList().toTypedArray()

    //---- Methods ----
    private fun mockQuestion() : Question {
        return Mockito.mock(Question::class.java)
    }

    //---- Configuration ----
    @BeforeTest
    fun setup() {
        questionnaire = Questionnaire()
    }

    //---- Tests ----
    @Test
    fun constructor_createsByDefault_emptyTitleTest() {
        assertThat(questionnaire.title).isEmpty()
    }

    @Test
    fun construct_withTitle_worksTest() {
        val title = BasicFixtures.SOME_TEXT_1

        questionnaire = Questionnaire(title = title)

        assertThat(questionnaire.title).isEqualTo(title)
    }

    @Test
    fun constructor_createsByDefault_emptyPortraitTest() {
        assertThat(questionnaire.portrait.path).isEmpty()
    }

    @Test
    fun construct_withImage_worksTest() {
        val image = Mockito.mock(Image::class.java)

        questionnaire = Questionnaire(portrait = image)

        assertThat(questionnaire.portrait).isEqualTo(image)
    }

    @Test
    fun constructor_createsByDefault_emptyQuestionnaireTest() {
        assertThat(questionnaire.questions).isEmpty()
    }

    @Test
    fun construct_withArrayOfQuestions_worksTest() {
        questionnaire = Questionnaire(questions = someQuestions)

        assertThat(questionnaire.questions).containsExactly(*someQuestions)
    }

}