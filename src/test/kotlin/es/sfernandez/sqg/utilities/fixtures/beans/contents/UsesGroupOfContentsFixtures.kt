package es.sfernandez.sqg.utilities.fixtures.beans.contents

import es.sfernandez.sqg.beans.contents.Content
import es.sfernandez.sqg.beans.contents.GroupOfContents

interface UsesGroupOfContentsFixtures : UsesContentFixtures {

    fun anEmptyGroupOfContents(): GroupOfContents {
        return GroupOfContents()
    }

    fun anEmptyGroupOfContentsWith(): GroupOfContentsFixtureBuilder {
        return GroupOfContentsFixtureBuilder(anEmptyGroupOfContents())
    }

    fun aGroupOfContents(): GroupOfContents {
        return anEmptyGroupOfContentsWith()
            .contents(anArbitraryContent())
            .build()
    }

    fun aGroupOfContentsWith(): GroupOfContentsFixtureBuilder {
        return GroupOfContentsFixtureBuilder(aGroupOfContents())
    }

    class GroupOfContentsFixtureBuilder(
        private val groupOfContents: GroupOfContents
    ) {

        fun contents(vararg contents: Content) : GroupOfContentsFixtureBuilder {
            groupOfContents.add(*contents)
            return this
        }

        fun build() : GroupOfContents {
            return groupOfContents
        }
    }

}