package es.sfernandez.sqg.deserializer.json

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.module.SimpleModule
import es.sfernandez.sqg.deserializer.DeserializationException
import es.sfernandez.sqg.deserializer.Deserializer
import es.sfernandez.sqg.deserializer.logs.*

abstract class JsonDeserializer<T> : Deserializer<T>, ProducesDeserializationLogs {

    //---- Attributes ----
    protected val mappedClass: Class<T>
    private val mapper: ObjectMapper

    protected val log = DeserializationLogsProducer()
    final override val logs: Array<DeserializationLog>
        get() = log.logs()

    //---- Constructor ----
    protected constructor(mappedClass : Class<T>) {
        this.mappedClass = mappedClass
        this.mapper = createMapper(mappedClass)
    }

    private fun createMapper(clazz : Class<T>) : ObjectMapper {
        val mapper = ObjectMapper()
        val module = SimpleModule()
        module.addDeserializer(clazz, createDeserializer())
        mapper.registerModule(module)
        return mapper
    }
    protected abstract fun createDeserializer() : StdDeserializer<T>

    //---- Methods ----
    final override fun deserialize(text : String) : T {
        log.logFactory = createLogFactory(text)
        return mapper.readValue(text, mappedClass)
    }

    private fun createLogFactory(text: String): DeserializationLogFactory {
        return DeserializationLogFactory(DeserializationContext(text, javaClass))
    }

    /**
     * Extracts the json tree from the given JsonParser and returns it as a JsonNode
     *
     * @param parser Parser which contains the JsonNode to extract
     * @return the json tree from the given JsonParser as a JsonNode
     * @throws DeserializationException if parser is null
     */
    protected fun extractJsonNode(parser: JsonParser?) : JsonNode {
        if(parser == null) throw DeserializationException("Error. Can not deserialize $mappedClass because JsonParser is null")
        return parser.codec.readTree(parser)
    }

    /**
     * Extracts the string value of field "key" from the "node". If missing, returns defaultValue
     *
     * @param node JsonNode where to extract the value from
     * @param key Field name to extract
     * @param defaultValue Default string value if missing property
     * @return the extracted string value
     */
    protected fun extractText(node: JsonNode, key: String, defaultValue: String = "") : String {
        val jsonProperty = node[key]

        if(jsonProperty == null) {
            log.warningMissingProperty(key, defaultValue)
            return defaultValue
        }

        if(!jsonProperty.isTextual) {
            log.warningIncorrectType(key, defaultValue)
            return defaultValue
        }

        return jsonProperty.asText()
    }

    /**
     * Extracts the boolean value of field "key" from the "node". If missing, returns defaultValue
     *
     * @param node JsonNode where to extract the value from
     * @param key Field name to extract
     * @param defaultValue Default boolean value if missing property
     * @return the extracted boolean value
     */
    protected fun extractBool(node: JsonNode, key: String, defaultValue: Boolean = false) : Boolean {
        val jsonProperty = node[key]

        if(jsonProperty == null) {
            log.warningMissingProperty(key, defaultValue.toString())
            return defaultValue
        }

        if(!jsonProperty.isBoolean) {
            log.warningIncorrectType(key, defaultValue.toString())
            return defaultValue
        }

        return jsonProperty.asBoolean()
    }

}