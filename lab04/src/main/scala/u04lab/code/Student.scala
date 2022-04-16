package u04lab.code

import List.*

trait Student:
  def name: String
  def year: Int
  def enrolling(course: Course*): Unit // the student participates to a Course
  def courses: List[String] // names of course the student participates to
  def hasTeacher(teacher: String): Boolean // is the student participating to a course of this teacher?

trait Course:
  def name: String
  def teacher: String

object Student:
  def apply(name: String, year: Int = 2017): Student = StudentImpl(name, year)
  private case class StudentImpl(name: String,
                                 year: Int) extends Student: //i parametri essendo case class sono automaticamente val e override
    private var coursesList: List[Course] = Nil()
    override def enrolling(course: Course*): Unit = course.foreach(c => coursesList = append(coursesList, List(c)))
    override def courses: List[String] = map(coursesList)(c => c.name)
    override def hasTeacher(teacher: String): Boolean = contains(map(coursesList)(c => c.teacher), teacher)

object Course:
  def apply(name: String, teacher: String): Course = CourseImpl(name, teacher)
  private case class CourseImpl(name: String,
                                teacher: String) extends Course


object SameTeacher:
  def unapply(l: List[Course]): scala.Option[String] = l match
    case Cons(h, t) => if length(filter(l)(_.teacher != h.teacher)) == 0 then Some(h.teacher) else None
/*
//altro modo
object SameTeacher:
  def unapply(l: List[Course]): scala.Option[String] = l match
    case Cons(h, t) => (filter(l)(x => x.teacher == h.teacher)) match
      case Cons(h, Nil()) => None
      case Cons(h, t) => Some(h.teacher)
      case _ => None
    case _ => None
*/

@main def checkStudents(): Unit =
  val cPPS = Course("PPS", "Viroli")
  val cPCD = Course("PCD", "Ricci")
  val cSDR = Course("SDR", "D'Angelo")
  val s1 = Student("mario", 2015)
  val s2 = Student("gino", 2016)
  val s3 = Student("rino") // defaults to 2017
  s1.enrolling(cPPS)
  s1.enrolling(cPCD)
  s2.enrolling(cPPS)
  s3.enrolling(cPPS)
  s3.enrolling(cPCD)
  s3.enrolling(cSDR)
  println(
    (s1.courses, s2.courses, s3.courses)
  ) // (Cons(PCD,Cons(PPS,Nil())),Cons(PPS,Nil()),Cons(SDR,Cons(PCD,Cons(PPS,Nil()))))
  println(s1.hasTeacher("Ricci")) // true

  //val courses = List(cPPS ,cPCD ,cSDR)
  val courses = List(cPPS ,cPPS)
  courses match
    case SameTeacher(t) => println(s"$courses have same teacher $t")
    case _ => println(s"$courses have different teachers")

/** Hints:
  *   - simply implement Course, e.g. with a case class
  *   - implement Student with a StudentImpl keeping a private Set of courses
  *   - try to implement in StudentImpl method courses with map
  *   - try to implement in StudentImpl method hasTeacher with map and find
  *   - check that the two println above work correctly
  *   - refactor the code so that method enrolling accepts a variable argument Course*
  */
