package es.sfernandez.sqg.model.correcting.replies

import es.sfernandez.sqg.utilities.Fixtures
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TextReplyTest {

    //---- Attributes ----
    private lateinit var reply : TextReply

    //---- Tests ----
    @Test
    fun get_returnsTextGivenInConstructionTest() {
        val text = Fixtures.SOME_TEXT_1

        reply = TextReply(text)

        assertThat(reply.get()).isEqualTo(text)
    }

}

