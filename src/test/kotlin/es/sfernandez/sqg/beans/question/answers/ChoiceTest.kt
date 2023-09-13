package es.sfernandez.sqg.beans.question.answers

import es.sfernandez.sqg.beans.contents.UnknownContent
import es.sfernandez.sqg.utilities.Fixtures
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.test.BeforeTest

class ChoiceTest {

    //---- Attributes ----
    private lateinit var choice: Choice

    //---- Configuration ----
    @BeforeTest
    fun setup() {
        choice = Choice()
    }

    //---- Tests ----
    @Test
    fun choices_byDefault_hasEmptyIdTest() {
        assertThat(choice.id).isEmpty()
    }

    @Test
    fun choices_byDefault_hasUnknownContentTest() {
        assertThat(choice.content).isInstanceOf(UnknownContent::class.java)
    }

    @Test
    fun construct_withId_worksTest() {
        val id = Fixtures.SOME_TEXT_1

        choice = Choice(id)

        assertThat(choice.id).isEqualTo(id)
    }

    @Test
    fun construct_withContent_worksTest() {
        val someContent = AnswerFixtures.FooContent()

        choice = Choice(content = someContent)

        assertThat(choice.content).isEqualTo(someContent)
    }

}