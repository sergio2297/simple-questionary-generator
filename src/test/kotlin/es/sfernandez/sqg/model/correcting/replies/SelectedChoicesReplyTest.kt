package es.sfernandez.sqg.model.correcting.replies

import es.sfernandez.sqg.beans.question.answers.AnswerFixtures
import es.sfernandez.sqg.beans.question.answers.Choice
import es.sfernandez.sqg.model.correcting.replies.SelectedChoicesReply
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class SelectedChoicesReplyTest {

    //---- Attributes ----
    private lateinit var reply : SelectedChoicesReply

    //---- Fixtures ----
    private val someChoices : Array<Choice> = AnswerFixtures.generateSomeChoices(5)

    //---- Tests ----
    @Test
    fun get_returnsChoiceGivenInConstructionTest() {
        val choice = someChoices[0]

        reply = SelectedChoicesReply(choice)

        Assertions.assertThat(reply.get()).containsExactly(choice)
    }

    @Test
    fun get_returnsChoicesGivenInConstructionTest() {
        val choices = someChoices

        reply = SelectedChoicesReply(*choices)

        Assertions.assertThat(reply.get()).containsExactly(*choices)
    }

}