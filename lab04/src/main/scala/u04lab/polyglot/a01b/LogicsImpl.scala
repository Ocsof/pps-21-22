package u04lab.polyglot.a01b
import scala.jdk.javaapi.OptionConverters
import scala.util.Random
import u04lab.polyglot.OptionToOptional
import u04lab.code.Option
import u04lab.code.Option.*
import u04lab.code.List
import u04lab.code.List.*


/** solution and descriptions at https://bitbucket.org/mviroli/oop2019-esami/src/master/a01b/sol2/ */
class LogicsImpl(private val size: Int, private val mines: Int) extends Logics:
  var minesSet: List[(Int, Int)] = Nil()
  var selectedSet : List[(Int, Int)] = Nil()
  deployMines()

  def hit(x: Int, y: Int): java.util.Optional[Integer] =
    if contains(this.minesSet, (x, y)) then
      OptionToOptional(None()) // Option => Optional converter
    else
      this.selectedSet = append(this.selectedSet, List((x, y)))
      OptionToOptional(Some(neighbours(x, y)))


  def won = length(selectedSet) + length(minesSet) == (size * size)

  private def neighbours(x: Int, y: Int): Int =
    var n = filter(surroundingCells(x, y))(x => contains(minesSet, x))
    length(n)
    
  private def surroundingCells(x: Int, y: Int): List[(Int, Int)] =
    var list: List[(Int, Int)] = Nil()
    val limit = size-1;
    for
      i <- scala.math.max(0, x-1) to scala.math.min(x+1, limit)
      j <- scala.math.max(0, y-1) to scala.math.min(y+1, limit)
    do
      if(i != x || j != y)
        list = append(list, List((i, j)))
    list

  private def deployMines() = //: Unit credo si possa omettere
    while(length(minesSet) < mines)
      this.minesSet = append(this.minesSet, List(randomFreeCell()))


  private def randomFreeCell(): (Int, Int) =
    val random = Random()
    val randomCell = (random.nextInt(size), random.nextInt(size))
    if(contains(this.minesSet, randomCell)) then randomFreeCell() else randomCell