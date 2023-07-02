package es.sfernandez.sqg.model.question.answers.replies

import es.sfernandez.sqg.model.question.answers.AnswerFixtures.generateSomeChoices
import es.sfernandez.sqg.model.question.answers.choices.Choice
import es.sfernandez.sqg.model.question.answers.replies.MultipleReply
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class MultipleReplyTest {

    //---- Attributes ----
    private lateinit var reply : MultipleReply

    //---- Fixtures ----
    private val someChoices : Array<Choice> = generateSomeChoices(5)

    //---- Tests ----
    @Test
    fun get_returnsChoiceGivenInConstructionTest() {
        val choices = someChoices

        reply = MultipleReply(choices)

        Assertions.assertThat(reply.get()).contains(*choices)
    }

}