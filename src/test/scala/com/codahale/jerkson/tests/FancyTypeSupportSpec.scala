package com.codahale.jerkson.tests

import java.net.URI
import org.junit.Test
import com.codahale.jerkson.Json._
import java.util.UUID
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class FancyTypeSupportSpec extends FlatSpec with ShouldMatchers {
  "A URI" should "generate a JSON string" in {
    generate(new URI("http://example.com/resource?query=yes")) should equal("\"http://example.com/resource?query=yes\"")
  }

  it should "be parsable from a JSON string" in {
    parse[URI]("\"http://example.com/resource?query=yes\"") should equal(new URI("http://example.com/resource?query=yes"))
  }


  val uuid = UUID.fromString("a62047e4-bfb5-4d71-aad7-1a6b338eee63")

  "A UUID" should "generate a JSON string" in {
    generate(uuid) should equal("\"a62047e4-bfb5-4d71-aad7-1a6b338eee63\"")
  }

  it should "be parsable from a JSON string" in {
    parse[UUID]("\"a62047e4-bfb5-4d71-aad7-1a6b338eee63\"") should equal(uuid)
  }

}
