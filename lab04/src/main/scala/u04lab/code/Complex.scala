package u04lab.code

trait Complex:
  def re: Double
  def im: Double
  def +(c: Complex): Complex // should implement the sum of two complex numbers..
  def *(c: Complex): Complex // should implement the product of two complex numbers

object Complex:
  def apply(re: Double, im: Double): Complex =
    ComplexImpl(re, im)
    //se metto case class posso togliere ovverride e val perchè lo sono in automatico
  private class ComplexImpl(override val re: Double,  //mettendo case class funzionano equals e toString,
                    override val im: Double) extends Complex:
    //assert(re != null && im != null) //come accertarsi che i due non siano null?
    override def +(c: Complex): Complex = Complex(re + c.re, im + c.im)
    override def *(c: Complex): Complex = Complex(re * c.re, im * c.im)



@main def checkComplex(): Unit =
  println(Complex(2, 3).toString)
  println(Complex(2, 3) == Complex(2, 3))
  println(Complex(2, 3) eq Complex(2, 3))
  println(Complex(2, 3) equals Complex(2, 3))
  val a = Array(Complex(10, 20), Complex(1, 1), Complex(7, 0))
  val c = a(0) + a(1) + a(2)
  println((c, c.re, c.im)) // (ComplexImpl(18.0,21.0),18.0,21.0)
  val c2 = a(0) * a(1)
  println((c2, c2.re, c2.im)) // (ComplexImpl(-10.0,30.0),-10.0,30.0)

/** Hints:
  *   - implement Complex with a ComplexImpl class, similar to PersonImpl in slides
  *   - check that equality and toString do not work
  *   - use a case class ComplexImpl instead, creating objects without the 'new' keyword
  *   - check equality and toString now
  */
