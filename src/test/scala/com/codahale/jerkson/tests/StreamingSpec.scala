package com.codahale.jerkson.tests

import com.codahale.jerkson.Json._
import java.io.ByteArrayInputStream
import org.junit.Test
import com.codahale.jerkson.AST.JInt
import org.scalatest.matchers.ShouldMatchers
import org.scalatest._

class StreamingSpec extends FlatSpec with ShouldMatchers {
  val json = """[
      {"id":1, "name": "Coda"},
      {"id":2, "name": "Niki"},
      {"id":3, "name": "Biscuit"},
      {"id":4, "name": "Louie"}
    ]"""

  "An AST.JInt" should "generate a JSON int" in {
    //generate(JInt(15)) should equal("15")
    stream[CaseClass](new ByteArrayInputStream(json.getBytes)).toList should equal(
      CaseClass(1, "Coda") ::
        CaseClass(2, "Niki") ::
        CaseClass(3, "Biscuit") ::
        CaseClass(4, "Louie") ::
        Nil)
  }
}