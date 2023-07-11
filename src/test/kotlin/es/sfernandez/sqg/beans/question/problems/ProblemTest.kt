package es.sfernandez.sqg.beans.question.problems

import es.sfernandez.sqg.beans.contents.HasContents
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ProblemTest {

    //---- Attributes ----
    private lateinit var problem : Problem

    //---- Tests ----
    @Test
    fun problems_areInstanceOf_hasContentsTest() {
        problem = Problem()

        assertThat(problem).isInstanceOf(HasContents::class.java)
    }

    @Test
    fun afterConstructor_contentIsEmptyTest() {
        problem = Problem()

        assertThat(problem.groupOfContents.contents()).isEmpty()
    }

}