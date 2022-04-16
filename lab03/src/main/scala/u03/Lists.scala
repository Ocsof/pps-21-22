package u03

import u02.Optionals.Option
import u02.Optionals.Option.*
import u02.AlgebraicDataTypes.Person

object Lists extends App:

  // A generic linkedlist
  enum List[E]:
    case Cons(head: E, tail: List[E])
    case Nil()
  // a companion object (i.e., module) for List
  object List:

    def sum(l: List[Int]): Int = l match
      case Cons(h, t) => h + sum(t)
      case _ => 0

    def map[A, B](l: List[A])(mapper: A => B): List[B] = l match
      case Cons(h, t) => Cons(mapper(h), map(t)(mapper))
      case Nil() => Nil()

    def filter[A](l1: List[A])(pred: A => Boolean): List[A] = l1 match
      case Cons(h, t) if pred(h) => Cons(h, filter(t)(pred))
      case Cons(_, t) => filter(t)(pred)
      case Nil() => Nil()

    def drop[A](l1: List[A], n: Int): List[A] = l1 match
      case Cons(h, t) if n > 0 => drop(t, n - 1)
      case _ => l1


    def append[A](left: List[A], right: List[A]): List[A] = left match
      case Cons(h, t) => Cons(h, append(t, right))
      case _ => right


    def flatMap[A, B](l: List[A])(f: A => List[B]): List[B] = l match
      case Cons(h, t) => append(f(h), flatMap(t)(f))
      case Nil() => Nil()


    //versione di Map usando FlatMap
    def map2[A, B](l: List[A])(mapper: A => B): List[B] = flatMap(l)(a => Cons(mapper(a), Nil()))

    //versione di Filter usando FlatMap
    def filter2[A](l1: List[A])(pred: A => Boolean): List[A] = flatMap(l1)(a => a match
      case a if pred(a) => Cons(a, Nil())
      case _ => Nil())

    def max(l: List[Int]): Option[Int] = l match
      case Cons(h, Cons(h2, t)) if (h > h2) => max(Cons(h, t))
      case Cons(h, Cons(h2, t)) => max(Cons(h2, t))
      case Cons(h, Nil()) => Some(h)
      case _ => None()

    def getCourses(l: List[Person]): List[String] = flatMap(l)(p => p match
      case Person.Teacher(_, courses) => Cons(courses, Nil())
      case _ => Nil()  //se è studente sbat e caz
    )

    def foldLeft[A, B](l: List[A])(n: B)(f: (B, A) => B): B = l match
      case Cons(h, t) => foldLeft(t)(f(n, h))(f)
      case Nil() => n
      
    def foldRight[A, B](l: List[A])(n: B)(f: (B, A) => B): B = l match 
      case Cons(h, t) => f(foldRight(t)(n)(f), h)
      case Nil() => n
    


  val l = List.Cons(10, List.Cons(20, List.Cons(30, List.Nil())))
  println(List.sum(l)) // 60

  import List.*

  println(sum(map(filter(l)(_ >= 20))(_ + 1))) // 21+31 = 52
