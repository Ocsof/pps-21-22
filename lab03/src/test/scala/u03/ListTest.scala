package u03

import org.junit.*
import org.junit.Assert.*
import u02.Optionals.Option.*
import u02.AlgebraicDataTypes.Person.*
import Lists.{l, *}

class ListTest:
  import List.*

  val l: List[Int] = Cons(10, Cons(20, Cons(30, Nil())))
  val tail: List[Int] = Cons(40, Nil())

  @Test def testSum() =
    assertEquals(0, sum(Nil()))
    assertEquals(60, sum(l))

  @Test def testMap() =
    assertEquals(Cons(11, Cons(21, Cons(31, Nil()))), map(l)(_ + 1))
    assertEquals(Cons("10", Cons("20", Cons("30", Nil()))), map(l)(_ + ""))

  @Test def testFilter() =
    assertEquals(Cons(20, Cons(30, Nil())), filter(l)(_ >= 20))
    assertEquals(Cons(10, Cons(30, Nil())), filter(l)(_ != 20))

  @Test def testDrop() =
    assertEquals(Cons(20, Cons(30, Nil())), drop(l, 1))
    assertEquals(Cons(30, Nil()), drop(l, 2))
    assertEquals(Nil(), drop(l, 5))

  @Test def testAppend() =
  assertEquals(append(l, tail), Cons(10, Cons(20, Cons(30, Cons(40, Nil())))))

  @Test def testFlatMap() =
    assertEquals(Cons(11, Cons(21, Cons(31, Nil()))), flatMap(l)(v => Cons(v + 1, Nil())))
    assertEquals(Cons(11, Cons(12, Cons(21, Cons(22, Cons(31, Cons(32, Nil ())))))), flatMap(l)(v => Cons(v + 1, Cons(v + 2, Nil ()))))

  @Test def testFilter2() =
    assertEquals(Cons(20, Cons(30, Nil())), filter(l)(_ >= 20))
    assertEquals(Cons(10, Cons(30, Nil())), filter(l)(_ != 20))

  @Test def testMap2() =
    assertEquals(Cons(11, Cons(21, Cons(31, Nil()))), map(l)(_ + 1))
  assertEquals(Cons("10", Cons("20", Cons("30", Nil()))), map(l)(_ + ""))

  @Test def testMax() =
    assertEquals(Some(25), max(Cons(10, Cons(25, Cons(20, Nil())))))
    assertEquals(None(), max(Nil()))

  @Test def testGetCourses() =
    val persons = Cons(Student("marco", 1), Cons(Teacher("luigi", "algebra"), Cons(Teacher("ugo", "arte"), Nil())))
    assertEquals(Cons("algebra", Cons("arte", Nil())), getCourses(persons))

  @Test def testFoldLeft() =
    assertEquals(60, foldLeft(l)(0)(_+_))
    assertEquals(-50, foldLeft(l)(10)(_-_))
    assertEquals("102030", foldLeft(l)("")(_+_))
  
  @Test def testFoldRight() =
    assertEquals(60, foldRight(l)(0)(_+_))
    assertEquals("302010", foldRight(l)("")(_+_))
  