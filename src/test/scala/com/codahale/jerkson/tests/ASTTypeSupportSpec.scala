package com.codahale.jerkson.tests

import com.codahale.jerkson.Json._
import com.codahale.jerkson.AST._
import org.scalatest.matchers.ShouldMatchers
import org.scalatest._

class ASTTypeSupportSpec extends FlatSpec with ShouldMatchers {

  "An AST.JInt" should "generates a JSON int" in {
    generate(JInt(15)) should equal("15")
  }

  it should "be parsable from a JSON int" in {
    parse[JInt]("15") should equal(JInt(15))
  }

  it should "be parsable from a JSON int as a JValue" in {
    parse[JValue]("15") should equal(JInt(15))
  }

  "An AST.JFloat" should "generate a JSON int" in {
    generate(JFloat(15.1)) should equal("15.1")
  }

  it should "be parsable from a JSON float" in {
    parse[JFloat]("15.1") should equal (JFloat(15.1))
  }

  it should "be parsable from a JSON float as a JValue" in {
    parse[JValue]("15.1") should equal (JFloat(15.1))
  }

  "An AST.JString" should "generate a JSON string" in {
    generate(JString("foo")) should equal ("\"foo\"")
  }

  it should "be parsable from a JSON int" in {
    parse[JString]("\"foo\"") should equal (JString("foo"))
  }

  it should "be parsable from a JSON int as a JValue" in {
    parse[JValue]("\"foo\"") should equal (JString("foo"))
  }

  "An AST.JNull" should "generate a JSON null" in {
    generate(JNull) should equal ("null")
  }

  it should "be parsable from a JSON null" in {
    parse[JNull.type]("null") should equal (JNull)
  }

  it should "be parsable from a JSON null as a JValue" in {
    parse[JValue]("null") should equal (JNull)
  }

  "An AST.JBoolean" should "generate a JSON true" in {
    generate(JBoolean(true)) should equal ("true")
  }

  it should "generate a JSON false" in {
    generate(JBoolean(false)) should equal ("false")
  }

  it should "be parsable from a JSON true" in {
    parse[JBoolean]("true") should equal (JBoolean(true))
  }

  it should "be parsable from a JSON false" in {
    parse[JBoolean]("false") should equal (JBoolean(false))
  }

  it should "be parsable from a JSON true as a JValue" in {
    parse[JValue]("true") should equal (JBoolean(true))
  }

  it should "be parsable from a JSON false as a JValue" in {
    parse[JValue]("false") should equal (JBoolean(false))
  }

  "An AST.JArray" should "generates a JSON array of ints" in {
    generate(JArray(List(JInt(1), JInt(2), JInt(3)))) should equal("[1,2,3]")
  }

  it should "be parsable from a JSON array of ints" in {
    parse[JArray]("[1,2,3]") should equal(JArray(List(JInt(1), JInt(2), JInt(3))))
  }

  it should "be parsable from a JSON array of ints as a JValue" in {
    parse[JValue]("[1,2,3]") should equal(JArray(List(JInt(1), JInt(2), JInt(3))))
  }

  //objects in following tests here for DRY
  val obj = JObject(List(JField("id", JInt(1)), JField("name", JString("foo"))))
  val objSerialized = """{"id":1,"name":"foo"}"""

  "An AST.JObject" should "generates a JSON object with matching field values" in {
    generate(obj) should equal(objSerialized)
  }

  it should "be parsable from a JSON object" in {
    parse[JObject](objSerialized) should equal(obj)
  }

  it should "be parsable from a JSON object as a JValue" in {
    parse[JValue](objSerialized) should equal(obj)
  }

  it should "be parsable from an empty JSON object" in {
    parse[JObject]("{}") should equal(JObject(Nil))
  }
}

