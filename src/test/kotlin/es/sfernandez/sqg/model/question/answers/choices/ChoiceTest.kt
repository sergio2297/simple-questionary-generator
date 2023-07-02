package es.sfernandez.sqg.model.question.answers.choices

import es.sfernandez.sqg.BasicFixtures
import es.sfernandez.sqg.model.question.answers.AnswerFixtures
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ChoiceTest {

    //---- Tests ----
    @Test
    fun createArbitraryChoice_worksTest() {
        val id = BasicFixtures.SOME_TEXT_1
        val content = AnswerFixtures.FooContent()

        val choice = Choice.create(id, content)

        assertThat(choice.id).isEqualTo(id)
        assertThat(choice.content).isSameAs(content)
    }

}