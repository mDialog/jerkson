package com.codahale.jerkson.tests

import scala.collection._
import com.codahale.jerkson.Json._
import org.junit.{ Ignore, Test }
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class CollectionSupportSpec extends FlatSpec with ShouldMatchers {
  "A collection.BitSet" should "generate a JSON array of ints" in {
    generate(BitSet(1)) should equal("[1]")
  }

  it should "be parsable from a JSON array of ints" in {
    parse[BitSet]("[1,2,3]") should equal(BitSet(1, 2, 3))
  }

  "A collection.Iterator[Int]" should "generate a JSON array of ints" in {
    generate(Seq(1, 2, 3).iterator) should equal("[1,2,3]")
  }

  it should "be parsable from a JSON array of ints" in {
    parse[Iterator[Int]]("[1,2,3]").toList should equal(List(1, 2, 3))
  }

  it should "be parsable from an empty JSON array" in {
    parse[Iterator[Int]]("[]").toList should equal(List.empty[Int])
  }

  "A collection.Traversable[Int]" should "generate a JSON array of ints" in {
    generate(Seq(1, 2, 3).toTraversable) should equal("[1,2,3]")
  }

  it should "be parsable from a JSON array of ints" in {
    parse[Traversable[Int]]("[1,2,3]").toList should equal(List(1, 2, 3))
  }

  it should "be parsable from an empty JSON array" in {
    parse[Traversable[Int]]("[]").toList should equal(List.empty[Int])
  }

  "A collection.BufferedIterator[Int]" should "generate a JSON array of ints" in {
    generate(Seq(1, 2, 3).iterator.buffered) should equal("[1,2,3]")
  }

  it should "be parsable from a JSON array of ints" in {
    parse[BufferedIterator[Int]]("[1,2,3]").toList should equal(List(1, 2, 3))
  }

  it should "be parsable from an empty JSON array" in {
    parse[BufferedIterator[Int]]("[]").toList should equal(List.empty[Int])
  }

  "A collection.Iterable[Int]" should "generate a JSON array of ints" in {
    generate(Seq(1, 2, 3).toIterable) should equal("[1,2,3]")
  }

  it should "be parsable from a JSON array of ints" in {
    parse[Iterable[Int]]("[1,2,3]").toList should equal(List(1, 2, 3))
  }

  it should "be parsable from an empty JSON array" in {
    parse[Iterable[Int]]("[]").toList should equal(List.empty[Int])
  }

  "A collection.Set[Int]" should "generate a JSON array of ints" in {
    generate(Set(1, 2, 3)) should equal("[1,2,3]")
  }

  it should "be parsable from a JSON array of ints" in {
    parse[Set[Int]]("[1,2,3]") should equal(Set(1, 2, 3))
  }

  it should "be parsable from an empty JSON array" in {
    parse[Set[Int]]("[]") should equal(Set.empty[Int])
  }

  "A collection.Map[String, Int]" should "generate a JSON object with int field values" in {
    generate(Map("one" -> 1, "two" -> 2)) should equal("""{"one":1,"two":2}""")
  }

  it should "be parsable from a JSON object with int field values" in {
    parse[Map[String, Int]]("""{"one":1,"two":2}""") should equal(Map("one" -> 1, "two" -> 2))
  }

  it should "be parsable from an empty JSON object" in {
    parse[Map[String, Int]]("{}") should equal(Map.empty[String, Int])
  }

  "A collection.IndexedSeq[Int]" should "generate a JSON array of ints" in {
    generate(IndexedSeq(1, 2, 3)) should equal("[1,2,3]")
  }

  it should "be parsable from a JSON array of ints" in {
    parse[IndexedSeq[Int]]("[1,2,3]") should equal(IndexedSeq(1, 2, 3))
  }

  it should "be parsable from an empty JSON array" in {
    parse[IndexedSeq[Int]]("[]") should equal(IndexedSeq.empty)
  }

  "A collection.Seq[Int]" should "generate a JSON array of ints" in {
    generate(Seq(1, 2, 3)) should equal("[1,2,3]")
  }

  it should "be parsable from a JSON array of ints" in {
    parse[Seq[Int]]("[1,2,3]") should equal(Seq(1, 2, 3))
  }

  it should "be parsable from an empty JSON array" in {
    parse[Seq[Int]]("[]") should equal(Seq.empty[Int])
  }

  "A collection.SortedMap[String, Int]" should "generate a JSON object with int field values" in {
    generate(SortedMap("one" -> 1, "two" -> 2)) should equal("""{"one":1,"two":2}""")
  }

  // TODO: 6/1/11 <coda> -- figure out how to deserialize SortedMap instances

  /**
   * I think all this would take is a mapping from Class[_] to Ordering, which
   * would need to have hard-coded the various primitive types, and then add
   * support for Ordered and Comparable classes. Once we have the Ordering,
   * we can pass it in manually to a builder.
   */

  ignore should "be parsable from a JSON object with int field values" in {
    parse[SortedMap[String, Int]]("""{"one":1,"two":2}""") should equal(SortedMap("one" -> 1, "two" -> 2))
  }

  ignore should "be parsable from an empty JSON object" in {
    parse[SortedMap[String, Int]]("{}") should equal(SortedMap.empty[String, Int])
  }

  "A collection.SortedSet[Int]" should "generate a JSON array of ints" in {
    generate(SortedSet(1, 2, 3)) should equal("[1,2,3]")
  }

  // TODO: 6/1/11 <coda> -- figure out how to deserialize SortedMap instances

  /**
   * I think all this would take is a mapping from Class[_] to Ordering, which
   * would need to have hard-coded the various primitive types, and then add
   * support for Ordered and Comparable classes. Once we have the Ordering,
   * we can pass it in manually to a builder.
   */

  ignore should "be parsable from a JSON array of ints" in {
    parse[SortedSet[Int]]("[1,2,3]") should equal(SortedSet(1, 2, 3))

  }

  ignore should "be parsable from an empty JSON array" in {
    parse[SortedSet[Int]]("[]") should equal(SortedSet.empty[Int])
  }

}
