Jerkson
-------

*Because I think you should use JSON.*

Jerkson is a Scala wrapper for [Jackson](http://jackson.codehaus.org/) which
brings Scala's ease-of-use to Jackson's features.


Requirements
------------

* Scala 2.10.x
* Jackson 2.2.x

Recent Changes
--------------
* Converted all tests to scalatest
* Removed pom.xml as maven is no longer needed to test

Setting Up Your Project
-----------------------
Clone and make a source dependency

Testing
-------
sbt test 
(maven is not needed in this fork!)

Parsing JSON
------------

```scala
import com.codahale.jerkson.Json._

// Parse JSON arrays
parse[List[Int]]("[1,2,3]") //=> List(1,2,3)

// Parse JSON objects
parse[Map[String, Int]]("""{"one":1,"two":2}""") //=> Map("one"->1,"two"->2)

// Parse JSON objects as case classes
// (Parsing case classes isn't supported in the REPL.)
case class Person(id: Long, name: String)
parse[Person]("""{"id":1,"name":"Coda"}""") //=> Person(1,"Coda")

// Parse streaming arrays of things
for (person <- stream[Person](inputStream)) {
  println("New person: " + person)
}
```

For more examples, check out the [specs](https://github.com/codahale/jerkson/blob/master/src/test/scala/com/codahale/jerkson/tests/).


Generating JSON
---------------

```scala
// Generate JSON arrays
generate(List(1, 2, 3)) //=> [1,2,3]

// Generate JSON objects
generate(Map("one"->1, "two"->"dos")) //=> {"one":1,"two":"dos"}
```

For more examples, check out the [specs](https://github.com/codahale/jerkson/blob/master/src/test/scala/com/codahale/jerkson/tests/).


Handling `snake_case` Field Names
=================================

```scala
case class Person(firstName: String, lastName: String)

@JsonSnakeCase
case class Snake(firstName: String, lastName: String)

generate(Person("Coda", "Hale"))   //=> {"firstName": "Coda","lastName":"Hale"}
generate(Snake("Windey", "Mover")) //=> {"first_name": "Windey","last_name":"Mover"}
```


License
-------

Copyright (c) 2010-2011 Coda Hale

Published under The MIT License, see LICENSE
