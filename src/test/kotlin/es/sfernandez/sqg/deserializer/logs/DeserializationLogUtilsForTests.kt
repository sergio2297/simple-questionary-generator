package es.sfernandez.sqg.deserializer.logs

import es.sfernandez.sqg.deserializer.json.JsonDeserializer
import org.assertj.core.api.Assertions

object DeserializationLogUtilsForTests {

    fun checkDeserializerLogsContainsWarningWithWord(deserializer: JsonDeserializer<*>, word: String) {
        checkDeserializerLogsContainLevelWithWord(deserializer, DeserializationLog.Level.WARNING, word)
    }

    fun checkDeserializerLogsContainsErrorWithWord(deserializer: JsonDeserializer<*>, word: String) {
        checkDeserializerLogsContainLevelWithWord(deserializer, DeserializationLog.Level.ERROR, word)
    }

    private fun checkDeserializerLogsContainLevelWithWord(
        deserializer: JsonDeserializer<*>,
        level: DeserializationLog.Level, word: String) {
        Assertions.assertThat(deserializer.logs().asSequence()
            .any { log ->
                log.level == level && log.msg.contains(word)
            })
            .isTrue()
    }

}