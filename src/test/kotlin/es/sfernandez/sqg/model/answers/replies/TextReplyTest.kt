package es.sfernandez.sqg.model.answers.replies

import es.sfernandez.sqg.BasicFixtures
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TextReplyTest {

    //---- Attributes ----
    private lateinit var reply : TextReply

    //---- Fixtures ----

    //---- Tests ----
    @Test
    fun get_returnsTextGivenInConstructionTest() {
        val text = BasicFixtures.SOME_TEXT

        reply = TextReply(text)

        assertThat(reply.get()).isEqualTo(text)
    }

}

