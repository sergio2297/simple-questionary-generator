package es.sfernandez.sqg.model.contents

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class VideoTest {

    //---- Attributes ----
    private lateinit var video: Video

    //---- Tests ----
    @Test
    fun videosAre_instanceOfContentTest() {
        video = Video()

        assertThat(video).isInstanceOf(Content::class.java)
    }

    @Test
    fun videosAre_videoContentTypeTest() {
        video = Video()

        assertThat(video.type).isEqualTo(ContentType.VIDEO)
    }

}