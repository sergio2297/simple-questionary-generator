package es.sfernandez.sqg.model.contents

import es.sfernandez.sqg.BasicFixtures
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

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
    fun constructEmptyText_markupIsSimpleTest() {
        text = Text()

        assertThat(text.markup).isEqualTo(Text.Markup.SIMPLE)
    }

    @Test
    fun constructText_withValue_worksTest() {
        val value = BasicFixtures.SOME_TEXT_1

        text = Text(value)

        assertThat(text.value).isEqualTo(value)
    }

    @ParameterizedTest
    @EnumSource(Text.Markup::class)
    fun constructText_withMarkup_worksTest(markup: Text.Markup) {
        text = Text(markup = markup)

        assertThat(text.markup).isEqualTo(markup)
    }

}