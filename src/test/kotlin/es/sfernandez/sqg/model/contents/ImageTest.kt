package es.sfernandez.sqg.model.contents

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ImageTest {

    //---- Attributes ----
    private lateinit var image: Image

    //---- Tests ----
    @Test
    fun imagesAre_instanceOfContentTest() {
        image = Image()

        assertThat(image).isInstanceOf(Content::class.java)
    }

    @Test
    fun imagesAre_imageContentTypeTest() {
        image = Image()

        assertThat(image.type).isEqualTo(ContentType.IMAGE)
    }

}