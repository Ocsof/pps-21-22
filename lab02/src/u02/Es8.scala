package u02

object Es8 extends App:

  enum Option[A]:
    case Some(a: A)
    case None() // here parens are needed because of genericity

  object Option:

    def isEmpty[A](opt: Option[A]): Boolean = opt match
      case None() => true
      case _ => false

    def orElse[A, B >: A](opt: Option[A], orElse: B): B = opt match
      case Some(a) => a
      case _ => orElse

    def flatMap[A, B](opt: Option[A])(f: A => Option[B]): Option[B] = opt match
      case Some(a) => f(a)
      case _ => None()

    def filter[A](opt: Option[A])(p: (A => Boolean)): Option[A] = opt match
      case Some(a) => if(p(a)) Some(a) else None()
      case None() => None()

    def map[A, B](opt: Option[A]) (p: (A => B)): Option[B] = opt match
      case Some(a) => Some(p(a))
      case None() => None()

    def combine[A, B](opt1: Option[A], opt2: Option[B]): Option[(A, B)] = (opt1, opt2) match
      case (Some(a), Some(b)) => Some((a,b))
      case _ => None()

    // Challenge: apply Generalisation + DRY + Strategy
    def apply[A](opt: Option[A])(p: A => A): Option[A] = opt match
      case None() => None()
      case Some(a) => Some(p(a))

    def double(opt: Option[Int]): Option[Int] = apply(opt)(_ * 2)

    def invert(opt: Option[Boolean]): Option[Boolean] = apply(opt)(!_)




  import Option.*
  val s1: Option[Int] = Some(1)
  val s2: Option[Int] = Some(2)
  val s3: Option[Int] = None()

  println(s1) // Some(1)
  println(orElse(s1, 0))
  println(orElse(s3, 0)) // 1,0
  println(flatMap(s1)(i => Some(i + 1))) // Some(2)
  println(flatMap(s1)(i => flatMap(s2)(j => Some(i + j)))) // Some(3)
  println(flatMap(s1)(i => flatMap(s3)(j => Some(i + j)))) // None
