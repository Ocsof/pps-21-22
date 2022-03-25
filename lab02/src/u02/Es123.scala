package u02

object Es123 extends App:
  println("Hello Scala")

  val parityFun: Int => String = _ match
    case x if x % 2 == 0 => "even"
    case _ => "odd"

  def parityMeth(x: Int): String = x match
    case x if x % 2 == 0 => "even"
    case _ => "odd"

  println(parityFun(0))
  println(parityMeth(3))

  val negFun: (String => Boolean) => (String => Boolean) = f => ( (s: String) => !f(s) )

  val empty: String => Boolean = _ == ""
  val notEmpty = negFun(empty)
  println(notEmpty("foo"))
  println(notEmpty(""))
  println(notEmpty("foo") && !notEmpty(""))

  def negMeth(f: (String => Boolean)): (String  => Boolean) = s => !f(s)
  val notEmptyMeth = negMeth(empty)
  println(notEmptyMeth("foo"))
  println(notEmptyMeth(""))
  println(notEmptyMeth("foo") && !notEmptyMeth(""))

  def negGeneric[A](f: A => Boolean): (A => Boolean) = a => !f(a)
  val isEven: Int => Boolean = x => x % 2 == 0
  println(isEven(6))
  val notIsEven = negGeneric(isEven)
  println(notIsEven(6))


