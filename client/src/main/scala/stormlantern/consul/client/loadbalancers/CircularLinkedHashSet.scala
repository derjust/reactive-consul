package stormlantern.consul.client.loadbalancers

import scala.collection.AbstractIterator
import scala.collection.mutable

class CircularLinkedHashSet[A] extends mutable.LinkedHashSet[A] {
  override def iterator: Iterator[A] = new AbstractIterator[A] {
    private var cur = firstEntry
    def hasNext = firstEntry ne null
    def next() =
      if (hasNext) {
        val res = cur.key
        if (cur.later == null)
          cur = firstEntry
        else
          cur = cur.later
        res
      } else Iterator.empty.next()
  }
}
