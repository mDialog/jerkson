package com.codahale.jerkson.tests

import com.codahale.jerkson.Json._
import com.codahale.jerkson.AST._
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec
import org.junit.Test

class JValueSpec extends FlatSpec with ShouldMatchers {
  "Selecting single nodes" should "return None with primitives" in {
    (parse[JValue]("8") \ "blah") should equal(JNull)
  }

  it should "return None on nonexistent fields" in {
    (parse[JValue]("{\"one\": \"1\"}") \ "two") should equal(JNull)
  }

  it should "return a JValue with an existing field" in {
    (parse[JValue]("{\"one\": \"1\"}") \ "one") should equal(JString("1"))
  }


  "Selecting array members" should "return None with primitives" in {
    (parse[JValue]("\"derp\"").apply(0)) should equal(JNull)
  }

  it should "return None on out of bounds" in {
    (parse[JValue]("[0, 1, 2, 3]").apply(4)) should equal(JNull)
  }

  it should "return a JValue" in {
    (parse[JValue]("[0, 1, 2, 3]").apply(2)) should equal(JInt(2))
  }


  "Deep selecting" should "return Nil with primitives" in {
    (parse[JValue]("0.234") \\ "herp") should equal(Nil)  //List()
  }

  it should "return Nil on nothing found" in {
    (parse[JValue]("{\"one\": {\"two\" : \"three\"}}") \\ "four") should equal(Nil)  //List()
  }

  it should "return single leaf nodes" in {
    (parse[JValue]("{\"one\": {\"two\" : \"three\"}}") \\ "two") should equal(Seq(JString("three")))
  }

  it should "return multiple leaf nodes" in {
    (parse[JValue]("{\"one\": {\"two\" : \"three\"}, \"four\": {\"two\" : \"five\"}}") \\ "two") should equal(Seq(JString("three"), JString("five")))
  }

}
