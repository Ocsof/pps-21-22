package u02

object Es4 extends App:
  def checkMeth(x: Int, y: Int, z: Int): Boolean = (x <= y && y <= z)

  def checkMethWithCurring(x: Int)(y: Int)(z: Int) = (x <= y && y <= z)

  val checkFun: (Int, Int, Int) => Boolean = (x, y, z) => (x <= y) && (y <= z)

  val checkFunCurried: Int => (Int => (Int => Boolean)) = x => y => z => (x <= y) && (y <= z)

  println(checkMeth(2, 5, 7))
  println(checkMeth(2, 7, 5))

  println(checkMethWithCurring(5)(7)(10))
  println(checkMethWithCurring(5)(17)(10))

  println(checkFun(2, 5, 7))
  println(checkFun(2, 7, 5))

  println(checkFunCurried(5)(7)(10))
  print(checkFunCurried(5)(17)(10))