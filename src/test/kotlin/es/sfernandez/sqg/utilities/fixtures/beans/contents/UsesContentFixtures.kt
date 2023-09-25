package es.sfernandez.sqg.utilities.fixtures.beans.contents

import es.sfernandez.sqg.beans.contents.Content
import org.mockito.Mockito

interface UsesContentFixtures {

    fun anArbitraryContent() : Content {
        return Mockito.mock(Content::class.java)
    }

}