package es.sfernandez.sqg.model.validators.beans.contents

import es.sfernandez.sqg.beans.contents.IsResource
import es.sfernandez.sqg.model.validators.ValidatorTest
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class IsResourceValidatorTest : ValidatorTest<IsResource> {

    //---- Attributes ----
    override val validator = IsResourceValidator()

    //---- Methods ----
    private fun resourceWith(path: String) : IsResource {
        val resource = Mockito.mock(IsResource::class.java)
        Mockito.`when`(resource.path).thenReturn(path)
        return resource
    }

    //---- Tests ----
    @Test
    fun validate_resourceWithEmptyPath_returnErrorTest() {
        val emptyPath = ""

        assertIsErrorValidate(resourceWith(emptyPath))
    }

    @Test
    fun validate_resourceWithPathWithoutCharacters_returnErrorTest() {
        val pathWithBlank = "   \t "

        assertIsErrorValidate(resourceWith(pathWithBlank))
    }

    @Test
    fun validate_resourceWithNotEmptyPath_returnOkTest() {
        val somePath = "C:\\Users\\Photos\\img.jpg"

        assertIsOkValidate(resourceWith(somePath))
    }

}