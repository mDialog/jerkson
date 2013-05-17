package com.codahale.jerkson.deser

import com.fasterxml.jackson.databind.{ DeserializationContext, JsonDeserializer }
import com.fasterxml.jackson.core.{ JsonToken, JsonParser }
import com.fasterxml.jackson.databind.JavaType
import scala.collection.immutable.IntMap
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer

class IntMapDeserializer(valueType: JavaType) extends JsonDeserializer[Object] with ResolvableDeserializer {
  var valueDeserializer: JsonDeserializer[Object] = _

  def deserialize(jp: JsonParser, ctxt: DeserializationContext) = {
    var map = IntMap.empty[Object]

    if (jp.getCurrentToken == JsonToken.START_OBJECT) {
      jp.nextToken()
    }

    if (jp.getCurrentToken != JsonToken.FIELD_NAME &&
      jp.getCurrentToken != JsonToken.END_OBJECT) {
      throw ctxt.mappingException(valueType.getRawClass)
    }

    while (jp.getCurrentToken != JsonToken.END_OBJECT) {
      try {
        val name = jp.getCurrentName.toInt
        jp.nextToken()
        map += ((name, valueDeserializer.deserialize(jp, ctxt)))
        jp.nextToken()
      } catch {
        case e: IllegalArgumentException ⇒ throw ctxt.mappingException(classOf[IntMap[_]])
      }
    }

    map
  }

  def resolve(ctxt: DeserializationContext) {
    valueDeserializer = ctxt.findRootValueDeserializer(valueType)
  }

  override def isCachable = true
}
