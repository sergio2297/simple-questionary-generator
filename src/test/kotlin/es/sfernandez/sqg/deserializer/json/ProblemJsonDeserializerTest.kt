package es.sfernandez.sqg.deserializer.json

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ProblemJsonDeserializerTest {

    //---- Constants and Definitions ----

    //---- Attributes ----
    val deserializer = ProblemJsonDeserializer()

    //---- Fixtures ----

    //---- Configuration ----

    //---- Methods ----

    //---- Tests ----
    @Test
    fun testsimple() {
        val text = "{" + "}"

        val problem = deserializer.deserialize(text)

        assertThat(problem.title).isEqualTo("Titulo")
    }

}