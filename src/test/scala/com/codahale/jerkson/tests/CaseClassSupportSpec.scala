package com.codahale.jerkson.tests

import com.codahale.jerkson.Json._
import com.codahale.jerkson.ParsingException
import com.fasterxml.jackson.databind.node.IntNode
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import collection.immutable.Map

class CaseClassSupportSpec extends FlatSpec with ShouldMatchers {
  "A basic case class" should "generate a JSON object with matching field values" in {
    generate(CaseClass(1, "Coda")) should equal("""{"id":1,"name":"Coda"}""")
  }

  it should "be parsable from a JSON object with corresponding fields" in {
    parse[CaseClass]("""{"id":1,"name":"Coda"}""") should equal(CaseClass(1, "Coda"))
  }

  it should "be parsable from a JSON object with extra fields" in {
    parse[CaseClass]("""{"id":1,"name":"Coda","derp":100}""") should equal(CaseClass(1, "Coda"))
  }

  it should "not be parsable from an incomplete JSON object" in {
    intercept[ParsingException] {
      parse[CaseClass]("""{"id":1}""")
    }
  }

  "A case class with a default field" should "be parsable from an incomplete JSON object" in {
    parse[CaseClassWithDefaultString]("""{"id":1}""") should equal(CaseClassWithDefaultString(1, "Coda"))
    parse[CaseClassWithDefaultInt]("""{"id":1}""") should equal(CaseClassWithDefaultInt(1, 42))
  }

  "A case class with lazy fields" should "generate a JSON object with those fields evaluated" in {
    generate(CaseClassWithLazyVal(1)) should equal("""{"id":1,"woo":"yeah"}""")
  }

  it should "be parsable from a JSON object without those fields" in {
    parse[CaseClassWithLazyVal]("""{"id":1}""") should equal(CaseClassWithLazyVal(1))
  }

  it should "not be parsable from an incomplete JSON object" in {
    intercept[ParsingException] {
      parse[CaseClassWithLazyVal]("""{}""")
    }
  }

  "A case class with ignored members" should "generate a JSON object without those fields" in {
    generate(CaseClassWithIgnoredField(1)) should equal("""{"id":1}""")
    generate(CaseClassWithIgnoredFields(1)) should equal("""{"id":1}""")
  }

  it should "be parsable from a JSON object without those fields" in {
    parse[CaseClassWithIgnoredField]("""{"id":1}""") should equal(CaseClassWithIgnoredField(1))
    parse[CaseClassWithIgnoredFields]("""{"id":1}""") should equal(CaseClassWithIgnoredFields(1))
  }

  it should "not be parsable from an incomplete JSON object" in {
    intercept[ParsingException] {
      parse[CaseClassWithIgnoredField]("""{}""")
    }

    intercept[ParsingException] {
      parse[CaseClassWithIgnoredFields]("""{}""")
    }
  }

  "A case class with transient members" should "generate a JSON object without those fields" in {
    generate(CaseClassWithTransientField(1)) should equal("""{"id":1}""")
  }

  it should "be parsable from a JSON object without those fields" in {
    parse[CaseClassWithTransientField]("""{"id":1}""") should equal(CaseClassWithTransientField(1))
  }

  it should "not be parsable from an incomplete JSON object" in {
    intercept[ParsingException] {
      parse[CaseClassWithTransientField]("""{}""")
    }
  }

  "A case class with overloaded members" should "generate a JSON object without those fields" in {
    generate(CaseClassWithOverloadedField(1)) should equal("""{"id":1}""")
  }

  "A case class with Option[String] member" should "generate a feild if the number is Some" in {
    generate(CaseClassWithOption(Some("what"))) should equal("""{"value":"what"}""")
  }

  it should "be parsable from a JSON object with that field" in {
    parse[CaseClassWithOption]("""{"value":"what"}""") should equal(CaseClassWithOption(Some("what")))
  }

  it should "not generate a field if the member is None" in {
    generate(CaseClassWithOption(None)) should equal("""{}""")
  }

  it should "be parsable from a JSON object without that field" in {
    parse[CaseClassWithOption]("""{}""") should equal(CaseClassWithOption(None))
  }

  it should "be parsable from a JSON object with a null value for that field" in {
    parse[CaseClassWithOption]("""{"value":null}""") should equal(CaseClassWithOption(None))
  }

  "A case class with a JsonNode member" should "generate a feild of the given type" in {
    generate(CaseClassWithJsonNode(new IntNode(2))) should equal("""{"value":2}""")
  }

