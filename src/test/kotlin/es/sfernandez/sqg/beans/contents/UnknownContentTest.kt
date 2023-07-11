package es.sfernandez.sqg.beans.contents

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class UnknownContentTest {

    //---- Attributes ----
    private lateinit var content: UnknownContent

    //---- Tests ----
    @Test
    fun unknownContentsAre_instanceOfContentTest() {
        content = UnknownContent()

        Assertions.assertThat(content).isInstanceOf(Content::class.java)
    }

    @Test
    fun unknownContentsAre_UnknownContentTypeTest() {
        content = UnknownContent()

        Assertions.assertThat(content.type).isEqualTo(ContentType.UNKNOWN)
    }
}