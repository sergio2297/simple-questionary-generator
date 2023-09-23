package es.sfernandez.sqg.model.validators.beans.contents

import es.sfernandez.sqg.beans.contents.Content
import es.sfernandez.sqg.beans.contents.GroupOfContents
import es.sfernandez.sqg.beans.contents.HasContents
import es.sfernandez.sqg.model.validators.ValidatorTest
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class HasContentsValidatorTest : ValidatorTest<HasContents> {

    //---- Attributes ----
    override val validator = HasContentsValidator()

    //---- Methods ----
    private fun hasContentsWith(vararg contents: Content) : HasContents {
        val hasContents = Mockito.mock(HasContents::class.java)

        val groupOfContents = GroupOfContents()
        groupOfContents.add(*contents)

        Mockito.`when`(hasContents.groupOfContents).thenReturn(groupOfContents)
        return hasContents
    }

    private fun emptyHasContents() : HasContents {
        return hasContentsWith()
    }

    private fun aContent() : Content {
        return Mockito.mock(Content::class.java)
    }

    //---- Test ----
    @Test
    fun validate_emptyHasContents_returnsWarningTest() {
        assertIsWarningValidate(emptyHasContents())
    }

    @Test
    fun validate_notEmptyHasContents_returnsOkTest() {
        assertIsOkValidate(hasContentsWith(aContent()))
    }

}