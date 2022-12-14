package u05lab.ex1

import u05lab.ex1.List

// Ex 1. implement the missing methods both with recursion or with using fold, map, flatMap, and filters
// List as a pure interface
enum List[A]:
  case ::(h: A, t: List[A])
  case Nil()
  def ::(h: A): List[A] = List.::(h, this)

  def head: Option[A] = this match
    case h :: t => Some(h)
    case _ => None

  def tail: Option[List[A]] = this match
    case h :: t => Some(t)
    case _ => None

  /*
  def append(list: List[A]): List[A] = this match
    case h :: t => h :: t.append(list)
    case _ => list
  */

  def foreach(consumer: A => Unit): Unit = this match
    case h :: t => consumer(h); t.foreach(consumer)
    case _ =>

  def get(pos: Int): Option[A] = this match
    case h :: t if pos == 0 => Some(h)
    case h :: t if pos > 0 => t.get(pos - 1)
    case _ => None

  def filter(predicate: A => Boolean): List[A] = this match
    case h :: t if predicate(h) => h :: t.filter(predicate)
    case _ :: t => t.filter(predicate)
    case _ => Nil()

  def map[B](fun: A => B): List[B] = this match
    case h :: t => fun(h) :: t.map(fun)
    case _ => Nil()

  def flatMap[B](f: A => List[B]): List[B] =
    foldRight[List[B]](Nil())(f(_) append _)

  def foldLeft[B](z: B)(op: (B, A) => B): B = this match
    case h :: t => t.foldLeft(op(z, h))(op)
    case Nil() => z

  def foldRight[B](z: B)(f: (A, B) => B): B = this match
    case h :: t => f(h, t.foldRight(z)(f))
    case _ => z

  def append(list: List[A]): List[A] =
    this.foldRight(list)((elem, l) => elem :: l)

  def length: Int = foldLeft(0)((l, _) => l + 1)

  def isEmpty: Boolean = this match
    case Nil() => true
    case _ => false

  def reverse(): List[A] = foldLeft[List[A]](Nil())((l, e) => e :: l)

  /** EXERCISES */

  private def zipRightAccumulator(acc: Int, list: List[A]): List[(A, Int)] = list match
    case h :: t => (h, acc) :: zipRightAccumulator(acc + 1, t)
    case _ => Nil()

  def zipRight: List[(A, Int)] = zipRightAccumulator(0, this)

  //  this.foldRight((emptyValue, z))((elem, acc) => (next(elem, acc._2, acc._1), accumulator(elem, acc._2)))._1
  private def foldWithMemory[B, C](z: B)(accumulator: (A, B) => B)(next: (A, B, C) => C)(emptyValue: C): C = this match
    case h :: t => next(h, z, t.foldWithMemory(accumulator(h, z))(accumulator)(next)(emptyValue))
    case _ => emptyValue

  private def mapWithMemory[B, C](z: B)(accumulator: (A, B) => B)(next: (A, B) => C): List[C] = this.foldWithMemory(z)(accumulator)((a, b, c: List[C]) => next(a,b) :: c)(Nil())

  def zipRight2: List[(A, Int)] = mapWithMemory(0)((_, acc) => acc+1)((_,_))

  def partition(pred: A => Boolean): (List[A], List[A]) = (this.filter(pred), this.filter(x => !pred(x)));
  def partition2(pred: A => Boolean): (List[A], List[A]) =
    this.foldRight((Nil(), Nil()))((elem, acc) => if pred(elem) then (elem :: acc._1, acc._2) else (acc._1, elem :: acc._2))

  def takeWhile(pred: A => Boolean): List[A] = this match
    case h :: t if pred(h) => h :: t.takeWhile(pred)
    case _ => Nil();

  def dropWhile(pred: A => Boolean): List[A] = this match
    case h :: t if pred(h) => t.dropWhile(pred)
    case _ => this

  def span(pred: A => Boolean): (List[A], List[A]) = (this.takeWhile(pred), this.dropWhile(pred))


  /** @throws UnsupportedOperationException if the list is empty */
  def reduce(op: (A, A) => A): A = this match
    case h :: Nil() => h
    case h :: t => op(h, t.reduce(op))
    case _ => throw UnsupportedOperationException()

  def drop(n: Int): List[A] = this match
    case h :: t if n > 0 => t.drop(n - 1)
    case _ => this

  def takeRight(n: Int): List[A] = this.drop(this.length - n)

  def collect[B](f: PartialFunction[A, B]): List[B] = this.filter(f.isDefinedAt).map(f)

// Factories
object List:

  def apply[A](elems: A*): List[A] =
    var list: List[A] = Nil()
    for e <- elems.reverse do list = e :: list
    list

  def of[A](elem: A, n: Int): List[A] =
    if n == 0 then Nil() else elem :: of(elem, n - 1)

@main def checkBehaviour(): Unit =
  val reference = List(1, 2, 3, 4)
  println(reference.zipRight) // List((1, 0), (2, 1), (3, 2), (4, 3))
  println(reference.partition(_ % 2 == 0)) // (List(2, 4), List(1, 3))
  println(reference.span(_ % 2 != 0)) // (List(1), List(2, 3, 4))
  println(reference.span(_ < 3)) // (List(1, 2), List(3, 4))
  println(reference.reduce(_ + _)) // 10
  try Nil.reduce[Int](_ + _)
  catch case ex: Exception => println(ex) // prints exception
  println(List(10).reduce(_ + _)) // 10
  println(reference.takeRight(3)) // List(2, 3, 4)