  "A case class with members of all ScalaSig types" should "be pasrable from a JSON object with those fields" in {
    val json = """
                 {
                   "map": {
                     "one": "two"
                   },
                   "set": [1, 2, 3],
                   "string": "woo",
                   "list": [4, 5, 6],
                   "seq": [7, 8, 9],
                   "sequence": [10, 11, 12],
                   "collection": [13, 14, 15],
                   "indexedSeq": [16, 17, 18],
                   "randomAccessSeq": [19, 20, 21],
                   "vector": [22, 23, 24],
                   "bigDecimal": 12.0,
                   "bigInt": 13,
                   "int": 1,
                   "long": 2,
                   "char": "x",
                   "bool": false,
                   "short": 14,
                   "byte": 15,
                   "float": 34.5,
                   "double": 44.9,
                   "any": true,
                   "anyRef": "wah",
                   "intMap": {
                     "1": "1"
                   },
                   "longMap": {
                     "2": 2
                   }
                 }
               """

    parse[CaseClassWithAllTypes](json) should equal(
      CaseClassWithAllTypes(
        map = Map("one" -> "two"),
        set = Set(1, 2, 3),
        string = "woo",
        list = List(4, 5, 6),
        seq = Seq(7, 8, 9),
        indexedSeq = IndexedSeq(16, 17, 18),
        vector = Vector(22, 23, 24),
        bigDecimal = BigDecimal("12.0"),
        bigInt = BigInt("13"),
        int = 1,
        long = 2L,
        char = 'x',
        bool = false,
        short = 14,
        byte = 15,
        float = 34.5f,
        double = 44.9d,
        any = true,
        anyRef = "wah",
        intMap = Map(1 -> 1),
        longMap = Map(2L -> 2L)))
  }

  "A case class nested inside of an object" should "be parsable from a JSON object" in {
    parse[OuterObject.NestedCaseClass]("""{"id": 1}""") should equal(OuterObject.NestedCaseClass(1))
  }

  "A case class nested inside of an object nested inside of an object" should "be parsable from a JSON object" in {
    parse[OuterObject.InnerObject.SuperNestedCaseClass]("""{"id": 1}""") should equal(OuterObject.InnerObject.SuperNestedCaseClass(1))
  }

  "`A case class with two constructor" should "be parsable from a JSON object with the same parameters as the case accessor" in {
    parse[CaseClassWithTwoConstructors]("""{"id":1,"name":"Bert"}""") should equal(CaseClassWithTwoConstructors(1, "Bert"))
  }

  it should "be parsable from a JSON object which works with the second constructor" in {
    //?
    intercept[ParsingException] {
      parse[CaseClassWithTwoConstructors]("""{"id":1}""")
    }
  }

  "A case class with snake-cased fields" should "be parsable from a snake-cased JSON object" in {
    parse[CaseClassWithSnakeCase]("""{"one_thing":"yes","two_thing":"good"}""") should equal(CaseClassWithSnakeCase("yes", "good"))
  }

  it should "generates a snake-cased JSON object" in {
    generate(CaseClassWithSnakeCase("yes", "good")) should equal("""{"one_thing":"yes","two_thing":"good"}""")
  }

  it should "throws errors with the snake-cased field names present" in {
    intercept[ParsingException] {
      parse[CaseClassWithSnakeCase]("""{"one_thing":"yes"}""")
    }
  }

  "A case class with array members" should "be parsable from a JSON object" in {
    val c = parse[CaseClassWithArrays]("""{"one":"1","two":["a","b","c"],"three":[1,2,3]}""")

    c.one should equal("1")
    c.two should equal(Array("a", "b", "c"))
    c.three should equal(Array(1, 2, 3))
  }

  it should "generate a JSON object" in {
    generate(CaseClassWithArrays("1", Array("a", "b", "c"), Array(1, 2, 3))) should equal(
      """{"one":"1","two":["a","b","c"],"three":[1,2,3]}""")
  }

  "A parameterized case class" should "be parsable from a JSON object" in {
    val c = parse[CaseClassWithParameter[Int]]("""{"id":"1","list":[2,3]}""")

    c.id should equal(1)
    c.list should equal(List(2, 3))
  }

  it should "generate a JSON object" in {
    generate(CaseClassWithParameter[Int](1, List(2, 3))) should equal(
      """{"id":1,"list":[2,3]}""")
  }

  "A multi-level parameterized case class" should "be parsable from a JSON object" in {
    val c = parse[CaseClassWithParameter[List[CaseClassWithParameter[Int]]]]("""{"id":"1","list":[[{"id":"2","list":[3]}]]}""")

    c.id should equal(1)
    c.list should equal(List(List(CaseClassWithParameter[Int](2, List(3)))))
  }

  it should "generate a JSON object" in {
    generate(CaseClassWithParameter[List[CaseClassWithParameter[Int]]](1, List(List(CaseClassWithParameter[Int](2, List(3)))))) should equal(
      """{"id":1,"list":[[{"id":2,"list":[3]}]]}""")
  }
}

