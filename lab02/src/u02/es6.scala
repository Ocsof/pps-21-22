package u02

object es6 extends App:
  def fib(n: Int): Int = n match
      case 0 => 0
      case 1 => 1
      case _ => fib(n - 1) + fib(n - 2)

  println(fib(4))

// Not tail recursive, checked with @annotation.tailrec



