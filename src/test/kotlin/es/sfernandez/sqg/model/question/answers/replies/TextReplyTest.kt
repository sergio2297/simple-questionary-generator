package es.sfernandez.sqg.model.question.answers.replies

import es.sfernandez.sqg.BasicFixtures
import es.sfernandez.sqg.model.question.answers.replies.TextReply
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TextReplyTest {

    //---- Attributes ----
    private lateinit var reply : TextReply

    //---- Tests ----
    @Test
    fun get_returnsTextGivenInConstructionTest() {
        val text = BasicFixtures.SOME_TEXT_1

        reply = TextReply(text)

        assertThat(reply.get()).isEqualTo(text)
    }

}

