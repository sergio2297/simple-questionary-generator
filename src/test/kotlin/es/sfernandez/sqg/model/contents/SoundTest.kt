package es.sfernandez.sqg.model.contents

import es.sfernandez.sqg.BasicFixtures
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.test.BeforeTest

class SoundTest {

    //---- Attributes ----
    private lateinit var sound: Sound

    //---- Configuration ----
    @BeforeTest
    fun setup() {
        sound = Sound()
    }
    
    //---- Tests ----
    @Test
    fun soundsAre_instanceOfContentTest() {
        sound = Sound()

        assertThat(sound).isInstanceOf(Content::class.java)
    }

    @Test
    fun soundsAre_instanceOfIsResourceTest() {
        assertThat(sound).isInstanceOf(IsResource::class.java)
    }
    
    @Test
    fun soundsAre_soundContentTypeTest() {
        sound = Sound()

        assertThat(sound.type).isEqualTo(ContentType.SOUND)
    }

    @Test
    fun sounds_byDefault_hasEmptyResourcePathTest() {
        assertThat(sound.path).isEmpty()
    }

    @Test
    fun sounds_byDefault_areAutoplayTest() {
        assertThat(sound.autoplay).isTrue()
    }

    @Test
    fun construct_withPath_worksTest() {
        val path = BasicFixtures.SOME_TEXT_1

        sound = Sound(path)

        assertThat(sound.path).isEqualTo(path)
    }

    @Test
    fun construct_withAutoplay_worksTest() {
        val autoplay = false

        sound = Sound(autoplay = autoplay)

        assertThat(sound.autoplay).isEqualTo(autoplay)
    }

}