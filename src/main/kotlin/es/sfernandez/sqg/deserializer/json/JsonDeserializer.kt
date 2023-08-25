package es.sfernandez.sqg.deserializer.json

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.node.ArrayNode
import es.sfernandez.sqg.deserializer.DeserializationException
import es.sfernandez.sqg.deserializer.Deserializer
import es.sfernandez.sqg.deserializer.json.questionnaire.contents.GroupOfContentsJsonDeserializer
import es.sfernandez.sqg.deserializer.logs.*
import java.util.function.Function

abstract class JsonDeserializer<T> : Deserializer<T>, ProducesDeserializationLogs {

    //---- Attributes ----
    protected val mappedClass: Class<T>
    private val mapper: ObjectMapper

    protected val log = DeserializationLogsProducer()

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

    override fun logs(): Array<DeserializationLog> {
        return log.logs()
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
     * Casts the given JsonNode to an ArrayNode iff the node is an Array
     *
     * @param jsonNode Node to cast
     * @return the node as an ArrayNode
     * @throws DeserializationException if node is not an Array
     */
    protected fun toJsonArray(jsonNode: JsonNode) : ArrayNode {
        if(!jsonNode.isArray) throw DeserializationException("Error. ${GroupOfContentsJsonDeserializer::class.simpleName}" +
                " expects a JSON Array not: '${jsonNode}'")
        return jsonNode as ArrayNode
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

    /**
     * Extracts the enum value of field "key" from the "node". If missing, returns defaultValue
     *
     * @param node JsonNode where to extract the value from
     * @param key Field name to extract
     * @param defaultValue Default enum value if missing property
     * @return the extracted enum value
     */
    protected inline fun <reified T : Enum<T>> extractEnum(node: JsonNode, key: String, defaultValue: T) : T {
        val jsonProperty = node[key]

        if(jsonProperty == null) {
            log.warningMissingProperty(key, defaultValue.name)
            return defaultValue
        }

        if(!jsonProperty.isTextual) {
            log.warningIncorrectType(key, defaultValue.name)
            return defaultValue
        }

        return try {
            enumValueOf<T>(jsonProperty.asText())
        } catch (ex: IllegalArgumentException) {
            log.warningUndefinedEnumConstant(key, defaultValue)
            defaultValue
        }
    }

    /**
     * Extracts the object of field "key" from the "node". If missing, returns defaultValue
     *
     * @param node JsonNode where to extract the object from
     * @param key Field name to extract
     * @param deserializer Deserializer to use
     * @param defaultValue Default object value if missing property
     * @return the extracted object value
     */
    protected fun <T> extractObject(node: JsonNode, key: String,
                                deserializer: JsonDeserializer<T>, defaultValue: T) : T {
        val jsonProperty = node[key]

        if(jsonProperty == null) {
            log.warningMissingProperty(key, defaultValue.toString())
            return defaultValue
        }

        if(!jsonProperty.isObject) {
            log.warningIncorrectType(key, defaultValue.toString())
            return defaultValue
        }

        val deserializedObj = deserializer.deserialize(jsonProperty.toString())
        dumpLogsFrom(deserializer)

        return deserializedObj
    }

    /**
     * Extracts the array of field "key" from the "node". If missing, returns emptyArray
     *
     * Using this method when the array contains simple types. If it has objects, then use [extractArrayOfObjects]
     *
     * @param node JsonNode where to extract the array from
     * @param key Field name to extract
     * @param casting Function to cast array values
     * @return the extracted array
     */
    protected inline fun <reified T> extractArray(node: JsonNode, key: String, casting: Function<JsonNode, T>) : Array<T> {
        val jsonProperty = node[key]
        val defaultValue = emptyArray<T>()

        if(jsonProperty == null) {
            log.warningMissingProperty(key, defaultValue.toString())
            return defaultValue
        }

        if(!jsonProperty.isArray) {
            log.warningIncorrectType(key, defaultValue.toString())
            return defaultValue
        }

        return toJsonArray(jsonProperty)
            .asSequence()
            .map(casting::apply)
            .toList()
            .toTypedArray()
    }

    /**
     * Extracts the array of field "key" from the "node". If missing, returns emptyArray
     *
     * Using this method when the array contains objects. If it has simple types, then use [extractArray]
     *
     * @param node JsonNode where to extract the array from
     * @param key Field name to extract
     * @param deserializer JsonDeserializer used to deserialize each array's element
     * @return the extracted array
     */
    protected inline fun <reified T> extractArrayOfObjects(node: JsonNode, key: String, deserializer: JsonDeserializer<T>) : Array<T> {
        return extractArray(node, key)
            { jsonNode -> deserializer.deserialize(jsonNode.toString()) }
            .also { dumpLogsFrom(deserializer) }
    }

    /**
     * Dump logs from the JsonDeserializer received
     *
     * @param deserializer JsonDeserializer to dump
     */
    protected fun dumpLogsFrom(deserializer: JsonDeserializer<*>) {
        log.dump(deserializer)
    }

}