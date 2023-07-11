package es.sfernandez.sqg.deserializer.logs

import es.sfernandez.sqg.deserializer.json.JsonDeserializer
import org.assertj.core.api.Assertions

object DeserializationLogUtilsForTests {

    fun checkDeserializerLogsContainsWarningWithKey(deserializer: JsonDeserializer<*>, key: String) {
        checkDeserializerLogsContainLevelWithKey(deserializer, DeserializationLog.Level.WARNING, key)
    }

    fun checkDeserializerLogsContainsErrorWithKey(deserializer: JsonDeserializer<*>, key: String) {
        checkDeserializerLogsContainLevelWithKey(deserializer, DeserializationLog.Level.ERROR, key)
    }

    private fun checkDeserializerLogsContainLevelWithKey(deserializer: JsonDeserializer<*>,
                                                         level: DeserializationLog.Level, key: String) {
        Assertions.assertThat(deserializer.logs.asSequence()
            .any { log ->
                log.level == level && log.msg.contains(key)
            })
            .isTrue()
    }

}