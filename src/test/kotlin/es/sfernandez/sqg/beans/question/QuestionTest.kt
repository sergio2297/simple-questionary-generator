package es.sfernandez.sqg.beans.question

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class QuestionTest {

    //---- Constants and Definitions ----

    //---- Attributes ----
    private lateinit var question : Question

    //---- Fixtures ----

    //---- Configuration ----

    //---- Methods ----

    //---- Tests ----
    @Test
    fun afterDefaultConstructor_titleIsEmptyTest() {
        question = Question()

        Assertions.assertThat(question.title).isEmpty()
    }

}