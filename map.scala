package iascala

/*

http://www.youtube.com/watch?v=hF2ZV5nIsJE
  - 19:44 break down tricks into granular components, build them back up in new ways
  - 20:16 hide the complexity, focus on 1 aspect of trick
  - 21:15 finished
  - Skating http://www.youtube.com/watch?v=hF2ZV5nIsJE#t=14m40s
  - Talking http://www.youtube.com/watch?v=hF2ZV5nIsJE#t=19m44s

scala> val m = Map("a" -> 1, "b" -> 2)
m: scala.collection.immutable.Map[String,Int] = Map(a -> 1, b -> 2)

Immediate observations:
  1) We used Map, but it's really scala.collection.immutable.Map and we didn't import anything
  2) We didn't specify type parameters, but it inferred [String, Int]
  3) We called Map() like a function, but it's name is capitalized; there's a Map type but we didn't use 'new'
  4) We can call Map() with any number of key/value pairs
  5) There's an "arrow" operator ->




#Type alias

scala> type StringIntMap = scala.collection.immutable.Map[String, Int]
defined type alias StringIntMap

#Predef
scala> val m = Map("a" -> 1, "b" -> 2)
m: scala.collection.immutable.Map[String,Int] = Map(a -> 1, b -> 2)

Map is actually scala.collection.immutable.Map
type alias is defined in Predef and automatically imported

scala> val m = scala.collection.immutable.Map("a" -> 1, "b" -> 2)
m: scala.collection.immutable.Map[String,Int] = Map(a -> 1, b -> 2)




#Type Inference
Omit types, and Scala can (usually) figure them out

scala> val m: Map[String, Int] = Map[String, Int]("a" -> 1)
m: Map[String,Int] = Map(a -> 1)

scala> val m = Map[String, Int]("a" -> 1)
m: scala.collection.immutable.Map[String,Int] = Map(a -> 1)

scala> val m: Map[String, Int] = Map("a" -> 1)
m: Map[String,Int] = Map(a -> 1)

scala> val m = Map("a" -> 1)
m: scala.collection.immutable.Map[String,Int] = Map(a -> 1)




#Objects
scala.collection.immutable.Map is an object; a value that is an instance of ImmutableMapFactory[Map]

scala> scala.collection.immutable.Map.isInstanceOf[scala.collection.generic.ImmutableMapFactory[Map]]
res7: Boolean = true

#Apply
Calling a function f(...) on a value is converted to calling method apply on that value: f.apply(...)

scala> val m = scala.collection.immutable.Map.apply("a" -> 1, "b" -> 2)
m: scala.collection.immutable.Map[String,Int] = Map(a -> 1, b -> 2)




#Repeated parameters
scala> Map()
res0: scala.collection.immutable.Map[Nothing,Nothing] = Map()

scala> Map("a" -> 1)
res1: scala.collection.immutable.Map[String,Int] = Map(a -> 1)

scala> Map("a" -> 1, "b" -> 2)
res2: scala.collection.immutable.Map[String,Int] = Map(a -> 1, b -> 2)




#Tuple Types
(T1, ..., Tn) is an alias for Tuplen[T1, ..., Tn]
(String, Int) is an alias for Tuple2[String, Int]

scala> Map(("a", 1), ("b", 2))
res19: scala.collection.immutable.Map[String,Int] = Map(a -> 1, b -> 2)

scala> Map(new Tuple2("a", 1), new Tuple2("b", 2))
res14: scala.collection.immutable.Map[String,Int] = Map(a -> 1, b -> 2)

scala> new Tuple2("a", 1)
res11: (String, Int) = (a,1)

scala> ("a", 1)
res12: (String, Int) = (a,1)

scala> new Pair("a", 1)
res13: (String, Int) = (a,1)

scala> "a" -> 1
res10: (String, Int) = (a,1)

#Infix operations
e1 op e2 is same as e1.op(e2)

scala> "a".->(1)
res17: (String, Int) = (a,1)

#Implicit conversions
String does not have a method named ->
Predef defines:
  - ArrowAssoc class that wraps an A, and a -> method with type B => Tuple2[A,B]
  - implicit conversion A => ArrowAssoc[A] for any type A
Compiler finds any2ArrowAssoc implicit from Predef

scala> new ArrowAssoc("a").->(1)
res15: (String, Int) = (a,1)

scala> any2ArrowAssoc("a").->(1)
res16: (String, Int) = (a,1)

#@inline






*/
