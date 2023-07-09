package es.sfernandez.sqg.model.contents

import es.sfernandez.sqg.BasicFixtures
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.test.BeforeTest

class ImageTest {

    //---- Attributes ----
    private lateinit var image: Image

    //---- Consfiguration ----
    @BeforeTest
    fun setup() {
        image = Image()
    }

    //---- Tests ----
    @Test
    fun imagesAre_instanceOfContentTest() {
        assertThat(image).isInstanceOf(Content::class.java)
    }

    @Test
    fun imagesAre_instanceOfIsResourceTest() {
        assertThat(image).isInstanceOf(IsResource::class.java)
    }

    @Test
    fun imagesAre_imageContentTypeTest() {
        assertThat(image.type).isEqualTo(ContentType.IMAGE)
    }

    @Test
    fun images_byDefault_hasEmptyResourcePathTest() {
        assertThat(image.path).isEmpty()
    }

    @Test
    fun images_byDefault_areNotClickToSeeTest() {
        assertThat(image.clickToSee).isFalse()
    }

    @Test
    fun construct_withPath_worksTest() {
        val path = BasicFixtures.SOME_TEXT_1

        image = Image(path)

        assertThat(image.path).isEqualTo(path)
    }

    @Test
    fun construct_withClickToSee_worksTest() {
        val clickToSee = true

        image = Image(clickToSee = clickToSee)

        assertThat(image.clickToSee).isEqualTo(clickToSee)
    }

}