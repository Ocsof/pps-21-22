package u02

object Es5 extends App:
  def composeMeth(f: (Int => Int), g: (Int => Int) ): (Int => Int) =
    i => f(g(i))

  println(composeMeth(_ - 1, _ * 2)(5))
  //println(composeMeth(x => x -1, x => x * 2)(5))

  val composeFun: ((Int => Int), (Int => Int)) => (Int => Int) =
    (f, g) => (i => f(g(i)))

  println(composeFun(_ - 1, _ * 2)(5))

  def composeGeneric[A, B, C](f: (B => C), g: (A => B) ): (A => C) =
    a => f(g(a))

  println(composeGeneric((n: Int) => n + "toString", (n: Int) => n * 2)(5))
