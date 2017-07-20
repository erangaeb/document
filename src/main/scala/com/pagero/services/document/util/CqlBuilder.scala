package com.pagero.services.document.util

import com.pagero.services.document.actor.RestRequestHandlerActor.Criteria

object CqlBuilder {
  def buildSearchQuery(criteria: Criteria) = {
    s"SELECT * FROM documents WHERE expr(documents_index, ${buildCriteriaFilter(criteria)});"
  }

  def buildCriteriaFilter(criteria: Criteria) = {
    var f = "'{filter: ["
    def build(l: List[(String, Any)]): String = {
      l match {
        case Nil =>
          f += "]}'"
          f
        case i :: Nil =>
          // last
          f += s"{type: ${'"'}wildcard${'"'}, field:${'"'}${i._1.toLowerCase}${'"'}, value:${'"'}${if (i._2 == None) "*" else i._2.asInstanceOf[Option[Any]].get}${'"'}}"
          build(Nil)
        case i :: rl =>
          f += s"{type: ${'"'}wildcard${'"'}, field:${'"'}${i._1.toLowerCase}${'"'}, value:${'"'}${if (i._2 == None) "*" else i._2.asInstanceOf[Option[Any]].get}${'"'}}, "
          build(rl)
      }
    }

    build(criteria.getClass.getDeclaredFields.map(_.getName).zip(criteria.productIterator.to).toList.drop(1))
  }
}
