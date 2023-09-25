package es.sfernandez.sqg.utilities.fixtures.beans.contents

import es.sfernandez.sqg.beans.contents.IsResource
import org.mockito.Mockito

interface UsesIsResourceFixtures {

    fun anEmptyResource(): IsResource {
        return aResourceWith("")
    }

    fun aResource(): IsResource {
        return aResourceWith("C:\\Users\\Photos\\img.jpg")
    }

    fun aResourceWith(path: String) : IsResource {
        val resource = Mockito.mock(IsResource::class.java)
        Mockito.`when`(resource.path).thenReturn(path)
        return resource
    }

}