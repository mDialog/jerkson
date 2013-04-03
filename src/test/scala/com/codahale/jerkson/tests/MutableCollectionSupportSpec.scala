package com.codahale.jerkson.tests


import com.codahale.jerkson.Json._
import com.codahale.jerkson.AST._
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec
import org.junit.Test
import scala.collection.mutable._
import com.codahale.jerkson.ParsingException

class MutableCollectionSupportSpec extends FlatSpec with ShouldMatchers {

  "A mutable.ResizableArray[Int]" should "generate a JSON array of ints" in {
    generate(ResizableArray(1, 2, 3)) should equal("[1,2,3]")
  }

  it should "be parsable from a JSON array of ints" in {
    parse[ResizableArray[Int]]("[1,2,3]") should equal(ResizableArray(1, 2, 3))
  }

  it should "be parsable from an empty JSON array" in {
    parse[ResizableArray[Int]]("[]") should equal(ResizableArray.empty[Int])
  }

  "A mutable.ArraySeq[Int]" should "generate a JSON array of ints" in {
    generate(ArraySeq(1, 2, 3)) should equal("[1,2,3]")
  }


  //Note should matchers don't implicitly convert ArraySeq[Int] in scala 2.10 for unknown reason
  it should "be parsable from a JSON array of ints" in {
    //parse[ArraySeq[Int]]("[1,2,3]") should equal(ArraySeq(1, 2, 3))
    assert(parse[ArraySeq[Int]]("[1,2,3]").equals(ArraySeq(1,2,3)))
  }

  it should "be parsable from an empty JSON array" in {
    //parse[ArraySeq[Int]]("[]") should equal(ArraySeq.empty[Int])
    assert(parse[ArraySeq[Int]]("[]").equals(ArraySeq.empty[Int]))
  }

  private val xs = new MutableList[Int]
  xs ++= List(1, 2, 3)

  "A mutable.MutableList[Int]" should "generate a JSON array of ints" in {
    generate(xs) should equal("[1,2,3]")
  }

  it should "be parsable from a JSON array of ints" in {
    parse[MutableList[Int]]("[1,2,3]") should equal(xs)
  }

  it should "be parsable from an empty JSON array" in {
    //parse[ArraySeq[Int]]("[]") should equal(MutableList[Int]())
    assert(parse[ArraySeq[Int]]("[]").equals(MutableList.empty[Int]))
  }

  "A mutable.Queue[Int]" should "generate a JSON array of ints" in {
    generate(Queue(1,2,3)) should equal("[1,2,3]")
  }

  it should "be parsable from a JSON array of ints" in {
    parse[Queue[Int]]("[1,2,3]") should equal(Queue(1,2,3))
  }

  it should "be parsable from an empty JSON array" in {
    parse[Queue[Int]]("[]") should equal(Queue[Int]())
  }

  "A mutable.ListBuffer[Int]" should "generate a JSON array of ints" in {
    generate(ListBuffer(1,2,3)) should equal("[1,2,3]")
  }

  it should "be parsable from a JSON array of ints" in {
    parse[ListBuffer[Int]]("[1,2,3]") should equal(Queue(1,2,3))
  }

  it should "be parsable from an empty JSON array" in {
    parse[ListBuffer[Int]]("[]") should equal(ListBuffer.empty[Int])
  }

  "A mutable.ArrayBuffer[Int]" should "generate a JSON array of ints" in {
    generate(ArrayBuffer(1,2,3)) should equal("[1,2,3]")
  }

  it should "be parsable from a JSON array of ints" in {
    parse[ArrayBuffer[Int]]("[1,2,3]") should equal(ArrayBuffer(1,2,3))
  }

  it should "be parsable from an empty JSON array" in {
    parse[ArrayBuffer[Int]]("[]") should equal(ArrayBuffer.empty[Int])
  }

  "A mutable.BitSet[Int]" should "generate a JSON array of ints" in {
    generate(BitSet(1,2,3)) should equal("[1,2,3]")
  }

  it should "be parsable from a JSON array of ints" in {
    parse[BitSet]("[1,2,3]") should equal(BitSet(1,2,3))
  }

  it should "be parsable from an empty JSON array" in {
    parse[BitSet]("[]") should equal(BitSet.empty)
  }

  "A mutable.HashSet[Int]" should "generate a JSON array of ints" in {
    generate(HashSet(1)) should equal("[1]")
  }

  it should "be parsable from a JSON array of ints" in {
    parse[HashSet[Int]]("[1,2,3]") should equal(HashSet(1,2,3))
  }

  it should "be parsable from an empty JSON array" in {
    parse[HashSet[Int]]("[]") should equal(HashSet.empty[Int])
  }

  "A mutable.LinkedHashSet[Int]" should "generate a JSON array of ints" in {
    generate(LinkedHashSet(1)) should equal("[1]")
  }

  it should "be parsable from a JSON array of ints" in {
    parse[LinkedHashSet[Int]]("[1,2,3]") should equal(LinkedHashSet(1,2,3))
  }

  it should "be parsable from an empty JSON array" in {
    parse[LinkedHashSet[Int]]("[]") should equal(LinkedHashSet.empty[Int])
  }

  "A mutable.Map[String, Int]" should "generate a JSON object" in {
    generate(Map("one" -> 1)) should equal("""{"one":1}""")
  }

  it should "be parsable from a JSON object with int field values" in {
    parse[Map[String, Int]]("""{"one":1}""") should equal(Map("one" -> 1))
  }

  it should "be parsable from an empty JSON array" in {
    parse[Map[String, Int]]("{}") should equal(Map.empty[String, Int])
  }

  "A mutable.Map[String, Any]" should "not be parsable from an empty JSON object in a JSON array" in {
    intercept[ParsingException]{
      parse[Map[String, Any]]("[{}]")
    }
  }

  "A mutable.HashMap[String, Int]" should "generate a JSON object" in {
    generate(HashMap("one" -> 1)) should equal("""{"one":1}""")
  }

  it should "be parsable from a JSON object with int field values" in {
    parse[HashMap[String, Int]]("""{"one":1}""") should equal(HashMap("one" -> 1))
  }

  it should "be parsable from an empty JSON array" in {
    parse[HashMap[String, Int]]("{}") should equal(HashMap.empty[String, Int])
  }

  "A mutable.LinkedHashMap[String, Int]" should "generate a JSON object" in {
    generate(LinkedHashMap("one" -> 1)) should equal("""{"one":1}""")
  }

  it should "be parsable from a JSON object with int field values" in {
    parse[LinkedHashMap[String, Int]]("""{"one":1}""") should equal(LinkedHashMap("one" -> 1))
  }

  it should "be parsable from an empty JSON array" in {
    parse[LinkedHashMap[String, Int]]("{}") should equal(LinkedHashMap.empty[String, Int])
  }
}
