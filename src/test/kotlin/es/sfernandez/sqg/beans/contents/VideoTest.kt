package es.sfernandez.sqg.beans.contents

import es.sfernandez.sqg.BasicFixtures
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.test.BeforeTest

class VideoTest {

    //---- Attributes ----
    private lateinit var video: Video

    //---- Configuration ----
    @BeforeTest
    fun setup() {
        video = Video()
    }
    
    //---- Tests ----
    @Test
    fun videosAre_instanceOfContentTest() {
        assertThat(video).isInstanceOf(Content::class.java)
    }

    @Test
    fun videosAre_instanceOfIsResourceTest() {
        assertThat(video).isInstanceOf(IsResource::class.java)
    }

    @Test
    fun videosAre_videoContentTypeTest() {
        assertThat(video.type).isEqualTo(ContentType.VIDEO)
    }

    @Test
    fun videos_byDefault_hasEmptyResourcePathTest() {
        assertThat(video.path).isEmpty()
    }

    @Test
    fun videos_byDefault_areAutoplayTest() {
        assertThat(video.autoplay).isTrue()
    }

    @Test
    fun construct_withPath_worksTest() {
        val path = BasicFixtures.SOME_TEXT_1

        video = Video(path)

        assertThat(video.path).isEqualTo(path)
    }

    @Test
    fun construct_withAutoplay_worksTest() {
        val autoplay = true

        video = Video(autoplay = autoplay)

        assertThat(video.autoplay).isEqualTo(autoplay)
    }

}