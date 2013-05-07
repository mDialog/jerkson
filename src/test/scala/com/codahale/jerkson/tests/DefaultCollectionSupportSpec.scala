package com.codahale.jerkson.tests

import com.codahale.jerkson.Json._
import com.codahale.jerkson.ParsingException
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class DefaultCollectionSupportSpec extends FlatSpec with ShouldMatchers {
  //class "A Range" {
  "A Range" should "generate a JSON object" in {
    generate(Range.inclusive(1, 4, 3)) should equal("""{"start":1,"end":4,"step":3,"inclusive":true}""")
  }

  it should "generate a JSON object without the inclusive field if it's exclusive" in {
    generate(Range(1, 4, 3)) should equal("""{"start":1,"end":4,"step":3}""")
  }

  it should "generate a JSON object without the step field if it's 1" in {
    generate(Range(1, 4)) should equal("""{"start":1,"end":4}""")
  }

  it should "be parsable from a JSON object" in {
    parse[Range]("""{"start":1,"end":4,"step":3,"inclusive":true}""") should equal(Range.inclusive(1, 4, 3))
  }

  it should "be parsable from a JSON object without the inclusive field" in {
    parse[Range]("""{"start":1,"end":4,"step":3}""") should equal(Range(1, 4, 3))
  }

  it should "be parsable from a JSON object without the step field" in {
    parse[Range]("""{"start":1,"end":4}""") should equal(Range(1, 4))
  }

  it should "be not parsable from a JSON object without the required fields" in {
    intercept[ParsingException] {
      parse[Range]("""{"start":1}""")
    }
  }

  //"A Pair[Int]"
  ignore should "(pair) generate a two-element JSON array of ints" in {
    // TODO: 5/31/11 <coda> -- fix Pair serialization
    generate(Pair(1, 2)) should equal("[1,2]")
  }

  ignore should "(pair) be parsable from a two-element JSON array of ints" in {
    // TODO: 5/31/11 <coda> -- fix Pair deserialization
    parse[Pair[Int, Int]]("[1,2]") should equal(Pair(1, 2))
  }

  //"A Triple[Int]"
  ignore should "(triple) generate a three-element JSON array of ints" in {
    // TODO: 5/31/11 <coda> -- fix Triple serialization
    generate(Triple(1, 2, 3)) should equal("[1,2,3]")
  }

  ignore should "(triple) be parsable from a three-element JSON array of ints" in {
    // TODO: 5/31/11 <coda> -- fix Triple deserialization
    parse[Triple[Int, Int, Int]]("[1,2,3]") should equal(Triple(1, 2, 3))

  }

  //"A four-tuple"
  ignore should "(quad) generate a four-element JSON array" in {
    // TODO: 5/31/11 <coda> -- fix Tuple4 serialization
    generate((1, "2", 3, "4")) should equal("[1,\"2\",3,\"4\"]")
  }

  ignore should "(quad) be parsable from a three-element JSON array of ints" in {
    // TODO: 5/31/11 <coda> -- fix Tuple4 deserialization
    parse[(Int, String, Int, String)]("[1,\"2\",3,\"4\"]") should equal((1, "2", 3, "4"))
  }

  // TODO: 6/1/11 <coda> -- add support for all Tuple1->TupleBillionty types

  "A Seq[Int]" should "generates a JSON array of ints" in {
    generate(Seq(1, 2, 3)) should equal("[1,2,3]")
  }

  it should "be parsable from a JSON array of ints" in {
    parse[Seq[Int]]("[1,2,3]") should equal(Seq(1, 2, 3))
  }

  it should "be parsable from an empty JSON array" in {
    parse[Seq[Int]]("[]") should equal(Seq.empty[Int])
  }

  "A List[Int]" should "generates a JSON array of ints" in {
    generate(List(1, 2, 3)) should equal("[1,2,3]")
  }

  it should "is parsable from a JSON array of ints" in {
    parse[List[Int]]("[1,2,3]") should equal(List(1, 2, 3))
  }

  it should "is parsable from an empty JSON array" in {
    parse[List[Int]]("[]") should equal(List.empty[Int])
  }

  "An IndexedSeq[Int]" should "generate a JSON array of ints" in {
    generate(IndexedSeq(1, 2, 3)) should equal("[1,2,3]")
  }

  it should "be parsable from a JSON array of ints" in {
    parse[IndexedSeq[Int]]("[1,2,3]") should equal(IndexedSeq(1, 2, 3))
  }

  it should "be parsable from an empty JSON array" in {
    parse[IndexedSeq[Int]]("[]") should equal(IndexedSeq.empty[Int])
  }

  "A Vector[Int]" should "generate a JSON array of ints" in {
    generate(Vector(1, 2, 3)) should equal("[1,2,3]")
  }

  it should "be parsable from a JSON array of ints" in {
    parse[Vector[Int]]("[1,2,3]") should equal(Vector(1, 2, 3))
  }

  it should "be parsable from an empty JSON array" in {
    parse[Vector[Int]]("[]") should equal(Vector.empty[Int])
  }

  "A Set[Int]" should "generates a JSON array of ints" in {
    generate(Set(1, 2, 3)) should equal("[1,2,3]")
  }

  it should "is parsable from a JSON array of ints" in {
    parse[Set[Int]]("[1,2,3]") should equal(Set(1, 2, 3))
  }

  it should "is parsable from an empty JSON array" in {
    parse[Set[Int]]("[]") should equal(Set.empty[Int])
  }

  "A Map[String, Int]" should "generate a JSON object with int field values" in {
    generate(Map("one" -> 1, "two" -> 2)) should equal("""{"one":1,"two":2}""")
  }

  it should "be parsable from a JSON object with int field values" in {
    parse[Map[String, Int]]("""{"one":1,"two":2}""") should equal(Map("one" -> 1, "two" -> 2))
  }

  it should "be parsable from an empty JSON object" in {
    parse[Map[String, Int]]("{}") should equal(Map.empty[String, Int])
  }

  "A Map[String, Any]" should "generate a JSON object with mixed field values" in {
    generate(Map("one" -> 1, "two" -> "2")) should equal("""{"one":1,"two":"2"}""")
  }

  it should "be parsable from a JSON object with mixed field values" in {
    parse[Map[String, Any]]("""{"one":1,"two":"2"}""") should equal(Map[String, Any]("one" -> 1, "two" -> "2"))
  }

  it should "be parsable from an empty JSON object" in {
    parse[Map[String, Any]]("{}") should equal(Map.empty[String, Any])
  }

  "A Stream[Int]" should "generate a JSON array" in {
    generate(Stream(1, 2, 3)) should equal("[1,2,3]")
  }

  it should "be parsable from a JSON array of ints" in {
    parse[Stream[Int]]("[1,2,3]") should equal(Stream(1, 2, 3))
  }

  it should "be parsable from an empty JSON array" in {
    parse[Stream[Int]]("[]") should equal(Stream.empty[Int])
  }

  "An Iterator[Int]" should "generate a JSON array of ints" in {
    generate(Seq(1, 2, 3).iterator) should equal("[1,2,3]")
  }

  it should "be parsable from a JSON array of ints" in {
    parse[Iterator[Int]]("[1,2,3]").toList should equal(List(1, 2, 3))
  }

  it should "be parsable from an empty JSON array" in {
    parse[Iterator[Int]]("[]").toList should equal(List.empty[Int])
  }

  "A Traversable[Int]" should "generate a JSON array of ints" in {
    generate(Seq(1, 2, 3).toTraversable) should equal("[1,2,3]")
  }

  it should "be parsable from a JSON array of ints" in {
    parse[Traversable[Int]]("[1,2,3]").toList should equal(List(1, 2, 3))
  }

  it should "be parsable from an empty JSON array" in {
    parse[Traversable[Int]]("[]").toList should equal(List.empty[Int])
  }

  "A BufferedIterator[Int]" should "generate a JSON array of ints" in {
    generate(Seq(1, 2, 3).iterator.buffered) should equal("[1,2,3]")
  }

  it should "be parsable from a JSON array of ints" in {
    parse[BufferedIterator[Int]]("[1,2,3]").toList should equal(List(1, 2, 3))
  }

  it should "be parsable from an empty JSON array" in {
    parse[BufferedIterator[Int]]("[]").toList should equal(List.empty[Int])
  }

  "An Iterable[Int]" should "generate a JSON array of ints" in {
    generate(Seq(1, 2, 3).toIterable) should equal("[1,2,3]")
  }

  it should "be parsable from a JSON array of ints" in {
    parse[Iterable[Int]]("[1,2,3]").toList should equal(List(1, 2, 3))
  }

  it should "be parsable from an empty JSON array" in {
    parse[Iterable[Int]]("[]").toList should equal(List.empty[Int])
  }

}
