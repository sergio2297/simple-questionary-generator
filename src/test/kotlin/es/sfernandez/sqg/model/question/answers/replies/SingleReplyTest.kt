package es.sfernandez.sqg.model.question.answers.replies

import es.sfernandez.sqg.model.question.answers.AnswerFixtures
import es.sfernandez.sqg.model.question.answers.choices.Choice
import es.sfernandez.sqg.model.question.answers.replies.SingleReply
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SingleReplyTest {

    //---- Attributes ----
    private lateinit var reply : SingleReply

    //---- Fixtures ----
    private val aChoice = AnswerFixtures.FooChoice()

    //---- Tests ----
    @Test
    fun get_returnsChoiceGivenInConstructionTest() {
        val choice : Choice = aChoice

        reply = SingleReply(choice)

        assertThat(reply.get()).isSameAs(choice)
    }
}