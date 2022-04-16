package u05lab.ex2

import scala.collection.immutable.HashMap

enum Question:
  case RELEVANCE
  case SIGNIFICANCE
  case CONFIDENCE
  case FINAL

trait ConferenceReviewing:
  def loadReview(article: Int, map: Map[Question ,Int]): Unit

  def loadReview(article: Int, rel: Int, sig: Int, conf: Int, fin: Int): Unit

  def orderedScores(article: Int, question: Question): List[Int]

  def averageFinalScore(article: Int): Double

  def acceptedArticle(): Set[Int]

  def sortedAcceptedArticles(): List[(Int, Double)]

  def averageWeightedFinalScoreMap(): Map[Int, Double]


  case class ConferenceReviewingImpl() extends ConferenceReviewing:
    var reviews: List[(Int, Map[Question, Int])] = List()

    override def loadReview(article: Int, map: Map[Question, Int]): Unit =
      reviews.::(article, map)

    override def loadReview(article: Int, rel: Int, sig: Int, conf: Int, fin: Int): Unit =
      def _createMap(rel: Int, sig: Int, conf: Int, fin: Int): Map[Question, Int] =
        HashMap.apply((Question.RELEVANCE, rel),
          (Question.SIGNIFICANCE, sig),
          (Question.CONFIDENCE, conf),
          (Question.FINAL, fin))
      reviews = reviews.::(article, _createMap(rel, sig, conf, fin))

    override def orderedScores(article: Int, question: Question): List[Int] =
      reviews.filter(_._1 == article)
        .map(_._2.get(question).get)
        .toList
        .sortWith(_ > _)

    override def averageFinalScore(article: Int): Double =
      val list = reviews.filter(_._1 == article)
        .map(_._2.get(Question.FINAL).toList[Int])
      list.foldLeft(0)(_ + _) / list.length
