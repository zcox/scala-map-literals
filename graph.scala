package iascala

case class Edge[+V, +L](source: V, target: V, label: Option[L])

trait Graph[V, L]

case class GraphImpl[V, L](edges: Edge[V, L]*) extends Graph[V, L]

object Graph {
  def apply[V, L](edges: Edge[V, L]*): Graph[V, L] = GraphImpl[V, L](edges: _*)
}

object Implicits {
  final class SourceTarget[V](val source: V) extends AnyVal {
    @inline def -->(target: V): Edge[V, Nothing] = Edge(source, target, None)
  }
  @inline implicit def any2SourceTarget[V](source: V): SourceTarget[V] = new SourceTarget(source)

  final class SourceLabel[V](val source: V) extends AnyVal {
    @inline def --[L](label: L): SourceLabelTarget[V, L] = new SourceLabelTarget(source, label)
  }
  @inline implicit def any2SourceLabel[V](source: V): SourceLabel[V] = new SourceLabel(source)

  final class SourceLabelTarget[V, L](source: V, label: L) {
    @inline def -->(target: V): Edge[V, L] = Edge(source, target, Some(label))
  }
}

object Scratch {
  import Implicits._

  val g1 = Graph(Edge("a", "b", Some(1)), Edge("b", "c", None))

  val g2 = Graph("a" --> "b", "b" --> "c")

  val g3 = Graph("a" -- 1 --> "b", "b" -- 2 --> "c")

  val g4 = Graph("a" --> "b", "b" -- 2 --> "c")

  val g5 = Graph("a" -- 1 --> "b", "b" --> "c")
}
