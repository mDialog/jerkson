package com.codahale.jerkson.tests

import com.codahale.jerkson.Json._
import scala.collection.immutable._
import com.codahale.jerkson.ParsingException
import org.junit.{Ignore, Test}
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec

class ImmutableCollectionSupportSpec extends FlatSpec with ShouldMatchers {
  "An immutable.Seq[Int]" should "generate a JSON array of ints" in {
    generate(Seq(1, 2, 3)) should equal("[1,2,3]")
  }

  it should "be parsable from a JSON array of ints" in {
    parse[Seq[Int]]("[1,2,3]") should equal(Seq(1, 2, 3))
  }

  it should "be parsable from an empty JSON array" in {
    parse[Seq[Int]]("[]") should equal(Seq.empty[Int])
  }

  "An immutable.List[Int]" should "generate a JSON array of ints" in {
    generate(List(1, 2, 3)) should equal("[1,2,3]")
  }

  it should "be parsable from a JSON array of ints" in {
    parse[List[Int]]("[1,2,3]") should equal(List(1, 2, 3))
  }

  it should "be parsable from an empty JSON array" in {
    parse[List[Int]]("[]") should equal(List.empty[Int])
  }

  "An immutable.IndexedSeq[Int]" should "generate a JSON array of ints" in {
    generate(IndexedSeq(1, 2, 3)) should equal("[1,2,3]")
  }

  it should "be parsable from a JSON array of ints" in {
    parse[IndexedSeq[Int]]("[1,2,3]") should equal(IndexedSeq(1, 2, 3))
  }

  it should "be parsable from an empty JSON array" in {
    parse[IndexedSeq[Int]]("[]") should equal(IndexedSeq.empty[Int])
  }


  "An immutable.TreeSet[Int]" should "generate a JSON array" in {
    generate(TreeSet(1)) should equal("[1]")
  }

  // TODO: 6/1/11 <coda> -- figure out how to deserialize TreeSet instances

  /**
   * I think all this would take is a mapping from Class[_] to Ordering, which
   * would need to have hard-coded the various primitive types, and then add
   * support for Ordered and Comparable classes. Once we have the Ordering,
   * we can pass it in manually to a builder.
   */

  ignore should "be parsable from a JSON array of ints" in {
    parse[TreeSet[Int]]("[1,2,3]") should equal(TreeSet(1, 2, 3))
  }

  ignore should "be parsable from an empty JSON array" in {
    parse[TreeSet[Int]]("[]") should equal(TreeSet.empty[Int])
  }


  "An immutable.HashSet[Int]" should "generate a JSON array" in {
    generate(HashSet(1)) should equal("[1]")
  }

  it should "be parsable from a JSON array of ints" in {
    parse[HashSet[Int]]("[1,2,3]") should equal(HashSet(1, 2, 3))
  }

  it should "be parsable from an empty JSON array" in {
    parse[HashSet[Int]]("[]") should equal(HashSet.empty[Int])
  }


  "An immutable.BitSet" should "generate a JSON array" in {
    generate(BitSet(1)) should equal("[1]")
  }

  it should "be parsable from a JSON array of ints" in {
    parse[BitSet]("[1,2,3]") should equal(BitSet(1, 2, 3))
  }

  it should "be parsable from an empty JSON array" in {
    parse[BitSet]("[]") should equal(BitSet.empty)
  }


  "An immutable.TreeMap[String, Int]" should "generate a JSON object" in {
    generate(TreeMap("one" -> 1)) should equal( """{"one":1}""")
  }

  // TODO: 6/1/11 <coda> -- figure out how to deserialize TreeMap instances

  /**
   * I think all this would take is a mapping from Class[_] to Ordering, which
   * would need to have hard-coded the various primitive types, and then add
   * support for Ordered and Comparable classes. Once we have the Ordering,
   * we can pass it in manually to a builder.
   */

  ignore should "be parsable from a JSON object with int field values" in {
    parse[TreeMap[String, Int]]( """{"one":1}""") should equal(TreeMap("one" -> 1))
  }

  ignore should "be parsable from an empty JSON object" in {
    parse[TreeMap[String, Int]]("{}") should equal(TreeMap.empty[String, Int])
  }


  "An immutable.HashMap[String, Int]" should "generate a JSON object" in {
    generate(HashMap("one" -> 1)) should equal( """{"one":1}""")
  }

  it should "be parsable from a JSON object with int field values" in {
    parse[HashMap[String, Int]]( """{"one":1}""") should equal(HashMap("one" -> 1))
  }

  it should "be parsable from an empty JSON object" in {
    parse[HashMap[String, Int]]("{}") should equal(HashMap.empty[String, Int])
  }


