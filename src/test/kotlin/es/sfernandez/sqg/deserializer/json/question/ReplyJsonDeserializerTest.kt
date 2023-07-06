package es.sfernandez.sqg.deserializer.json.question

import es.sfernandez.sqg.deserializer.json.question.ReplyJsonDeserializer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ReplyJsonDeserializerTest {

    //---- Constants and Definitions ----

    //---- Attributes ----
    val deserializer = ReplyJsonDeserializer()

    //---- Fixtures ----

    //---- Configuration ----

    //---- Methods ----

    //---- Tests ----
    @Test
    fun testsimple() {
        val text = "{" + "}"

        val reply = deserializer.deserialize(text)

        assertThat(reply.get()).isEqualTo("text")
    }

}