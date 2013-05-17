package com.codahale.jerkson.tests

import com.codahale.jerkson.Json._
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import com.codahale.jerkson.AST._
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.IntNode

class BasicTypeSupportSpec extends FlatSpec with ShouldMatchers {
  "A Byte" should "generate a JSON int" in {
    generate(15.toByte) should equal("15")
  }

  it should "be parsable from a JSON int" in {
    parse[Byte]("15") should equal(15)
  }

  "A Short" should "generate a JSON int" in {
    generate(15.toShort) should equal("15")
  }

  it should "be parsable from a JSON int" in {
    parse[Short]("15") should equal(15)
  }

  "An Int" should "generate a JSON int" in {
    generate(15) should equal("15")
  }

  it should "be parsable from a JSON int" in {
    parse[Int]("15") should equal(15)
  }

  "A Long" should "generate a JSON int" in {
    generate(15L) should equal("15")
  }

  it should "be parsable from a JSON int" in {
    parse[Long]("15") should equal(15L)
  }

  "A BigInt" should "generate a JSON int" in {
    generate(BigInt(15)) should equal("15")
  }

  it should "be parsable from a JSON int" in {
    parse[BigInt]("15") should equal(BigInt(15))
  }

  it should "be parsable from a JSON string" in {
    parse[BigInt]("\"15\"") should equal(BigInt(15))
  }

  "A Float" should "generate a JSON float" in {
    generate(15.1F) should equal("15.1")
  }

  it should "be parsable from a JSON float" in {
    parse[Float]("15.1") should equal(15.1F)
  }

  "A Double" should "generate a JSON float" in {
    generate(15.1D) should equal("15.1")
  }

  it should "be parsable from a JSON float" in {
    parse[Double]("15.1") should equal(15.1D)
  }

  "A BigDecimal" should "generate a JSON float" in {
    generate(BigDecimal(15.1)) should equal("15.1")
  }

  it should "be parsable from a JSON float" in {
    parse[BigDecimal]("15.1") should equal(BigDecimal(15.1))
  }

  it should "be parsable from a JSON int" in {
    parse[BigDecimal]("15") should equal(BigDecimal(15.0))
  }

  "A String" should "generate a JSON string" in {
    generate("foo") should equal("\"foo\"")
  }

  it should "be parsable from a JSON string" in {
    parse[String]("\"foo\"") should equal("foo")
  }

  "A StringBuilder" should "generate a JSON string" in {
    generate(new StringBuilder("foo")) should equal("\"foo\"")
  }

  it should "be parsable from a JSON string" in {
    parse[StringBuilder]("\"foo\"").toString should equal("foo")
  }

  "A null Object" should "generate a JSON null" in {
    generate[Object](null) should equal("null")
  }

  it should "be parsable from a JSON null" in {
    parse[Object]("null") should equal(null)
  }

  "A Boolean" should "generate a JSON true" in {
    generate(true) should equal("true")
  }

  it should "generate a JSON false" in {
    generate(false) should equal("false")
  }

  it should "be parsable from a JSON true" in {
    parse[Boolean]("true") should equal(true)
  }

  it should "be parsable from a JSON false" in {
    parse[Boolean]("false") should equal(false)
  }

  "A Some[Int]" should "generate a JSON int" in {
    generate(Some(12)) should equal("12")
  }

  it should "be parsable from a JSON int as an Option[Int]" in {
    parse[Option[Int]]("12") should equal(Some(12))
  }

  "A None" should "generate a JSON null" in {
    generate(None) should equal("null")
  }

  it should "be parsable from a JSON null as an Option[Int]" in {
    parse[Option[Int]]("null") should equal(None)
  }

  "A Left[String]" should "generate a JSON string" in {
    generate(Left("foo")) should equal("\"foo\"")
  }

  it should "be parsable from a JSON string as an Either[String, Int]" in {
    parse[Either[String, Int]]("\"foo\"") should equal(Left("foo"))
  }

  "A JsonNode" should "generate whatever the JSON node is" in {
    generate(new IntNode(2)) should equal("2")
  }

  it should "be parsable from a JSON AST node" in {
    parse[JsonNode]("2") should equal(new IntNode(2))
  }

  it should "be parsable from a JSON AST node as a specific type" in {
    parse[IntNode]("2") should equal(new IntNode(2))
  }

  it should "be parsable itself" in {
    parse[Int](new IntNode(2)) should equal(2)
  }

  "An Array[Int]" should "generate a JSON array of ints" in {
    generate(Array(1, 2, 3)) should equal("[1,2,3]")
  }

  it should "be parsable from a JSON array of ints" in {
    parse[Array[Int]]("[1,2,3]").toList should equal(List(1, 2, 3))
  }

  it should "be parsable from an empty JSON array" in {
    parse[Array[Int]]("[]").toList should equal(List.empty)
  }
}