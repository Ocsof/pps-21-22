package u02

object Es9 extends App:
  enum Tree[A]:
    case Leaf(value: A)
    case Branch(left: Tree[A], right: Tree[A])

  object Tree:
    def traverse[A](t: Tree[A], accFun: A => Int): Int =
      def _traverse(t: Tree[A]): Int = t match
        case Branch(l, r) => _traverse(l) + _traverse(r)
        case Leaf(e) => accFun(e)
      _traverse(t)

    def size[A](t: Tree[A]): Int = traverse(t, (x: A) => 1)

    def find[A](t: Tree[A], elem: A): Boolean = count(t, elem) > 0

    def count[A](t: Tree[A], elem: A): Int = traverse(t, (x: A) => if(x==elem) 1 else 0)

  import Tree.*

  val tree = Branch(Branch(Leaf(1), Leaf(2)), Leaf(1))
  println(tree) // Branch(Branch(Leaf(1),Leaf(2)),Leaf(1))
  println(size(tree)) // ..,3
  println(find(tree, 1)) // true
  println(find(tree, 4)) // false
  println(count(tree, 1)) // 2
