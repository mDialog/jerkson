package com.codahale.jerkson.tests

import com.codahale.jerkson.Json._
import com.codahale.jerkson.ParsingException
import java.io.ByteArrayInputStream
import org.junit.Test
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class EdgeCaseSpec extends FlatSpec with ShouldMatchers {

  "Deserializing lists" should "not cache Seq builders" in {
    parse[List[Int]]("[1,2,3,4]") should equal(List(1, 2, 3, 4))
    parse[List[Int]]("[1,2,3,4]") should equal(List(1, 2, 3, 4))
  }

  "Parsing a JSON array of ints with nulls" should "be readable as a List[Option[Int]]" in {
    parse[List[Option[Int]]]("[1,2,null,4]") should equal(List(Some(1), Some(2), None, Some(4)))
  }

  "Deserializing maps" should "not cache Map builders" in {
    parse[Map[String, Int]](""" {"one":1, "two": 2} """) should equal(Map("one" -> 1, "two" -> 2))
    parse[Map[String, Int]](""" {"one":1, "two": 2} """) should equal(Map("one" -> 1, "two" -> 2))
  }

  "Parsing malformed JSON" should "throw a ParsingException with an informative message" in {
    intercept[ParsingException] {
      parse[Boolean]("jjf8;09")
    }

    intercept[ParsingException] {
      parse[CaseClass]("{\"ye\":1")
    }
  }

  "Parsing invalid JSON" should "throw a ParsingException with an informative message" in {
    intercept[ParsingException] {
      parse[CaseClass]("900")
    }
    intercept[ParsingException] {
      parse[CaseClass]("{\"woo\": 1}")
    }
  }

  "Parsing an empty document" should "throw a ParsingException with an informative message" in {
    val input = new ByteArrayInputStream(Array.empty)
    intercept[ParsingException] {

      parse[CaseClass](input)
    }
  }
}
