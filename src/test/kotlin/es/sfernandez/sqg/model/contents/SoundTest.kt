package es.sfernandez.sqg.model.contents

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SoundTest {

    //---- Attributes ----
    private lateinit var sound: Sound

    //---- Tests ----
    @Test
    fun soundsAre_instanceOfContentTest() {
        sound = Sound()

        assertThat(sound).isInstanceOf(Content::class.java)
    }

    @Test
    fun soundsAre_soundContentTypeTest() {
        sound = Sound()

        assertThat(sound.type).isEqualTo(ContentType.SOUND)
    }

}