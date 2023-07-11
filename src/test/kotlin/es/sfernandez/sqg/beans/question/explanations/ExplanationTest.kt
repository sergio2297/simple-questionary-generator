package es.sfernandez.sqg.beans.question.explanations

import es.sfernandez.sqg.beans.contents.HasContents
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class ExplanationTest {

    //---- Attributes ----
    private lateinit var explanation : Explanation

    //---- Tests ----
    @Test
    fun problems_areInstanceOf_hasContentsTest() {
        explanation = Explanation()

        Assertions.assertThat(explanation).isInstanceOf(HasContents::class.java)
    }

    @Test
    fun afterConstructor_contentIsEmptyTest() {
        explanation = Explanation()

        Assertions.assertThat(explanation.groupOfContents.contents()).isEmpty()
    }
    
}