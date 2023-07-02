package es.sfernandez.sqg.deserializer.json

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
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