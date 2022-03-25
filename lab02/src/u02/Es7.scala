package u02

object es7 extends App:

  enum Shape:
    case Rectangle(width: Double, height: Double)
    case Circle(radius: Double)
    case Square(side: Double)

  object Shape:
    def area(shape: Shape): Double = shape match
      case Rectangle(w, h) => w * h
      case Circle(r) => java.lang.Math.PI * r * r
      case Square(s) => s*4

    def perimeter(shape: Shape): Double = shape match {
      case Rectangle(w, h) => w * 2 + h * 2
      case Circle(r) => 2 * java.lang.Math.PI * r
      case Square(s) => s*s
    }

  import Shape.*

    println(perimeter(Shape.Square(5))) // 20
  println(perimeter(Shape.Rectangle(5, 10))) // 30
  println(perimeter(Shape.Circle(10))) // 62 e qualcosa

  println(area(Shape.Square(5))) // 25
  println(area(Shape.Rectangle(5, 10))) // 50
  println(area(Shape.Circle(10))) // 314 e qualcosa
