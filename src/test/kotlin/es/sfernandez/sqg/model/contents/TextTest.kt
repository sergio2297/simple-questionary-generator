package es.sfernandez.sqg.model.contents

import es.sfernandez.sqg.BasicFixtures
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TextTest {

    //---- Attributes ----
    private lateinit var text : Text

    //---- Tests ----
    @Test
    fun textsAre_instanceOfContentTest() {
        text = Text()

        assertThat(text).isInstanceOf(Content::class.java)
    }

    @Test
    fun textsAre_textContentTypeTest() {
        text = Text()

        assertThat(text.type).isEqualTo(ContentType.TEXT)
    }

    @Test
    fun constructEmptyText_valueIsEmptyTest() {
        text = Text()

        assertThat(text.value).isEmpty()
    }

    @Test
    fun constructText_withValue_worksTest() {
        val value = BasicFixtures.SOME_TEXT_1

        text = Text(value)

        assertThat(text.value).isEqualTo(value)
    }

}