  "An immutable.HashMap[String, Any]" should "generate a JSON object" in {
    generate(HashMap[String, Any]("one" -> 1)) should equal( """{"one":1}""")
  }

  it should "be parsable from a JSON object with int field values" in {
    parse[HashMap[String, Any]]( """{"one":1}""") should equal(HashMap("one" -> 1))
  }

  it should "be parsable from an empty JSON object" in {
    parse[HashMap[String, Any]]("{}") should equal(HashMap.empty[String, Any])
  }

  it should "is not parsable from an empty JSON object in a JSON array" in {
    intercept[ParsingException] {
      parse[HashMap[String, Any]]("[{}]")
    }
  }


  "An immutable.Map[Int, String]" should "generate a JSON object" in {
    generate(Map(1 -> "one")) should equal( """{"1":"one"}""")
  }

  it should "be parsable from a JSON object with decimal field names and string field values" in {
    parse[Map[Int, String]]( """{"1":"one"}""") should equal(Map(1 -> "one"))
  }

  it should "is not parsable from a JSON object with non-decimal field names" in {
    intercept[ParsingException] {
      parse[Map[Int, String]]( """{"one":"one"}""")
    }
  }

  it should "be parsable from an empty JSON object" in {
    parse[Map[Int, String]]("{}") should equal(Map.empty[Int, String])
  }


  "An immutable.Map[Int, Any]" should "is not parsable from an empty JSON object in a JSON array" in {
    intercept[ParsingException] {
      parse[Map[Int, Any]]("[{}]")
    }
  }


  "An immutable.IntMap[Any]" should "is not parsable from an empty JSON object in a JSON array" in {
    intercept[ParsingException] {
      parse[IntMap[Any]]("[{}]")
    }
  }

  "An immutable.LongMap[Any]" should "is not parsable from an empty JSON object in a JSON array" in {
    intercept[ParsingException] {
      parse[LongMap[Any]]("[{}]")
    }
  }


  "An immutable.Map[Long, Any]" should "is not parsable from an empty JSON object in a JSON array" in {
    intercept[ParsingException] {
      parse[Map[Long, Any]]("[{}]")
    }
  }


  "An immutable.Map[Long, String]" should "generate a JSON object" in {
    generate(Map(1L -> "one")) should equal( """{"1":"one"}""")
  }

  it should "be parsable from a JSON object with decimal field names and string field values" in {
    parse[Map[Long, String]]( """{"1":"one"}""") should equal(Map(1L -> "one"))
  }

  it should "is not parsable from a JSON object with non-decimal field names" in {
    intercept[ParsingException] {
      parse[Map[Long, String]]( """{"one":"one"}""")
    }
  }

  it should "be parsable from an empty JSON object" in {
    parse[Map[Long, String]]("{}") should equal(Map.empty[Long, String])
  }


  "An immutable.IntMap[String]" should "generate a JSON object" in {
    generate(IntMap(1 -> "one")) should equal( """{"1":"one"}""")
  }

  it should "be parsable from a JSON object with decimal field names and string field values" in {
    parse[IntMap[String]]( """{"1":"one"}""") should equal(IntMap(1 -> "one"))
  }

  it should "is not parsable from a JSON object with non-decimal field names" in {
    intercept[ParsingException] {
      parse[IntMap[String]]( """{"one":"one"}""")
    }
  }

  it should "be parsable from an empty JSON object" in {
    parse[IntMap[String]]("{}") should equal(IntMap.empty[String])
  }


  "An immutable.LongMap[String]" should "generate a JSON object" in {
    generate(LongMap(1L -> "one")) should equal( """{"1":"one"}""")
  }

  it should "be parsable from a JSON object with int field names and string field values" in {
    parse[LongMap[String]]( """{"1":"one"}""") should equal(LongMap(1L -> "one"))
  }

  it should "is not parsable from a JSON object with non-decimal field names" in {
    intercept[ParsingException] {
      parse[LongMap[String]]( """{"one":"one"}""")
    }
  }

  it should "be parsable from an empty JSON object" in {
    parse[LongMap[String]]("{}") should equal(LongMap.empty)
  }


  "An immutable.Queue[Int]" should "generate a JSON array" in {
    generate(Queue(1, 2, 3)) should equal("[1,2,3]")
  }

  it should "be parsable from a JSON array of ints" in {
    parse[Queue[Int]]("[1,2,3]") should equal(Queue(1, 2, 3))
  }

  it should "be parsable from an empty JSON array" in {
    parse[Queue[Int]]("[]") should equal(Queue.empty)
  }

}
