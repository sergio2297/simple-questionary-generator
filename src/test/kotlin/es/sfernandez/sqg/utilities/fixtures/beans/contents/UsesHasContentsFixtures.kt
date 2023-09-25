package es.sfernandez.sqg.utilities.fixtures.beans.contents

import es.sfernandez.sqg.beans.contents.GroupOfContents
import es.sfernandez.sqg.beans.contents.HasContents
import org.mockito.Mockito

interface UsesHasContentsFixtures : UsesGroupOfContentsFixtures {

    fun anEmptyHasContents(): HasContents {
        val hasContents = Mockito.mock(HasContents::class.java)
        Mockito.`when`(hasContents.groupOfContents).thenReturn(anEmptyGroupOfContents())
        return hasContents
    }

    fun anEmptyHasContentsWith(): HasContentsFixtureBuilder {
        return HasContentsFixtureBuilder(anEmptyHasContents())
    }

    fun anArbitraryHasContents(): HasContents {
        return anEmptyHasContentsWith()
            .groupOfContents(aGroupOfContents())
            .build()
    }

//    fun anArbitraryHasContentsWith(): HasContentsFixtureBuilder {
//        return HasContentsFixtureBuilder(anArbitraryHasContents())
//    }

    class HasContentsFixtureBuilder(
        private val hasContents: HasContents
    ) {

        fun groupOfContents(groupOfContents: GroupOfContents): HasContentsFixtureBuilder {
            Mockito.`when`(hasContents.groupOfContents).thenReturn(groupOfContents)
            return this
        }

        fun build(): HasContents {
            return hasContents
        }

    }

}